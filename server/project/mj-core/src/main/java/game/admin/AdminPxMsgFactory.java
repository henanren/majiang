package game.admin;

import com.isnowfox.game.proxy.PxMsgFactory;
import game.scene.msg.*;


public class AdminPxMsgFactory extends PxMsgFactory {
    @Override
    protected void init() {
        super.init();
        super.add(RegAdminMsg.ID, RegAdminMsg.class);
        super.add(PayMsg.ID, PayMsg.class);
        super.add(SettingMsg.ID, SettingMsg.class);
    }
}
