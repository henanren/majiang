package game.boss.net;

import com.isnowfox.core.net.Session;
import com.isnowfox.game.proxy.PxMsgHandler;
import com.isnowfox.game.proxy.message.PxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import mj.net.message.MessageFactoryImpi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GatewayListenHandler implements PxMsgHandler<Gateway> {
    private static final Logger log = LoggerFactory.getLogger(GatewayListenHandler.class);

    private final MessageFactoryImpi messageFactory = MessageFactoryImpi.getInstance();
    // private final int gatewayUserMaxConnect;

    private MessageManager messageManager;
    @Autowired
    private GatewayManager gatewayManager;

    public GatewayListenHandler() {
    }

    @Override
    public void onConnect(Session<Gateway> session) throws Exception {
        log.info("收到网关连接!{}", session);
    }

    @Override
    public void onDisconnect(Session<Gateway> session) throws Exception {
        if (session != null) {
            gatewayManager.unreg(session.get());
        }
        log.info("网关断开!{}", session);
    }

    @Override
    public void onException(Session<Gateway> session, Throwable cause) throws Exception {
        log.error("错误，断开网关！{}", session, cause);
        session.channel.close();
    }

    @Override
    public Session<Gateway> createSession(ChannelHandlerContext ctx) throws Exception {
        return new Session<>(ctx.channel());
    }

    @Override
    public boolean onIn(Session<Gateway> session, ByteBuf in) throws Exception {
        return true;
    }

    @Override
    public void onMessage(PxMsg msg) throws Exception {
        messageManager.handlerGatewayMessage(msg);
    }

    public void setMessageManager(MessageManager messageManager) {
        this.messageManager = messageManager;
    }
}
