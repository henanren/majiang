package game.boss.msg;

import com.isnowfox.core.net.Session;
import com.isnowfox.game.proxy.message.PxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class RegSessionMsg implements PxMsg {
    public static final int ID = RegGatewayMsg.ID + 1;

    private short sessionId;
    private String ip;
    @SuppressWarnings("rawtypes")
    private Session session;

    public RegSessionMsg() {

    }

    @Override
    public void encode(ByteBuf out) throws Exception {
        out.writeShort(sessionId);
        byte[] bytes = ip.getBytes("utf8");
        out.writeShort(bytes.length);
        out.writeBytes(bytes);
    }

    @Override
    public void decode(ByteBuf in, ChannelHandlerContext ctx) throws Exception {
        sessionId = in.readShort();
        int len = in.readShort();
        byte[] bytes = new byte[len];
        in.readBytes(bytes);
        ip = new String(bytes, "utf8");
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


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public short getSessionId() {
        return sessionId;
    }

    public void setSessionId(short sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "RegSessionMsg [session=" + session
                + "]";
    }
}
