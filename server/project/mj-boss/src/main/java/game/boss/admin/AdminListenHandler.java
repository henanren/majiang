package game.boss.admin;

import com.isnowfox.core.net.Session;
import com.isnowfox.game.proxy.PxMsgHandler;
import com.isnowfox.game.proxy.message.PxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import mj.net.message.MessageFactoryImpi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminListenHandler implements PxMsgHandler<Admin> {
    private static final Logger log = LoggerFactory.getLogger(AdminListenHandler.class);

    private AdminMessageManager messageManager;
    @Autowired
    private AdminManager AdminManager;

    public AdminListenHandler() {
    }

    @Override
    public void onConnect(Session<Admin> session) throws Exception {
        log.info("收到Admin连接!{}", session);
    }

    @Override
    public void onDisconnect(Session<Admin> session) throws Exception {
        if (session != null) {
            AdminManager.unreg(session.get());
        }
        log.info("Admin断开!{}", session);
    }

    @Override
    public void onException(Session<Admin> session, Throwable cause) throws Exception {
        log.error("错误，Admin网关！{}", session, cause);
        session.channel.close();
    }

    @Override
    public Session<Admin> createSession(ChannelHandlerContext ctx) throws Exception {
        return new Session<>(ctx.channel());
    }

    @Override
    public boolean onIn(Session<Admin> session, ByteBuf in) throws Exception {
        return true;
    }

    @Override
    public void onMessage(PxMsg msg) throws Exception {
        messageManager.handler(msg);
    }

    public void setMessageManager(AdminMessageManager messageManager) {
        this.messageManager = messageManager;
    }
}
