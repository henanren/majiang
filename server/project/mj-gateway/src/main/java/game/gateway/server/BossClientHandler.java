package game.gateway.server;

import game.gateway.GatewayService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isnowfox.core.net.Session;
import com.isnowfox.game.proxy.PxMsgHandler;
import com.isnowfox.game.proxy.message.PxMsg;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class  BossClientHandler implements PxMsgHandler<Object>{
	private final static Logger log = LoggerFactory.getLogger(GameServerHandler.class);

	private GatewayService gatewayService;

	@Override
	public void onConnect(Session<Object> session) throws Exception {
		log.info("连接boss服务器成功！");
		gatewayService.onBossConnect();
	}

	@Override
	public void onDisconnect(Session<Object> session) throws Exception {
		log.info("boss连接断开!！");
		gatewayService.onBossDisconnect();
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
		gatewayService.handlerBoss(msg);
	}

	public void setGatewayService(GatewayService gatewayService) {
		this.gatewayService = gatewayService;
	}
}
