package game.scene.msg;

import com.isnowfox.game.proxy.message.AbstractSessionPxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;
import java.util.Map;

/**
 * @author zuoge85@gmail.com on 2017/1/1.
 */
public class CheckJoinRoomRetMsg extends AbstractSessionPxMsg {
    public static final int ID = ChapterEndMsg.ID + 1;
    private short sceneId;
    /**
     * 牌局id
     */
    private int roomId;
    private int joinSessionId;
    private int joinGatewayId;
    private int joinUserId;
    private boolean succcess;

    public CheckJoinRoomRetMsg() {
        super(ID);
    }


    @Override
    public void encode(ByteBuf out) throws Exception {
        out.writeShort(sceneId);
        /**牌局id*/
        out.writeInt(roomId);


        out.writeInt(joinGatewayId);
        out.writeInt(joinSessionId);
        out.writeInt(joinUserId);
        out.writeBoolean(succcess);
    }

    @Override
    public void decode(ByteBuf in, ChannelHandlerContext ctx) throws Exception {
        sceneId = in.readShort();
        /**
         * 牌局id
         */
        roomId = in.readInt();


        joinGatewayId = in.readInt();
        joinSessionId = in.readInt();
        joinUserId = in.readInt();

        succcess = in.readBoolean();
    }

    public short getSceneId() {
        return sceneId;
    }

    public void setSceneId(short sceneId) {
        this.sceneId = sceneId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getJoinSessionId() {
        return joinSessionId;
    }

    public void setJoinSessionId(int joinSessionId) {
        this.joinSessionId = joinSessionId;
    }

    public int getJoinGatewayId() {
        return joinGatewayId;
    }

    public void setJoinGatewayId(int joinGatewayId) {
        this.joinGatewayId = joinGatewayId;
    }

    public int getJoinUserId() {
        return joinUserId;
    }

    public void setJoinUserId(int joinUserId) {
        this.joinUserId = joinUserId;
    }

    public boolean isSucccess() {
        return succcess;
    }

    public void setSucccess(boolean succcess) {
        this.succcess = succcess;
    }

    @Override
    public String toString() {
        return "CheckJoinRoomRetMsg{" +
                "sceneId=" + sceneId +
                ", roomId=" + roomId +
                ", joinSessionId=" + joinSessionId +
                ", joinGatewayId=" + joinGatewayId +
                ", joinUserId=" + joinUserId +
                ", succcess=" + succcess +
                '}';
    }
}
