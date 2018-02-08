package game.scene.room;

import com.isnowfox.core.net.message.Message;
import com.isnowfox.core.thread.FrameQueueContainer;
import game.boss.SceneUserInfo;
import game.scene.msg.*;
import game.scene.net.BossClient;
import mj.data.Config;
import mj.net.handler.MessageHandler;
import mj.net.message.game.GameDelRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zuoge85@gmail.com on 16/9/30.
 */
public class RoomService extends FrameQueueContainer implements ApplicationContextAware {
    private static final int FRAME_TIME_SPAN = 33;
    private static final int RUN_QUEUE_MAX = 1024;

    private static final Logger log = LoggerFactory.getLogger(RoomService.class);

    @Value("${sceneId}")
    private int sceneId;

    @Value("${rulesName}")
    private String rulesName;

    private RoomAsyncService roomAsyncService;
    private ApplicationContext applicationContext;
    private HashMap<Integer, RoomImpi> map = new HashMap<>();
    private HashMap<Integer, SceneUser> sceneUserMap = new HashMap<>();
    /**
     * 通过id 映射到map
     * id 是 gatewayId<<32|sessionId
     */
    private HashMap<Long, Room> gatewayIdUnionSessionIdMap = new HashMap<>();

    /**
     * 通过id 映射到map
     * id 是 gatewayId<<32|sessionId
     */
    private HashMap<Long, SceneUser> gatewayIdUnionSessionIdUserMap = new HashMap<>();

    public RoomService() {
        super(FRAME_TIME_SPAN, RUN_QUEUE_MAX);
        start();
    }

    @Autowired
    private BossClient bossClient;


    public void setRoomAsyncService(RoomAsyncService roomAsyncService) {
        this.roomAsyncService = roomAsyncService;
    }


    public void checkOfflineRoom(CheckOfflineRoomMsg msg) {
        run(() -> {
            RoomImpi room = map.get(msg.getRoomId());
            if (room == null) {
                log.info("房间已经不存在了");
                return;
            }
            room.offline(msg.getUserId());
        });
    }

    public void checkJoinRoom(CheckJoinRoomMsg msg) {
        //创建房间
        run(() -> {
            RoomImpi room = map.get(msg.getRoomId());
            if (room == null) {
                Config config = new Config(msg.getOptions());
                room = new RoomImpi(roomAsyncService, config);

                RoomInfo roomInfo = new RoomInfo(room, rulesName);
                BeanUtils.copyProperties(msg, roomInfo);
                room.setRoomInfo(roomInfo);

                room.start();
                applicationContext.getAutowireCapableBeanFactory().autowireBean(room);
                map.put(msg.getRoomId(), room);
            }
            //进入房间成功
            SceneUser joinSceneUser = null;

            List<SceneUser> sceneUsers = new ArrayList<>();
            for (UserInfo userInfo : msg.getUserInfos()) {
                SceneUser sceneUser = sceneUserMap.get(userInfo.getUserId());
                if (sceneUser == null) {
                    sceneUser = new SceneUser();
                    sceneUser.setJoinGame(false);
                    applicationContext.getAutowireCapableBeanFactory().autowireBean(sceneUser);
                }
                sceneUser.setRoom(room);
                sceneUser.setLocationIndex(userInfo.getLocationIndex());
                sceneUser.setUserName(userInfo.getUserName());
                sceneUser.setAvatar(userInfo.getAvatar());
                sceneUser.setSex(userInfo.getSex());
                sceneUser.setGold(userInfo.getGold());
                sceneUser.setUserId(userInfo.getUserId());
                sceneUser.setIp(userInfo.getIp());
                sceneUser.setLongitude(userInfo.getLongitude());
                sceneUser.setLatitude(userInfo.getLatitude());
                if (userInfo.getUserId() == msg.getJoinUserId()) {
                    sceneUser.setSessionId(msg.getJoinSessionId());
                    sceneUser.setGatewayId(msg.getJoinGatewayId());
                    joinSceneUser = sceneUser;
                }
                sceneUsers.add(sceneUser);

                if (msg.getUser0() == userInfo.getUserId()) {
                    sceneUser.setScore(msg.getScore0());
                } else if (msg.getUser1() == userInfo.getUserId()) {
                    sceneUser.setScore(msg.getScore1());
                } else if (msg.getUser2() == userInfo.getUserId()) {
                    sceneUser.setScore(msg.getScore2());
                } else if (msg.getUser3() == userInfo.getUserId()) {
                    sceneUser.setScore(msg.getScore3());
                }

                sceneUserMap.put(userInfo.getUserId(), sceneUser);
            }


            long gatewayIdUnionSessionId = getGatewayIdUnionSessionId(msg.getJoinSessionId(), msg.getJoinGatewayId());
            gatewayIdUnionSessionIdMap.put(gatewayIdUnionSessionId, room);
            gatewayIdUnionSessionIdUserMap.put(gatewayIdUnionSessionId, joinSceneUser);
            room.join(joinSceneUser, sceneUsers, (r) -> {
                //进入房间成功
                CheckJoinRoomRetMsg ret = new CheckJoinRoomRetMsg();
                ret.setRoomId(msg.getRoomId());
                ret.setJoinUserId(msg.getJoinUserId());
                ret.setJoinGatewayId(msg.getJoinGatewayId());
                ret.setJoinSessionId(msg.getJoinSessionId());
                ret.setSucccess(r);
                bossClient.writeAndFlush(ret);
            });
        });
    }

