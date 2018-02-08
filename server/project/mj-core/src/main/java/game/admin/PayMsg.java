package game.admin;

import com.isnowfox.game.proxy.message.AbstractSessionPxMsg;
import com.isnowfox.game.proxy.message.LogoutPxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class PayMsg extends AbstractSessionPxMsg {
    public static final int ID = RegAdminMsg.ID + 1;

    private int userId;
    private int gold;

    public PayMsg() {
        super(ID);
    }

    public PayMsg(int userId, int gold) {
        this();
        this.userId = userId;
        this.gold = gold;
    }

    @Override
    public void encode(ByteBuf out) throws Exception {
        out.writeInt(userId);
        out.writeInt(gold);
    }

    @Override
    public void decode(ByteBuf in, ChannelHandlerContext ctx) throws Exception {
        userId = in.readInt();
        gold = in.readInt();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return "PayMsg{" +
                "userId=" + userId +
                ", gold=" + gold +
                '}';
    }
}
