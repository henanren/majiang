package game.scene.msg;

import com.isnowfox.game.proxy.message.AbstractSessionPxMsg;
import com.isnowfox.game.proxy.message.LogoutPxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class RegSceneMsg extends AbstractSessionPxMsg {
    public static final int ID = LogoutPxMsg.ID + 1;

    private short sceneId;

    private String sceneAddress;
    private int scenePort;

    public RegSceneMsg() {
        super(ID);
    }


    public RegSceneMsg(short sceneId, String sceneAddress, int scenePort) {
        this();
        this.sceneId = sceneId;
        this.sceneAddress = sceneAddress;
        this.scenePort = scenePort;
    }

    @Override
    public void encode(ByteBuf out) throws Exception {
        out.writeShort(sceneId);
        writeString(out, sceneAddress);
        out.writeInt(scenePort);
    }

    @Override
    public void decode(ByteBuf in, ChannelHandlerContext ctx) throws Exception {
        sceneId = in.readShort();
        sceneAddress = readString(in);
        scenePort = in.readInt();
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
        return "RegSceneMsg{" +
                "sceneId=" + sceneId +
                ", sceneAddress='" + sceneAddress + '\'' +
                ", scenePort=" + scenePort +
                '}';
    }
}
