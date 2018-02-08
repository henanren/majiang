package game.scene.msg;

import com.isnowfox.game.proxy.message.AbstractSessionPxMsg;
import game.boss.SceneUserInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

public class CheckDelRoomMsg extends AbstractSessionPxMsg {
    public static final int ID = CheckExitRoomMsg.ID + 1;

    private short sceneId;
    /**
     * 牌局id
     */
    private int roomId;

    private int userId;
    private boolean result;


    private List<SceneUserInfo> infos = new ArrayList<>();
    private boolean isEnd;


    public CheckDelRoomMsg() {
        super(ID);
    }


    @Override
    public void encode(ByteBuf out) throws Exception {
        out.writeShort(sceneId);
        /**牌局id*/
        out.writeInt(roomId);
        /**用户id*/
        out.writeInt(userId);

        out.writeBoolean(result);
        out.writeBoolean(isEnd);

        out.writeByte(infos.size());

        for (int i = 0; i < infos.size(); i++) {
            SceneUserInfo item = infos.get(i);
            item.encode(out);
        }
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

        result = in.readBoolean();
        isEnd = in.readBoolean();

        int len = in.readByte();
        for (int i = 0; i < len; i++) {
            SceneUserInfo item = new SceneUserInfo();
            item.decode(in, ctx);
            infos.add(item);
        }
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<SceneUserInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<SceneUserInfo> infos) {
        this.infos = infos;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    public String toString() {
        return "CheckDelRoomMsg{" +
                "sceneId=" + sceneId +
                ", roomId=" + roomId +
                ", userId=" + userId +
                ", result=" + result +
                ", infos=" + infos +
                ", isEnd=" + isEnd +
                '}';
    }
}
