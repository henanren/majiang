package game.gateway.server;

import com.isnowfox.core.net.NetPacketHandler;
import com.isnowfox.core.net.Session;
import com.isnowfox.core.net.message.Packet;
import com.isnowfox.game.platform.Platform;
import com.isnowfox.game.platform.User;
import game.gateway.GatewayService;
import game.gateway.SessionService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class GameServerHandler implements NetPacketHandler<User> {
    private final static Logger log = LoggerFactory.getLogger(GameServerHandler.class);

    @Autowired
    private SessionService sessionService;

    @Autowired
    private Platform platform;

    @Autowired
    private GatewayService gatewayService;

    public GameServerHandler() {
    }

    @Override
    public void onConnect(Session<User> session) throws Exception {
    }

    @Override
    public void onDisconnect(Session<User> session) throws Exception {
        if (session != null) {
            sessionService.unReg(session.get());
        }
    }

    @Override
    public void onException(Session<User> session, Throwable cause)
            throws Exception {
        if (cause instanceof IOException) {
            log.error("错误，断开玩家{},{}", session, cause.getMessage());
        } else {
            log.error("错误，断开玩家{}", session, cause);
        }
        session.channel.close();
    }

    @Override
    public Session<User> createSession(ChannelHandlerContext ctx)
            throws Exception {
        Session<User> session = sessionService.reg(ctx);
        gatewayService.onCreateSession(ctx, session);
        return session;
    }

    @Override
    public boolean onIn(Session<User> session, ByteBuf in) throws Exception {
        return platform.onIn(in, session.get());
    }

    @Override
    public void onPacket(Packet msg) throws Exception {
        log.info("收到消息内容:{},{}", msg.getBuf().toString(), ByteBufUtil.hexDump(msg.getBuf()));
        gatewayService.handlerClient(msg);
    }
}
