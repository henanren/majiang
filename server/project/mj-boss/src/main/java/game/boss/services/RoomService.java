package game.boss.services;

import com.isnowfox.core.thread.FrameQueueContainer;
import com.isnowfox.util.UUID;
import game.boss.ServerRuntimeException;
import game.boss.dao.dao.*;
import game.boss.dao.entity.*;
import game.boss.model.Room;
import game.boss.model.User;
import game.boss.net.BossService;
import game.boss.type.RoomCheckIdState;
import game.scene.msg.ChapterEndMsg;
import game.scene.msg.ChapterStartMsg;
import game.scene.msg.CheckDelRoomMsg;
import game.utils.DateUtils;
import mj.data.Config;
import mj.net.message.login.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 房间服务
 * 一致性的保证在于,同一个用户的逻辑只在一个线程内,请注意!
 * <p>
 * <b>房间CHECK_ID 原则是:<b/>
 * 先把查找NO_USE 修改成BUFFER,然后插入线程安全队列
 * 然后使用,使用后修改USE
 * 使用结束修改NO_USE
 * 以上原则保证一致性,
 * <p>
 * 但是注意 服务器启动的时候检查 BUFFER的id,如果存在全部修改成NO_USE
 * 因为可能出现BUFFE装的未使用
 *
 * @author zuoge85@gmail.com on 16/9/30.
 */
@Service
public class RoomService extends FrameQueueContainer implements BaseService {
    private static final Logger log = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    private BossService bossService;
    @Autowired
    private AsyncDbService asyncDbService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoomUserDao roomUserDao;

    @Autowired
    private UserLinkRoomDao userLinkRoomDao;
    @Autowired
    private RoomCheckIdPoolDao roomCheckIdPoolDao;

    /**
     * 房间映射
     */
    private HashMap<Integer, Room> joinUserRoomMap = new HashMap<>();

    /**
     * 创建
     */
    private HashMap<Integer, Room> crateUserRoomMap = new HashMap<>();
    /**
     * 房间映射
     */
    private HashMap<Integer, Room> roomMap = new HashMap<>();
    /**
     * 房间映射
     */
    private HashMap<String, Room> checkIdRoomMap = new HashMap<>();


    public RoomService() {
        super(FRAME_TIME_SPAN, RUN_QUEUE_MAX);
    }

    @PostConstruct
    private void init() {
        String sql = "UPDATE " + RoomCheckIdPoolDO.Table.DB_TABLE_NAME +
                " SET `" + RoomCheckIdPoolDO.Table.STATE + "`=" + RoomCheckIdState.NO_USE.ordinal() +
                " WHERE `" + RoomCheckIdPoolDO.Table.STATE + "`=" + RoomCheckIdState.BUFFER.ordinal();

        log.debug("init:" + sql);
        roomCheckIdPoolDao.getJdbcTemplate().execute(
                sql
        );
        start();
    }

