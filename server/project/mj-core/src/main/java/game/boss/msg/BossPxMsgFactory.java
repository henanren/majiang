package game.boss.msg;

import com.isnowfox.game.proxy.PxMsgFactory;


public class BossPxMsgFactory extends PxMsgFactory {

    @Override
    protected void init() {
        super.init();
        super.add(RegGatewayMsg.ID, RegGatewayMsg.class);
        super.add(RegSessionMsg.ID, RegSessionMsg.class);
        super.add(JoinRoomMsg.ID, JoinRoomMsg.class);
        super.add(ExitRoomMsg.ID, ExitRoomMsg.class);
        super.add(DelRoomMsg.ID, DelRoomMsg.class);
    }
}
