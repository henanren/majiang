package game.boss.msg;

import com.isnowfox.game.proxy.message.AbstractSessionPxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class ExitRoomMsg extends AbstractSessionPxMsg {
    public static final int ID = JoinRoomMsg.ID + 1;

    private short sessionId;
    private short sceneId;


    public ExitRoomMsg() {
        super(ID);
    }

    @Override
    public void encode(ByteBuf out) throws Exception {
        out.writeShort(sessionId);
        out.writeShort(sceneId);
    }

    @Override
    public void decode(ByteBuf in, ChannelHandlerContext chc) throws Exception {
        sessionId =  in.readShort();
        sceneId =  in.readShort();
    }

    public short getSessionId() {
        return sessionId;
    }

    public void setSessionId(short sessionId) {
        this.sessionId = sessionId;
    }

    public short getSceneId() {
        return sceneId;
    }

    public void setSceneId(short sceneId) {
        this.sceneId = sceneId;
    }




    @Override
    public String toString() {
        return "ExitRoomMsg{" +
                "sessionId=" + sessionId +
                ", sceneId=" + sceneId +
                '}';
    }
}