    /**
     * 创建房间
     */
    public void createRoom(CreateRoom msg, User user) {
        run(() -> {
            {
                Room room = crateUserRoomMap.get(user.getUserId());
                if (room != null) {
                    sendAlreadyCreateRoom(user);
                    sendCreateRoomRet(user, false, null);
                    return;
                }
            }
            Config config = new Config(msg);
            {
                int max = config.getInt(Config.CHAPTER_MAX);
                int gold = (int) Math.ceil(max / 8);

                if (user.getUserDO().getGold() < gold) {
                    sendNoGold(user);
                    sendCreateRoomRet(user, false, null);
                    return;
                }
            }
            //生成roomCheckId
            String roomCheckId = getBufferId();
            int sceneId = bossService.getRandomSceneId();
            asyncDbService.excuete(user, () -> {
                RoomDO room = checkUserCreateRoom(user);
                if (room == null) {
                    room = new RoomDO();
                    room.setCreateUserId(user.getUserId());
                    room.setRoomCheckId(roomCheckId);
                    room.setSceneId(sceneId);
                    room.setStart(true);
                    room.setStartDate(new Date());
                    room.setUserMax(4);
                    room.setUuid(UUID.generateNoSep());
                    room.setConfig(config);


                    int isUpdate = roomCheckIdPoolDao.updatePartial(
                            Collections.singletonMap(RoomCheckIdPoolDO.Table.STATE, RoomCheckIdState.USE.ordinal()),
                            RoomCheckIdPoolDO.Table.ID, roomCheckId,
                            RoomCheckIdPoolDO.Table.STATE, RoomCheckIdState.BUFFER.ordinal()
                    );

                    if (isUpdate < 1) {
                        throw new RuntimeException("id使用错误!,id状态不对!" + roomCheckId);
                    }
                    long id = roomDao.insert(room);
                    room.setId((int) id);

                    List<RoomDO> roomDOs = roomDao.find(100, RoomDO.Table.ROOM_CHECK_ID, room.getRoomCheckId(), RoomDO.Table.START, true);
                    if (roomDOs.size() > 1) {
                        roomDao.del(room.getKey());
                        sendCreateRoomError(user);
                        sendCreateRoomRet(user, false, null);
                        throw new RuntimeException("严重错误!roomCheckId 使用错误!,创建房间失败" + roomDOs);
                    } else {
                        initRoomData(user, room);
                        //房间创建成功
                        sendCreateRoomSuccess(user);
                        sendCreateRoomRet(user, true, room.getRoomCheckId());
                    }
                } else {
                    //释放id
                    freeId(roomCheckId);
                    sendAlreadyCreateRoom(user);
                    sendCreateRoomRet(user, false, null);
                    //检查 checkId 是否已经被使用
                    List<RoomDO> roomDOs = roomDao.find(100, RoomDO.Table.ROOM_CHECK_ID, room.getRoomCheckId(), RoomDO.Table.START, true);
                    if (roomDOs.size() > 1) {
                        throw new RuntimeException("严重错误!roomCheckId 使用错误!" + roomDOs);
                    }
                }
            });
        });
    }

    private void sendCreateRoomRet(User user, boolean result, String roomCheckId) {
        user.send(new CreateRoomRet(result, roomCheckId));
    }

    /**
     * 进入房间
     * 如果已经进入房间,可以重复进入
     */
    private void joinRoom(User user, Room room, Consumer<Boolean> callback) {
        if (frameThread != Thread.currentThread()) {
            throw new ServerRuntimeException("只能在指定的线程调用");
        }
        initRoomData(user, room);
        RoomDO roomDO = room.getRoomDO();

        user.setJoinHomeGatewaySuccess(false);
        user.setJoinHomeSceneSuccess(false);

        int userId = user.getUserId();
        int locationIndex = -1;

        String userName = user.getUserDO().getName();
        if (roomDO.getUser0() < 1 || roomDO.getCreateUserId() == userId) {
            roomDO.setUser0(userId);
            roomDO.setUserName0(userName);
            locationIndex = 0;
        } else if (userId == roomDO.getUser1()) {
            roomDO.setUser1(userId);
            roomDO.setUserName1(userName);
            locationIndex = 1;
        } else if (userId == roomDO.getUser2()) {
            roomDO.setUser2(userId);
            roomDO.setUserName2(userName);
            locationIndex = 2;
        } else if (userId == roomDO.getUser3()) {
            roomDO.setUser3(userId);
            roomDO.setUserName3(userName);
            locationIndex = 3;
        } else if (roomDO.getUser1() < 1) {
            roomDO.setUser1(userId);
            roomDO.setUserName1(userName);
            locationIndex = 1;
        } else if (roomDO.getUser2() < 1) {
            roomDO.setUser2(userId);
            roomDO.setUserName2(userName);
            locationIndex = 2;
        } else if (roomDO.getUser3() < 1) {
            roomDO.setUser3(userId);
            roomDO.setUserName3(userName);
            locationIndex = 3;
        } else {
            sendRoomFull(user);
            callback.accept(false);
            return;
        }
        //
        int finalLocationIndex = locationIndex;
        asyncDbService.excuete(user, () -> {
            RoomUserDO roomUserDO = roomUserDao.get(userId);
            if (roomUserDO == null) {
                roomUserDO = new RoomUserDO();
            }
            roomUserDO.setUserId(userId);
            roomUserDO.setStartDate(new Date());
            roomUserDO.setRoomId(roomDO.getId());
            roomUserDO.setRoomCheckId(roomDO.getRoomCheckId());
            roomUserDO.setLocationIndex(finalLocationIndex);


            roomUserDao.replace(roomUserDO);
            roomDO.setVersion(roomUserDO.getVersion() + 1);
            roomDao.update(roomDO);
            user.setJoinRoomCallback(callback);
            joinRoomSuccess(user, room, roomUserDO);
        });
    }

