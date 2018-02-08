package game.scene.room;

import game.scene.msg.ChapterEndMsg;
import game.scene.msg.ChapterStartMsg;
import game.scene.msg.RoomEndMsg;
import game.scene.net.BossClient;
import game.scene.room.majiang.MajiangChapter;
import game.scene.services.DbService;
import mj.data.ChapterEndResult;
import mj.data.Config;
import mj.net.message.game.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author zuoge85@gmail.com on 16/10/18.
 */
public class RoomImpi extends Room {
    private long prevTime = 0;
    private long startTime = 0;

    @Autowired
    private DbService dbService;
    @Autowired
    private BossClient bossClient;


    public RoomImpi(RoomAsyncService roomAsyncService, Config config) {
        super(roomAsyncService);
        this.config = config;
    }

    @Override
    protected void frame() {
        long now = System.currentTimeMillis();
        if (prevTime == 0) {
            prevTime = now;
        }
//        log.info("frame:{}", now - prevTime);
        innerFrame(now);
        prevTime = now;
    }


    private void innerFrame(long now) {
        //开始游戏逻辑！
        if (roomInfo.isStart()) {
//            MajiangChapter chapter = roomInfo.getChapter();
//            if(chapter.isOperationTimeOut(now)){
//                //超时，强制出票
//                chapter.forceChuPai();
//            }
        }
    }

    public void join(SceneUser joinSceneUser, List<SceneUser> sceneUsers, Consumer<Boolean> callback) {
        run(() -> {
            if (isEnd()) {
                callback.accept(false);
            } else {
                for (SceneUser sceneUser : sceneUsers) {
                    roomInfo.updateUserInfo(sceneUser);
                }
                //更新 网管和session
                SceneUser sceneUser = roomInfo.getUserInfo(joinSceneUser.getLocationIndex());

                sceneUser.setOnline(true);
                sceneUser.setGatewayId(joinSceneUser.getGatewayId());
                sceneUser.setSessionId(joinSceneUser.getSessionId());

                callback.accept(true);
            }
        });
    }

    @Override
    public void start() {
        super.start();
        startTime = System.currentTimeMillis();
    }

    public void exitRoom(int userId, Consumer<Boolean> callback) {
        run(() -> {
            if (roomInfo.isChapterStart()) {
                callback.accept(false);
            } else {
                callback.accept(true);
                if (roomInfo.removeUserInfo(userId)) {
                    sendMessage(new GameExitUser(userId));
                }
            }
        });
    }

    public void offline(int userId) {
        run(() -> {
            SceneUser user = getRoomInfo().getUserInfoByUserId(userId);
            user.setOnline(false);
            user.setJoinGame(false);
            sendMessage(new UserOffline(user.getLocationIndex()), user);
        });
    }

    public void delRoom(int userId, Consumer<Boolean> callback) {
        run(() -> {
//            if (roomInfo.isChapterStart()) {
//                callback.accept(false);
//            } else {
                callback.accept(true);
//            }
        });
    }

    /**
     * 玩家进入游戏
     */
    public void joinGame(SceneUser sceneUser) {
        checkThread();

        sceneUser.setJoinGame(true);
        //同步游戏信息到客户端
        sceneUser.sendMessage(roomInfo.toMessage(sceneUser.getLocationIndex()));
        //通知其他玩家

        GameUserInfo msg = sceneUser.toMessage();
        roomInfo.excuteDistanceKm(msg, sceneUser);
        //计算距离
        sendMessage(msg, sceneUser);
    }


    public void faPaiRet(SceneUser user, OperationFaPaiRet msg) {
        checkThread();
        MajiangChapter chapter = roomInfo.getChapter();
        chapter.faPaiRet(user.getLocationIndex(), msg.getOpt(), msg.getPai());
    }

    public void outRet(SceneUser user, OperationOutRet msg) {
        checkThread();
        MajiangChapter chapter = roomInfo.getChapter();
        chapter.outRet(user.getLocationIndex(), msg.getPai());
    }

