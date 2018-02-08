package game.scene.msg;

import com.isnowfox.game.proxy.message.AbstractSessionPxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

public class ChapterStartMsg extends AbstractSessionPxMsg {
    public static final int ID = RoomEndMsg.ID + 1;

    private int roomId;


    public ChapterStartMsg() {
        super(ID);
    }


    @Override
    public void encode(ByteBuf out) throws Exception {
        out.writeInt(roomId);

    }

    @Override
    public void decode(ByteBuf in, ChannelHandlerContext ctx) throws Exception {
        roomId = in.readInt();
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "ChapterEndMsg{" +
                "roomId=" + roomId +
                '}';
    }
}
