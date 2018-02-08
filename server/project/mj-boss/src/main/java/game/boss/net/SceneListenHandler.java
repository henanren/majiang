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

public class SceneListenHandler implements PxMsgHandler<Scene> {
    private static final Logger log = LoggerFactory.getLogger(SceneListenHandler.class);

    private final MessageFactoryImpi messageFactory = MessageFactoryImpi.getInstance();

    private SceneMessageManager messageManager;
    @Autowired
    private SceneManager sceneManager;

    public SceneListenHandler() {
    }

    @Override
    public void onConnect(Session<Scene> session) throws Exception {
        log.info("收到Scene连接!{}", session);
    }

    @Override
    public void onDisconnect(Session<Scene> session) throws Exception {
        if (session != null) {
            sceneManager.unreg(session.get());
        }
        log.info("Scene断开!{}", session);
    }

    @Override
    public void onException(Session<Scene> session, Throwable cause) throws Exception {
        log.error("错误，Scene网关！{}", session, cause);
        session.channel.close();
    }

    @Override
    public Session<Scene> createSession(ChannelHandlerContext ctx) throws Exception {
        return new Session<>(ctx.channel());
    }

    @Override
    public boolean onIn(Session<Scene> session, ByteBuf in) throws Exception {
        return true;
    }

    @Override
    public void onMessage(PxMsg msg) throws Exception {
        messageManager.handler(msg);
    }

    public void setMessageManager(SceneMessageManager messageManager) {
        this.messageManager = messageManager;
    }
}