    public void checkOffline(User user) {
        run(() -> {
            Room room = joinUserRoomMap.get(user.getUserId());
            if (room != null) {
                bossService.startOfflineScene(user, room, user.getSessionId());
            } else {
                log.info("玩家不在房间，无法发送离线消息！", user);
            }
        });
    }

    private void joinRoomSuccess(User user, Room room, RoomUserDO roomUserDO) {
        run(() -> {
            room.addUser(roomUserDO.getLocationIndex(), user.getUserDO());
            joinUserRoomMap.put(user.getUserId(), room);
            bossService.startJoinScene(user, room, roomUserDO, user.getSessionId());
        });
    }

    public void joinRoomGatewaySuccess(User user) {
        run(() -> {
            user.setJoinHomeGatewaySuccess(true);
            if (user.isJoinHomeGatewaySuccess() && user.isJoinHomeSceneSuccess()) {
                joinHomeSceneSuccess(user);
            }
        });
    }

    public void joinRoomSceneSuccess(int userId, boolean succcess) {
        userService.handlerUser(userId, user -> {
            run(() -> {
                if (succcess) {
                    user.setJoinHomeSceneSuccess(true);
                    if (user.isJoinHomeGatewaySuccess() && user.isJoinHomeSceneSuccess()) {
                        joinHomeSceneSuccess(user);
                    }
                } else {
                    //进入失败
                    Consumer<Boolean> callback = user.getJoinRoomCallback();
                    callback.accept(false);
                    user.setJoinRoomCallback(null);
                }
            });
        });
    }

    private void joinHomeSceneSuccess(User user) {
        if (frameThread != Thread.currentThread()) {
            throw new ServerRuntimeException("只能在指定的线程调用");
        }
        Consumer<Boolean> callback = user.getJoinRoomCallback();
        callback.accept(true);
        user.setJoinRoomCallback(null);

        user.send(new StartGame());
    }

    public void delRoom(int userId, User user, boolean isEnd) {
        asyncDbService.excuete(userId, () -> {
            RoomDO room = roomDao.findObject(
                    RoomDO.Table.CREATE_USER_ID, userId,
                    RoomDO.Table.START, true
            );
            if (room != null) {
                run(() -> {
                    bossService.startDelRoomScene(userId, room, isEnd);
                });
            } else {
                log.error("关闭房间错误！crateUserId:{}", userId);
                if (user != null && !isEnd) {
                    sendAlreadyDelRoom(user);
                    sendDelRoomRet(user);
                }
            }
        });
    }

    public void delRoomSceneSuccess(CheckDelRoomMsg msg) {
        userService.handlerUser(msg.getUserId(), user -> {
            run(() -> {
                if (msg.isResult()) {
                    bossService.startDelRoomGateway(msg.getInfos());
                    delRoomSuccess(msg.getUserId(), user, msg.isEnd());
                } else {
                    log.error("房间不能关闭！crateUserId:{}", msg.getUserId());
                    if (user != null && !msg.isEnd()) {
                        sendCannotDelRoom(user);
                    }
                }
            });
        });
    }

