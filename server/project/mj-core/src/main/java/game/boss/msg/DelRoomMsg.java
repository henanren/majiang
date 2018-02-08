package game.boss.msg;

import com.isnowfox.game.proxy.message.AbstractSessionPxMsg;
import game.boss.SceneUserInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

public class DelRoomMsg extends AbstractSessionPxMsg {
    public static final int ID = ExitRoomMsg.ID + 1;

    private short sessionId;
    private int gatewayId;
    private int userId;

    public DelRoomMsg() {
        super(ID);
    }

    @Override
    public void encode(ByteBuf out) throws Exception {
        out.writeShort(sessionId);
        out.writeInt(gatewayId);
        out.writeInt(userId);
    }

    @Override
    public void decode(ByteBuf in, ChannelHandlerContext chc) throws Exception {
        sessionId = in.readShort();
        gatewayId = in.readInt();
        userId = in.readInt();
    }


    public short getSessionId() {
        return sessionId;
    }

    public void setSessionId(short sessionId) {
        this.sessionId = sessionId;
    }

    public int getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(int gatewayId) {
        this.gatewayId = gatewayId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DelRoomMsg{" +
                "sessionId=" + sessionId +
                ", gatewayId=" + gatewayId +
                ", userId=" + userId +
                '}';
    }
}
