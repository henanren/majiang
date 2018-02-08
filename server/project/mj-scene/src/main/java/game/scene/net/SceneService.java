package game.scene.net;

import com.isnowfox.core.net.message.Message;
import com.isnowfox.game.proxy.message.PxMsg;
import game.scene.msg.*;
import game.scene.net.BossClient;
import game.scene.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 暴露net包内 的较为安全的方法
 * @author zuoge85@gmail.com on 16/10/5.
 */
@Service
public class SceneService {
    @Value("${sceneId}")
    private int sceneId;

    @Value("${address}")
    private String address;

    @Value("${port}")
    private int port;

    @Autowired
    private BossClient bossClient;
    @Autowired
    private RoomService roomService;
    @Autowired GatewayManager gatewayManager;

    public void onBossConnect() {
        bossClient.writeAndFlush(new RegSceneMsg((short) sceneId, address, port));
    }

    public void onBossMessage(PxMsg msg) {
        switch (msg.getType()) {
            case CheckJoinRoomMsg.ID: {
                roomService.checkJoinRoom((CheckJoinRoomMsg) msg);
                break;
            }
            case CheckOfflineRoomMsg.ID: {
                roomService.checkOfflineRoom((CheckOfflineRoomMsg) msg);
                break;
            }
            case CheckExitRoomMsg.ID: {
                roomService.checkExitRoom((CheckExitRoomMsg) msg);
                break;
            }
            case CheckDelRoomMsg.ID: {
                roomService.checkDelRoom((CheckDelRoomMsg) msg);
                break;
            }
        }
    }

    public void sendMessage(int gatewayId, int sessionId, Message msg) {
        gatewayManager.sendMessage(gatewayId, sessionId, msg);
    }
}