    public void delRoomGatewaySuccess(User user) {
        run(() -> {
//            delRoomSuccess(user);
        });
    }


    private void delRoomSuccess(final int userId, User user, boolean isEnd) {
        if (frameThread != Thread.currentThread()) {
            throw new ServerRuntimeException("只能在指定的线程调用");
        }
        asyncDbService.excuete(userId, () -> {
            RoomUserDO roomUserDO = roomUserDao.get(userId);
            if (roomUserDO != null) {
                RoomDO room = roomDao.get(roomUserDO.getRoomId());
                if (room != null) {
                    run(() -> {
                        room.setEndDate(new Date());

                        crateUserRoomMap.remove(userId);
                        roomMap.remove(room.getId());
                        checkIdRoomMap.remove(room.getRoomCheckId());
                        joinUserRoomMap.remove(room.getUser0());
                        joinUserRoomMap.remove(room.getUser1());
                        joinUserRoomMap.remove(room.getUser2());
                        joinUserRoomMap.remove(room.getUser3());

                        Room roomObj = roomMap.get(room.getId());
                        //房主扣除房卡
                        if (roomObj == null || roomObj.isStart()) {
                            userService.minusGold(room.getCreateUserId(), room.getConfig());
                        }


                        asyncDbService.excuete(userId, () -> {


                            room.setStart(false);
                            room.setEnd(isEnd);
                            room.setVersion(room.getVersion() + 1);
                            roomDao.update(room);
                            //插入记录


                            List<UserLinkRoomDO> userLinkRoomDOList = new ArrayList<>();
                            for (int i = 0; i < 4; i++) {
                                try {
                                    int curUserId = (Integer) BeanUtils.getPropertyDescriptor(RoomDO.class, "User" + i).getReadMethod().invoke(room);
                                    if (curUserId > 0) {
                                        roomUserDao.del(new RoomUserDO.Key(curUserId));
                                    }
                                    if (room.getChapterNums() > 0) {
                                        UserLinkRoomDO link = new UserLinkRoomDO();
                                        link.setUserId(curUserId);
                                        link.setRoomId(room.getId());
                                        BeanUtils.copyProperties(room, link, "key");
                                        userLinkRoomDOList.add(link);
                                    }
                                } catch (Exception e) {
                                    throw new ServerRuntimeException(e);
                                }
                            }
                            if (userLinkRoomDOList.size() > 0) {
                                userLinkRoomDao.insert(userLinkRoomDOList);
                            }

                            //释放checkId
                            roomCheckIdPoolDao.updatePartial(
                                    Collections.singletonMap(RoomCheckIdPoolDO.Table.STATE, RoomCheckIdState.NO_USE.ordinal()),
                                    RoomCheckIdPoolDO.Table.ID, room.getRoomCheckId(),
                                    RoomCheckIdPoolDO.Table.STATE, RoomCheckIdState.BUFFER.ordinal()
                            );
                            if (user != null) {
                                sendDelRoomRet(user);
                            }
                        });
                    });
                } else {
                    if (user != null && !isEnd) {
                        sendAlreadyDelRoom(user);
                        sendDelRoomRet(user);
                    }
                }
            } else {
                if (user != null && !isEnd) {
                    sendAlreadyDelRoom(user);
                    sendDelRoomRet(user);
                }
            }
        });

    }

