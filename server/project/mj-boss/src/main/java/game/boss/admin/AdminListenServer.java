package game.boss.admin;

import com.isnowfox.game.proxy.PxServer;
import game.admin.AdminPxMsgFactory;
import game.scene.msg.ScenePxMsgFactory;

import java.util.concurrent.ExecutionException;

/**
 * @author zuoge85@gmail.com on 16/9/27.
 */
public class AdminListenServer {
    public static final int BOSS_THREAD_NUMS = 1;
    public static final int WORKER_THREAD_NUMS = 1;

    private int port;

    private AdminListenHandler messageHandler;
    private PxServer pxServer;

    public void start() throws Exception {
        pxServer = PxServer.create(
                new AdminPxMsgFactory(), port, messageHandler, BOSS_THREAD_NUMS, WORKER_THREAD_NUMS
        );
        pxServer.start();
    }

    public void close() throws ExecutionException, InterruptedException {
        if(pxServer != null){
            pxServer.close();
        }
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setMessageHandler(AdminListenHandler messageHandler) {
        this.messageHandler = messageHandler;
    }
}
