package game.scene.net;

import com.isnowfox.game.proxy.PxMsgHandler;
import com.isnowfox.game.proxy.PxServer;
import game.boss.msg.BossPxMsgFactory;

import java.util.concurrent.ExecutionException;

/**
 * @author zuoge85@gmail.com on 16/9/27.
 */
public class SceneServer {
    public static final int BOSS_THREAD_NUMS = 2;
    public static final int WORKER_THREAD_NUMS = 4;

    private int port;

    private SceneHandler messageHandler;
    private PxServer pxServer;

    public void start() throws Exception {
        pxServer = PxServer.create(new BossPxMsgFactory(), port, messageHandler, BOSS_THREAD_NUMS, WORKER_THREAD_NUMS);
        pxServer.start();
    }

    public void close() throws ExecutionException, InterruptedException {
        pxServer.close();
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setMessageHandler(SceneHandler messageHandler) {
        this.messageHandler = messageHandler;
    }
}
