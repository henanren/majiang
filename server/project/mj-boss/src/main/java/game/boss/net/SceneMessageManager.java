package game.boss.net;

import com.isnowfox.core.net.Session;
import com.isnowfox.game.proxy.message.PxMsg;
import game.boss.ServerRuntimeException;
import game.boss.services.RoomService;
import game.boss.services.UserService;
import game.scene.msg.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zuoge85@gmail.com on 16/9/27.
 */
public class SceneMessageManager {
    private static final Logger log = LoggerFactory.getLogger(SceneMessageManager.class);

    @Autowired
    private SceneManager sceneManager;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;

    void handler(PxMsg msg) {
        short sessionId = -1;
        try {
            switch (msg.getType()){
                case RegSceneMsg.ID:{
                    RegSceneMsg regSceneMsg = (RegSceneMsg) msg;
                    log.info("注册Scene", regSceneMsg);
                    Scene scene = sceneManager.reg(
                            msg.getSession().channel, regSceneMsg.getSceneId(),
                            regSceneMsg.getSceneAddress(), regSceneMsg.getScenePort()
                    );
                    if (scene != null) {
                        msg.getSession().set(scene);
                    }
                    break;
                }
                case CheckJoinRoomRetMsg.ID:{
                    CheckJoinRoomRetMsg checkJoinRoomRetMsg = (CheckJoinRoomRetMsg) msg;
                    roomService.joinRoomSceneSuccess(checkJoinRoomRetMsg.getJoinUserId(), checkJoinRoomRetMsg.isSucccess());
                    break;
                }
                case CheckExitRoomMsg.ID:{
                    CheckExitRoomMsg checkExitRoomMsg = (CheckExitRoomMsg) msg;
                    roomService.exitRoomSceneSuccess(checkExitRoomMsg.getUserId(), checkExitRoomMsg.getSceneId(), checkExitRoomMsg.isResult());
                    break;
                }
                case CheckDelRoomMsg.ID:{
                    CheckDelRoomMsg checkDelRoomMsg = (CheckDelRoomMsg) msg;
                    roomService.delRoomSceneSuccess(checkDelRoomMsg);
                    break;
                }
                case ChapterEndMsg.ID:{
                    ChapterEndMsg chapterEndMsg = (ChapterEndMsg) msg;
                    roomService.chapterEnd(chapterEndMsg);
                    break;
                }
                case RoomEndMsg.ID:{
                    RoomEndMsg endMsg = (RoomEndMsg) msg;
                    roomService.delRoom(endMsg.getCrateUserId(), null, true);
                    break;
                }
                case ChapterStartMsg.ID:{
                    ChapterStartMsg startMsg = (ChapterStartMsg) msg;
                    roomService.chapterStart(startMsg);
                    break;
                }
            }
        } catch (Throwable th) {
            log.error("严重消息错误 " + msg, th);
//            Scene scene = (Scene) msg.getSession().get();
//            if (sessionId > -1 && scene != null) {
////                Net.getInstance().closeSession(sessionId, SystemErrorCode.PROTOCAL_ERROR);
//                log.error("踢掉session:" + sessionId);
//                scene.unReg(sessionId);
//            }
        }
    }

    private Scene checkScene(PxMsg msg) {
        Session<Scene> sceneSession = msg.getSession();
        final Scene scene = sceneSession.get();
        if (scene == null) {
            log.error("未注册的Scene:{},sceneSession:{}", msg, sceneSession);
            throw new ServerRuntimeException("未注册的Scene:" + msg + ",sceneSession:" + sceneSession);
        }
        return scene;
    }
}
