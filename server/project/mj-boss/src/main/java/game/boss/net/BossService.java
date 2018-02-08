package game.boss.net;

import com.isnowfox.core.net.message.Message;
import game.boss.SceneUserInfo;
import game.boss.ServerRuntimeException;
import game.boss.dao.entity.RoomDO;
import game.boss.dao.entity.RoomUserDO;
import game.boss.model.Room;
import game.boss.model.User;
import game.boss.msg.DelRoomMsg;
import game.boss.msg.ExitRoomMsg;
import game.boss.msg.JoinRoomMsg;
import game.scene.msg.CheckDelRoomMsg;
import game.scene.msg.CheckExitRoomMsg;
import game.scene.msg.CheckOfflineRoomMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 暴露较为安全的管理接口
 *
 * @author zuoge85@gmail.com on 16/9/30.
 */
public class BossService {
    private static final Logger log = LoggerFactory.getLogger(BossService.class);

    @Autowired
    private GatewayManager gatewayManager;

    @Autowired
    private SceneManager sceneManager;

    public int getRandomSceneId() {
        return sceneManager.getRandomSceneId();
    }

    public void startJoinScene(User user, Room room, RoomUserDO roomUserDO, int sessionId) {
        RoomDO roomDO = room.getRoomDO();
        Scene scene = sceneManager.getScene(roomDO.getSceneId());
        if (scene == null) {
            throw new ServerRuntimeException("scene 为空, sceneId:" + roomDO.getSceneId());
        }

        sceneManager.send(roomDO.getSceneId(), room.toMsg(user));

        JoinRoomMsg msg = new JoinRoomMsg();
        msg.setSessionId(user.getSessionId());
        msg.setSceneId((short) roomDO.getSceneId());
        msg.setSceneAddress(scene.getAddress());
        msg.setScenePort(scene.getPort());

        user.sendToGateway(msg);
    }

    public void startOfflineScene(User user, Room room, int sessionId) {
        CheckOfflineRoomMsg m = new CheckOfflineRoomMsg();

        m.setUserId(user.getUserId());
        m.setRoomId(room.getRoomDO().getId());
        m.setJoinSessionId(sessionId);
        m.setJoinGatewayId((short) user.getGatewayId());
        sceneManager.send(room.getRoomDO().getSceneId(), m);
    }

    public void startExitRoomScene(User user, RoomDO room) {
        Scene scene = sceneManager.getScene(room.getSceneId());
        if (scene == null) {
            throw new ServerRuntimeException("scene 为空, sceneId:" + room.getSceneId());
        }

        CheckExitRoomMsg m = new CheckExitRoomMsg();
        m.setSceneId(scene.getId());
        m.setRoomId(room.getId());
        m.setUserId(user.getUserId());
        sceneManager.send(room.getSceneId(), m);
    }

    public void startExitRoomGateway(User user, int sceneId) {
        ExitRoomMsg msg = new ExitRoomMsg();
        msg.setSessionId(user.getSessionId());
        msg.setSceneId((short) sceneId);
        user.sendToGateway(msg);
    }


    public void startDelRoomScene(int UserId, RoomDO room, boolean isEnd) {
        Scene scene = sceneManager.getScene(room.getSceneId());
        if (scene == null) {
            throw new ServerRuntimeException("scene 为空, sceneId:" + room.getSceneId());
        }

        CheckDelRoomMsg m = new CheckDelRoomMsg();
        m.setSceneId((short) scene.getId());
        m.setRoomId(room.getId());
        m.setUserId(UserId);
        m.setEnd(isEnd);
        sceneManager.send(room.getSceneId(), m);
    }

    public void startDelRoomGateway(List<SceneUserInfo> infos) {
        for (SceneUserInfo userInfo : infos) {
            DelRoomMsg msg = new DelRoomMsg();
            msg.setSessionId(msg.getSessionId());
            msg.setGatewayId(msg.getGatewayId());
            msg.setUserId(msg.getUserId());
            gatewayManager.writeMsg(userInfo.getGatewayId(), msg);
        }
    }

    public void writeToAll(Message msg) {
        gatewayManager.writeToAll(msg);
    }
}
