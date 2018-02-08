package game.boss.handler;

import game.boss.dao.dao.UserDao;
import game.boss.dao.entity.UserDO;
import game.boss.model.User;
import game.boss.services.AsyncDbService;
import game.boss.services.UserService;
import mj.net.handler.login.LoginHandler;
import mj.net.message.login.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author zuoge85@gmail.com on 16/9/27.
 */
@Component
public class LoginHandlerImpi implements LoginHandler<User> {
    @Autowired
    private UserService userService;

    @Override
    public boolean handler(Login msg, User user) {
        userService.loginByOpenId(msg, user);
        return false;
    }
}
