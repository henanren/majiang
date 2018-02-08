package game.scene.msg;

import com.isnowfox.game.proxy.message.AbstractSessionPxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChapterEndMsg extends AbstractSessionPxMsg {
    public static final int ID = CheckOfflineRoomMsg.ID + 1;

    private int roomId;

    private Map<Integer, Integer> userScoreMap;

    public ChapterEndMsg() {
        super(ID);
    }


    @Override
    public void encode(ByteBuf out) throws Exception {
        out.writeInt(roomId);
        out.writeByte(userScoreMap.size());
        userScoreMap.forEach((userId, score) -> {
            out.writeInt(userId);
            out.writeInt(score);
        });
    }

    @Override
    public void decode(ByteBuf in, ChannelHandlerContext ctx) throws Exception {
        userScoreMap = new HashMap<>();

        roomId = in.readInt();
        byte size = in.readByte();
        for (int i = 0; i < size; i++) {
            int userId = in.readInt();
            int score = in.readInt();
            userScoreMap.put(userId,score);
        }
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Map<Integer, Integer> getUserScoreMap() {
        return userScoreMap;
    }

    public void setUserScoreMap(Map<Integer, Integer> userScoreMap) {
        this.userScoreMap = userScoreMap;
    }

    @Override
    public String toString() {
        return "ChapterEndMsg{" +
                "roomId=" + roomId +
                ", userScoreMap=" + userScoreMap +
                '}';
    }
}
