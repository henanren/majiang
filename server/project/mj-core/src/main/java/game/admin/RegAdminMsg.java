package game.admin;

import com.isnowfox.game.proxy.message.AbstractSessionPxMsg;
import com.isnowfox.game.proxy.message.LogoutPxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class RegAdminMsg extends AbstractSessionPxMsg {
    public static final int ID = LogoutPxMsg.ID + 1;


    public RegAdminMsg() {
        super(ID);
    }



    @Override
    public void encode(ByteBuf out) throws Exception {

    }

    @Override
    public void decode(ByteBuf in, ChannelHandlerContext ctx) throws Exception {

    }
}