    public void checkExitRoom(CheckExitRoomMsg msg) {
        run(() -> {
            RoomImpi room = map.get(msg.getRoomId());
            if (room == null) {
                return;
            }

            room.exitRoom(msg.getUserId(), (ret) -> {
                run(() -> {
                    if (ret) {
                        long gatewayIdUnionSessionId = getGatewayIdUnionSessionId(msg.getJoinSessionId(), msg.getJoinGatewayId());
                        gatewayIdUnionSessionIdMap.remove(gatewayIdUnionSessionId);
                        gatewayIdUnionSessionIdUserMap.remove(gatewayIdUnionSessionId);
                    }
                    msg.setResult(ret);
                    bossClient.writeAndFlush(msg);
                });
            });
        });
    }

    public void checkDelRoom(CheckDelRoomMsg msg) {
        run(() -> {
            RoomImpi room = map.get(msg.getRoomId());
            if (room == null) {
                return;
            }
            room.delRoom(msg.getUserId(), (ret) -> {
                //进入房间成功
                run(() -> {
                    List<SceneUserInfo> userInfos = new ArrayList<SceneUserInfo>();
                    if (ret) {
                        RoomInfo roomInfo = room.getRoomInfo();
                        for (SceneUser u : roomInfo.getUsers()) {
                            if (u != null) {
                                long gatewayIdUnionSessionId = getGatewayIdUnionSessionId(u.getSessionId(), u.getGatewayId());
                                gatewayIdUnionSessionIdMap.remove(gatewayIdUnionSessionId);
                                gatewayIdUnionSessionIdUserMap.remove(gatewayIdUnionSessionId);
                                u.sendMessage(new GameDelRoom(room.isEnd(),room.isStart()));
                                if(room.isEnd()){
//                                    u.noticeError("room.endRoom");
                                }else{
//                                    u.noticeError("room.delRoom");
                                }
                                userInfos.add(new SceneUserInfo((short) u.getSessionId(), u.getGatewayId(), u.getUserId()));
                            }
                        }
                        map.remove(msg.getRoomId());
                        room.close();
                        room.setRoomInfo(null);
                        msg.setInfos(userInfos);
                    }
                    msg.setResult(ret);
                    bossClient.writeAndFlush(msg);
                });
            });
        });
    }

    public void handler(MessageHandler handler, Message message, short sessionId, short gatewayId) {
        run(() -> {
            long gatewayIdUnionSessionId = getGatewayIdUnionSessionId(sessionId, gatewayId);
            Room room = gatewayIdUnionSessionIdMap.get(gatewayIdUnionSessionId);
            if (room == null) {
                throw new RuntimeException(String.format(
                        "未正确进入Room,消息不能执行! Message:%s,sessionId:%s,gatewayId:%s",
                        message, sessionId, gatewayId
                ));
            }
            SceneUser sceneUser = gatewayIdUnionSessionIdUserMap.get(gatewayIdUnionSessionId);
            if (sceneUser == null) {
                throw new RuntimeException(String.format(
                        "玩家未进入房间?,消息不能执行! Room:%s, Message:%s,sessionId:%s,gatewayId:%s",
                        room, message, sessionId, gatewayId
                ));
            }
            room.handler(handler, message, sceneUser);
        });
    }

    @Override
    protected void threadMethod(int frameCount, long time, long passedTime) {

    }

    private long getGatewayIdUnionSessionId(int gatewayId, int sessionId) {
        return (long) gatewayId << 32 | (long) sessionId;
    }

    @Override
    protected void errorHandler(Throwable t) {
        log.error("严重异常", t);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
