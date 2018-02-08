package game.boss.msg;

import com.isnowfox.core.net.Session;
import com.isnowfox.game.proxy.message.LogoutPxMsg;
import com.isnowfox.game.proxy.message.PxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class RegGatewayMsg implements PxMsg {
	public static final int ID = LogoutPxMsg.ID + 1;

	private short gatewayId;
	@SuppressWarnings("rawtypes")
	private Session session;

	public RegGatewayMsg() {

	}

	public RegGatewayMsg(short gatewayId) {
		this.gatewayId = gatewayId;
	}

	public short getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(short gatewayId) {
		this.gatewayId = gatewayId;
	}


	@Override
	public void encode(ByteBuf out) throws Exception {
		out.writeShort(gatewayId);
	}

	@Override
	public void decode(ByteBuf in, ChannelHandlerContext ctx) throws Exception {
		gatewayId = in.readShort();
	}

	@Override
	public int getType() {
		return ID;
	}

	@SuppressWarnings("unchecked")
	public final <T> Session<T> getSession() {
		return session;
	}

	public final <T> void setSession(Session<T> session) {
		this.session = session;
	}

	@Override
	public String toString() {
		return "RegGatewayMsg{" +
				"gatewayId=" + gatewayId +
				", session=" + session +
				'}';
	}
}
