package game.boss.msg;

import com.isnowfox.game.proxy.message.AbstractSessionPxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class JoinRoomMsg extends AbstractSessionPxMsg {
    public static final int ID = RegSessionMsg.ID + 1;

    private short sessionId;
    private short sceneId;

    private String sceneAddress;
    private int scenePort;

    public JoinRoomMsg() {
        super(ID);
    }

    @Override
    public void encode(ByteBuf out) throws Exception {
        out.writeShort(sessionId);
        out.writeShort(sceneId);
        writeString(out, sceneAddress);
        out.writeInt(scenePort);
    }

    @Override
    public void decode(ByteBuf in, ChannelHandlerContext chc) throws Exception {
        sessionId =  in.readShort();
        sceneId =  in.readShort();
        sceneAddress = readString(in);
        scenePort = in.readInt();
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

    public String getSceneAddress() {
        return sceneAddress;
    }

    public void setSceneAddress(String sceneAddress) {
        this.sceneAddress = sceneAddress;
    }

    public int getScenePort() {
        return scenePort;
    }

    public void setScenePort(int scenePort) {
        this.scenePort = scenePort;
    }


    @Override
    public String toString() {
        return "JoinRoomMsg{" +
                "sessionId=" + sessionId +
                ", sceneId=" + sceneId +
                ", sceneAddress='" + sceneAddress + '\'' +
                ", scenePort=" + scenePort +
                '}';
    }
}
