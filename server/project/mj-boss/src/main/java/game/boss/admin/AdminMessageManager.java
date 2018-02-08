package game.boss.admin;

import com.isnowfox.game.proxy.message.PxMsg;
import game.admin.PayMsg;
import game.admin.RegAdminMsg;
import game.admin.SettingMsg;
import game.boss.services.RoomService;
import game.boss.services.SettingService;
import game.boss.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zuoge85@gmail.com on 16/9/27.
 */
public class AdminMessageManager {
    private static final Logger log = LoggerFactory.getLogger(AdminMessageManager.class);

    @Autowired
    private AdminManager adminManager;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;
    @Autowired
    private SettingService settingService;

    void handler(PxMsg msg) {
        short sessionId = -1;
        try {
            switch (msg.getType()) {
                case RegAdminMsg.ID: {
                    RegAdminMsg regSceneMsg = (RegAdminMsg) msg;
                    log.info("注册Scene", regSceneMsg);
                    Admin admin = adminManager.reg(
                            msg.getSession().channel
                    );
                    if (admin != null) {
                        msg.getSession().set(admin);
                    }
                    break;
                }
                case PayMsg.ID: {
                    PayMsg payMsg = (PayMsg) msg;
                    Admin admin = adminManager.get(msg.getSession().channel);
                    msg.getSession().set(admin);

                    userService.pay(payMsg.getUserId(), payMsg.getGold());
                    break;
                }
                case SettingMsg.ID: {
                    SettingMsg payMsg = (SettingMsg) msg;
                    Admin admin = adminManager.get(msg.getSession().channel);
                    msg.getSession().set(admin);
                    settingService.change(payMsg);
                    break;
                }
            }
        } catch (Throwable th) {
            log.error("严重消息错误 " + msg, th);
        }
    }
}
