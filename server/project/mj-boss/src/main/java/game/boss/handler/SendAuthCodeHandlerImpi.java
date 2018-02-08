package game.boss.handler;

import game.boss.model.User;
import game.boss.services.SmsService;
import mj.net.handler.login.SendAuthCodeHandler;
import mj.net.message.login.SendAuthCode;
import mj.net.message.login.SendAuthCodeRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zuoge85@gmail.com on 2016/11/22.
 */
@Component
public class SendAuthCodeHandlerImpi implements SendAuthCodeHandler<User> {
    @Autowired
    private SmsService smsService;

    @Override
    public boolean handler(SendAuthCode msg, User user) {
        smsService.authCode(msg.getPhone(), r -> {
            if (r != null) {
                if (r.contains("InvalidRecNum.Malformed")) {
                    r = "错误的手机号码！";
                } else if (r.contains("Frequency limit reaches")) {
                    r = "请求太频繁，请等待1分钟后重试！";
                }
                user.noticeError(r);
            }
            user.send(new SendAuthCodeRet(r == null));
        });
        return false;
    }
}
