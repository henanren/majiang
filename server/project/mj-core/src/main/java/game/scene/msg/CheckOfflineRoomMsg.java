package game.scene.msg;

import com.isnowfox.game.proxy.message.AbstractSessionPxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class CheckOfflineRoomMsg extends AbstractSessionPxMsg {
    public static final int ID = CheckDelRoomMsg.ID + 1;

    private int sceneId;
    /**
     * 牌局id
     */
    private int roomId;

    private int userId;

    private int joinSessionId;
    private int joinGatewayId;

    public CheckOfflineRoomMsg() {
        super(ID);
    }


    @Override
    public void encode(ByteBuf out) throws Exception {
        out.writeShort(sceneId);
        /**牌局id*/
        out.writeInt(roomId);
        /**用户id*/
        out.writeInt(userId);

        out.writeInt(joinSessionId);
        out.writeInt(joinGatewayId);

    }

    @Override
    public void decode(ByteBuf in, ChannelHandlerContext ctx) throws Exception {
        sceneId = in.readShort();
        /**
         * 牌局id
         */
        roomId = in.readInt();
        /**
         * 创建用户id
         */
        userId = in.readInt();

        joinSessionId = in.readInt();
        joinGatewayId = in.readInt();
    }

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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


    @Override
    public String toString() {
        return "CheckExitRoomMsg{" +
                "sceneId=" + sceneId +
                ", roomId=" + roomId +
                ", userId=" + userId +
                ", joinSessionId=" + joinSessionId +
                ", joinGatewayId=" + joinGatewayId +
                '}';
    }
}
