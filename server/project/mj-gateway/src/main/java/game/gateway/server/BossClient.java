package game.gateway.server;

import com.isnowfox.game.proxy.PxClient;
import game.boss.msg.BossPxMsgFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import java.util.concurrent.ExecutionException;

/**
 * @author zuoge85@gmail.com on 16/9/26.
 */
public final class BossClient {
    public static final int THREAND_NUMS = 1;
    private final PxClient pxClient;

    public BossClient(String bossAddress, int bossPort, BossClientHandler bossClientHandler) throws Exception {
        pxClient = PxClient.create(new BossPxMsgFactory(), bossAddress, bossPort, bossClientHandler, THREAND_NUMS);
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
}
