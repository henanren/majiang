package game.gateway.server;

import com.isnowfox.game.proxy.PxClient;
import game.boss.msg.BossPxMsgFactory;
import io.netty.channel.ChannelFuture;

import java.util.concurrent.ExecutionException;

/**
 * @author zuoge85@gmail.com on 16/9/26.
 */
public final class SceneClient {
    public static final int THREAND_NUMS = 1;
    private final PxClient pxClient;
    private boolean isConnect = false;

    public SceneClient(String sceneAddress, int scenePort, SceneClientHandler sceneClientHandler) throws Exception {
        pxClient = PxClient.create(new BossPxMsgFactory(), sceneAddress, scenePort, sceneClientHandler, THREAND_NUMS);
    }

    public void connect() throws Exception {
        pxClient.connectRetry();
    }

    public void close() throws ExecutionException, InterruptedException {
        pxClient.close();
    }

    public void writeAndFlush(Object msg) {
        pxClient.writeAndFlush(msg);
    }

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }
}