    /**
     * 先退出场景服务器，如果成功在退出网关
     */
    public void exitRoom(User user) {
        asyncDbService.excuete(user, () -> {
            final int userId = user.getUserId();
            RoomUserDO roomUserDO = roomUserDao.get(userId);
            if (roomUserDO != null) {
                RoomDO room = roomDao.get(roomUserDO.getRoomId());
                if (room != null) {
                    run(() -> {
                        bossService.startExitRoomScene(user, room);
                    });
                } else {
                    sendAlreadyExitRoom(user);
                    sendExitRoomRet(user);
                }
            } else {
                sendAlreadyExitRoom(user);
                sendExitRoomRet(user);
            }
        });
    }


    public void exitRoomSceneSuccess(int userId, int sceneId, boolean result) {
        userService.handlerUser(userId, user -> {
            run(() -> {
                if (result) {
                    bossService.startExitRoomGateway(user, sceneId);
                } else {
                    sendCannotExitRoom(user);
                }
            });
        });
    }

    public void exitRoomGatewaySuccess(User user) {
        run(() -> {
            exitRoomSuccess(user);
        });
    }


    public void chapterStart(ChapterStartMsg msg) {
        run(() -> {
            Room room = roomMap.get(msg.getRoomId());
            if (room != null) {
                room.setStart(true);
            }
        });
    }

    public void chapterEnd(ChapterEndMsg msg) {
        run(() -> {
            Room room = roomMap.get(msg.getRoomId());
            if (room != null) {
                RoomDO roomDO = room.getRoomDO();
                msg.getUserScoreMap().forEach((userId, score) -> {
                    if (userId == roomDO.getUser0()) {
                        roomDO.setScore0(score);
                    } else if (userId == roomDO.getUser1()) {
                        roomDO.setScore1(score);
                    } else if (userId == roomDO.getUser2()) {
                        roomDO.setScore2(score);
                    } else if (userId == roomDO.getUser3()) {
                        roomDO.setScore3(score);
                    }
                });

                roomDO.setChapterNums(roomDO.getChapterNums() + 1);
                asyncDbService.excuete(() -> {
                    roomDao.update(roomDO);
                });
            }
        });
    }


    private void exitRoomSuccess(User user) {
        if (frameThread != Thread.currentThread()) {
            throw new ServerRuntimeException("只能在指定的线程调用");
        }

        int userId = user.getUserId();
        Room room = joinUserRoomMap.remove(user.getUserId());
        if (room != null) {
            RoomDO roomDO = room.getRoomDO();
            room.removeUser(userId);
            if (roomDO.getUser0() == userId) {
                roomDO.setUser0(0);
            } else if (roomDO.getUser1() == userId) {
                roomDO.setUser1(0);
            } else if (roomDO.getUser2() == userId) {
                roomDO.setUser2(0);
            } else if (roomDO.getUser3() == userId) {
                roomDO.setUser3(0);
            } else {
                sendAlreadyExitRoom(user);
                sendExitRoomRet(user);
                return;
            }

            asyncDbService.excuete(user, () -> {
                roomUserDao.del(new RoomUserDO.Key(userId));
                roomDO.setVersion(roomDO.getVersion() + 1);
                roomDao.update(roomDO);
                sendExitRoomRet(user);
            });
        } else {
            sendAlreadyExitRoom(user);
            sendExitRoomRet(user);
        }
    }


    public void joinRoom(String roomCheckId, User user) {
        run(() -> {
            if (frameThread != Thread.currentThread()) {
                throw new ServerRuntimeException("只能在指定的线程调用");
            }

            {
                Room room = checkIdRoomMap.get(roomCheckId);
                if (room != null) {
                    joinRoom(user, room, result -> {
                        sendJoinRoomRet(user, result);
                    });
                    return;
                }
            }
            asyncDbService.excuete(user, () -> {
                RoomDO roomDO = roomDao.findObject(
                        RoomDO.Table.ROOM_CHECK_ID, roomCheckId,
                        RoomDO.Table.START, true
                );

                //读取全部进入游戏的用户信息
                if (roomDO != null) {
                    Room room = new Room(roomDO);
                    for (int i = 0; i < 4; i++) {
                        try {
                            int userId = (Integer) BeanUtils.getPropertyDescriptor(RoomDO.class, "User" + i).getReadMethod().invoke(room.getRoomDO());
                            if (userId > -1) {
                                UserDO userDO = userDao.get(userId);
                                if (userDO != null) {
                                    room.addUser(i, userDO);
                                }
                            }
                        } catch (Exception e) {
                            throw new ServerRuntimeException(e);
                        }
                    }
                    run(() -> {
                        joinRoom(user, room, result -> {
                            sendJoinRoomRet(user, result);
                        });
                    });
                } else {
                    sendErrorRoomCheckId(user);
                    sendJoinRoomRet(user, false);
                }
            });
        });
    }