    public void cpghRet(SceneUser user, OperationCPGHRet msg) {
        checkThread();
        MajiangChapter chapter = roomInfo.getChapter();
        chapter.cpghRet(user.getLocationIndex(), msg.getOpt(), msg.getChi());
    }

    public void chapterStart(SceneUser user) {
        checkThread();

        bossClient.writeAndFlush(new ChapterStartMsg());
        if (user.getUserId() == roomInfo.getCreateUserId()) {
            if (roomInfo.isFull()) {
                if (!roomInfo.isChapterStart()) {
                    roomInfo.setStart(true);
                    MajiangChapter chapter = roomInfo.getChapter();
                    chapter.start();
                    roomInfo.setChapterStart(true);

                    for (SceneUser u : roomInfo.getUsers()) {
                        if (u != null && u.isJoinGame()) {
                            u.sendMessage(chapter.toMessage(u.getLocationIndex()));
                        }
                    }
                    chapter.startNext();
                } else {
                    user.sendMessage(new GameChapterStartRet());
                    log.error("已经开始了！user:{},room:{}", user.getUserId(), roomInfo);
                }
            } else {
                user.sendMessage(new GameChapterStartRet());
                user.noticeError("room.notEnoughUser");
            }
        } else {
            log.error("非创建者不能开始！user:{},room:{}", user.getUserId(), roomInfo);
            user.sendMessage(new GameChapterStartRet());
        }
    }


    @Override
    public void endChapter(ChapterEndResult endResult, MajiangChapter majiangChapter) {
        checkThread();
        roomInfo.setChapterStart(false);
        RoomInfo roomInfo = getRoomInfo();
        dbService.save(endResult, roomInfo.getRoomId(), roomInfo.getRoomCheckId(), roomInfo.getSceneId());

        ChapterEndMsg msg = new ChapterEndMsg();
        msg.setRoomId(roomInfo.getRoomId());
        Map<Integer, Integer> map = Arrays.stream(roomInfo.getUsers()).collect(
                Collectors.toMap(SceneUser::getUserId, SceneUser::getScore)
        );
        msg.setUserScoreMap(map);

        bossClient.writeAndFlush(msg);

        if (majiangChapter.getChapterNums() >= config.getInt(Config.CHAPTER_MAX)) {
            //房间结束
            this.setEnd(true);
            RoomEndMsg m = new RoomEndMsg();
            m.setCrateUserId(getRoomInfo().getCreateUserId());
            m.setRoomId(getRoomInfo().getRoomId());
            bossClient.writeAndFlush(m);
        }
    }


    @Override
    public void voteDelStart(VoteDelStart msg, SceneUser user) {
        checkThread();

        SceneUser[] users = roomInfo.getUsers();
        boolean isDirectDel = true;
        for (SceneUser u :users) {
            if(u!=null && u.getUserId()!= user.getUserId() && u.isOnline()){
                isDirectDel = false;
                break;
            }
        }
        if(isDirectDel){
            RoomEndMsg m = new RoomEndMsg();
            m.setCrateUserId(getRoomInfo().getCreateUserId());
            m.setRoomId(getRoomInfo().getRoomId());
            bossClient.writeAndFlush(m);
        }else{
            voteDelInfo.put(user.getUserId(), 0);
            sendMessage(new VoteDelSelect(user.getUserId(), user.getUserName()), user);
        }
    }
    @Override
    public void voteDelSelect(VoteDelSelectRet msg, SceneUser user) {
        checkThread();
        if (msg.getResult()) {
            Integer count = voteDelInfo.get(msg.getUserId());
            if (count != null) {
                voteDelInfo.put(msg.getUserId(), count + 1);
            }
            if (count >= 0) {
                RoomEndMsg m = new RoomEndMsg();
                m.setCrateUserId(getRoomInfo().getCreateUserId());
                m.setRoomId(getRoomInfo().getRoomId());
                bossClient.writeAndFlush(m);
            }
        }
    }
}
