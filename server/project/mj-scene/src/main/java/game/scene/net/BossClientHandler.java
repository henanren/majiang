package game.scene.net;

import com.isnowfox.core.net.Session;
import com.isnowfox.game.proxy.PxMsgHandler;
import com.isnowfox.game.proxy.message.PxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class BossClientHandler implements PxMsgHandler<Object> {
    private final static Logger log = LoggerFactory.getLogger(BossClientHandler.class);

    @Autowired
    private SceneService sceneService;

    @Override
    public void onConnect(Session<Object> session) throws Exception {
        log.info("连接boss服务器成功！");
        sceneService.onBossConnect();
    }

    @Override
    public void onDisconnect(Session<Object> session) throws Exception {
        log.info("boss服务器断开！");
    }

    @Override
    public void onException(Session<Object> session, Throwable cause)
            throws Exception {
        log.info("错误!{}", session, cause);
    }

    @Override
    public Session<Object> createSession(ChannelHandlerContext ctx)
            throws Exception {
        return new Session<>(ctx.channel());
    }

    @Override
    public boolean onIn(Session<Object> session, ByteBuf in) throws Exception {
        return true;
    }

    @Override
    public void onMessage(PxMsg msg) throws Exception {
        sceneService.onBossMessage(msg);
    }
}