    private void sendJoinRoomRet(User user, boolean result) {
        user.send(new JoinRoomRet(result));
    }

    /**
     * 检查用户是否进入了房间,如果进入了房间,那么尝试进入房间
     */
    public void checkUserRoom(User user, Consumer<String> callback) {
        asyncDbService.excuete(user, () -> {
            RoomUserDO roomUserDO = roomUserDao.get(user.getUserId());
            if (roomUserDO == null) {
                callback.accept(null);
            } else {
                callback.accept(roomUserDO.getRoomCheckId());
            }
        });
    }

    /**
     * 一致性的保证在于,同一个用户的逻辑只在一个线程内
     * !!!!!!!!!!!请注意
     */
    private RoomDO checkUserCreateRoom(User user) {
        if (frameThread == Thread.currentThread()) {
            throw new ServerRuntimeException("只能在指定的线程调用,不能再room 线程使用");
        }
        RoomDO room = roomDao.findObject(
                RoomDO.Table.CREATE_USER_ID, user.getUserId(),
                RoomDO.Table.START, true
        );
        if (room != null) {
//            initRoomData(user, room);
            return room;
        }
        return null;
    }

    private void initRoomData(User user, RoomDO roomDO) {
        run(() -> {
            initRoomData(user, new Room(roomDO));
        });
    }

    private boolean initRoomData(User user, Room room) {
        if (frameThread != Thread.currentThread()) {
            throw new ServerRuntimeException("只能在指定的线程调用");
        }
        RoomDO roomDO = room.getRoomDO();
        if (!roomMap.containsKey(roomDO.getId())) {
            checkIdRoomMap.put(roomDO.getRoomCheckId(), room);
            crateUserRoomMap.put(roomDO.getCreateUserId(), room);
            roomMap.put(roomDO.getId(), room);
            return true;
        }
        return false;
    }

    private LinkedBlockingQueue<String> idBuffer = new LinkedBlockingQueue<>();

    /**
     * 生成一个随机id 不重复!
     */
    private String getBufferId() {
        if (frameThread != Thread.currentThread()) {
            throw new ServerRuntimeException("只能在指定的线程调用");
        }
        String id = idBuffer.poll();
        if (id != null) {
            return id;
        }
        initCheckId();
        try {
            return idBuffer.take();
        } catch (InterruptedException e) {
            throw new ServerRuntimeException(e);
        }
    }

    private void freeId(String id) {
        idBuffer.offer(id);
    }

