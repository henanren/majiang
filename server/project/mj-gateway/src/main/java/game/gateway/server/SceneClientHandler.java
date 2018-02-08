package game.gateway.server;

import com.isnowfox.core.net.Session;
import com.isnowfox.game.proxy.PxMsgHandler;
import com.isnowfox.game.proxy.message.PxMsg;
import game.gateway.GatewayService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class SceneClientHandler implements PxMsgHandler<Object> {
    private final static Logger log = LoggerFactory.getLogger(GameServerHandler.class);

    @Autowired
    private GatewayService gatewayService;

    private Runnable connectCallback;

    private SceneClient client;

    public SceneClientHandler(Runnable connectCallback) {
        this.connectCallback = connectCallback;
    }

    /**
     * TODO: 这儿有个风险,就是连接上了场景服务器,但是还没有注册成功!!,虽然发生问题的可能性较小,还是应该重构!
     */
    @Override
    public void onConnect(Session<Object> session) throws Exception {
        log.info("连接场景服务器成功！");
        gatewayService.onSceneConnect(client);
        connectCallback.run();
    }

    @Override
    public void onDisconnect(Session<Object> session) throws Exception {
        log.info("场景连接断开!！");
        gatewayService.onSceneDisconnect(client);
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
        gatewayService.handlerScene(msg);
    }

    public void setClient(SceneClient client) {
        this.client = client;
    }
}