    private void initCheckId() {
        asyncDbService.excuete(() -> {
            //查找多个未使用id,然后缓存
            if (idBuffer.size() > INIT_ID_BUFFER) {
                return;
            }
            for (int l = 0; l < LOOP_NUMS; l++) {
                if (!idBuffer.isEmpty()) {
                    return;
                }

                List<RoomCheckIdPoolDO> roomCheckIdPoolDOs = roomCheckIdPoolDao.find(
                        100, RoomCheckIdPoolDO.Table.STATE, RoomCheckIdState.NO_USE.ordinal()
                );
                for (int i = 0; i < roomCheckIdPoolDOs.size(); i++) {
                    RoomCheckIdPoolDO idPoolDO = roomCheckIdPoolDOs.get(i);
                    int isUpdate = roomCheckIdPoolDao.updatePartial(
                            Collections.singletonMap(RoomCheckIdPoolDO.Table.STATE, RoomCheckIdState.BUFFER.ordinal()),
                            RoomCheckIdPoolDO.Table.ID, idPoolDO.getId(),
                            RoomCheckIdPoolDO.Table.STATE, RoomCheckIdState.NO_USE.ordinal()
                    );
                    if (isUpdate > 0) {
                        idBuffer.offer(idPoolDO.getId());
                    }
                }
                if (idBuffer.isEmpty()) {
                    RoomCheckIdPoolDO item = new RoomCheckIdPoolDO();
                    for (int i = 0; i < INIT_ID_NUMS; ) {
                        item.setId(RandomStringUtils.randomNumeric(CHECK_ROOM_ID_LEN));
                        item.setState(RoomCheckIdState.BUFFER.ordinal());
                        try {
                            roomCheckIdPoolDao.insert(item);
                            i++;
                            idBuffer.offer(item.getId());
                        } catch (DuplicateKeyException ignored) {

                        }
                    }
                }
            }
            throw new ServerRuntimeException("id 初始化严重问题!!!!超过重复次数上线,需要人工介入");
        });
    }


    private void sendAlreadyCreateRoom(User user) {
        user.noticeError("room.alreadyCreateRoom");
    }

    private void sendNoGold(User user) {
        user.noticeError("room.noGold");
    }

    private void sendCreateRoomSuccess(User user) {
        user.noticeError("room.createRoomSuccess");
    }

//    private void sendAlreadyJoinRoom(User user) {
//        user.noticeError("room.alreadyJoinRoom");
//    }

    private void sendErrorRoomCheckId(User user) {
        user.noticeError("room.errorRoomCheckId");
    }

    private void sendRoomFull(User user) {
        user.noticeError("room.full");
    }

    private void sendCreateRoomError(User user) {
        user.noticeError("room.createRoomError");
    }


    private void sendAlreadyExitRoom(User user) {
        user.noticeError("room.alreadyExitRoom");
    }

    private void sendAlreadyDelRoom(User user) {
        user.noticeError("room.alreadyDelRoom");
    }


    private void sendCannotExitRoom(User user) {
        user.noticeError("room.cannotExitRoom");
    }

    private void sendCannotDelRoom(User user) {
        user.send(new DelRoomRet(false));
        user.noticeError("room.cannotDelRoom");
    }

    private void sendExitRoomRet(User user) {
        user.send(new ExitRoomRet());
    }

    private void sendDelRoomRet(User user) {
        user.send(new DelRoomRet(true));
    }

    @Override
    protected void threadMethod(int frameCount, long time, long passedTime) {
    }

    @Override
    protected void errorHandler(Throwable e) {
        log.error("严重异常", e);
    }

    public void requestHistory(User user) {
        asyncDbService.excuete(user, () -> {
            List<UserLinkRoomDO> list = userLinkRoomDao.find(20, UserLinkRoomDO.Table.USER_ID, user.getUserId());
            ArrayList<RoomHistory> collect = list.stream().map(r -> {
                RoomHistory h = new RoomHistory();
                h.setChapterNums(r.getChapterNums());
                h.setRoomCheckId(r.getRoomCheckId());
                h.setStartDate(DateUtils.format(r.getStartDate()));
                h.setUserNames(new String[]{
                        r.getUserName0(), r.getUserName1(), r.getUserName2(), r.getUserName3()
                });
                h.setScores(new int[]{
                        r.getScore0(), r.getScore1(), r.getScore2(), r.getScore3()
                });
                return h;
            }).collect(Collectors.toCollection(ArrayList::new));

            RoomHistoryListRet msg = new RoomHistoryListRet();
            msg.setList(collect);
            user.send(msg);
        });
    }

}
