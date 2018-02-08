package game.boss.handler;

import game.boss.model.User;
import mj.net.handler.login.PingHandler;
import mj.net.message.login.Ping;
import mj.net.message.login.Pong;
import org.springframework.stereotype.Component;

/**
 * @author zuoge85@gmail.com on 16/10/3.
 */
@Component
public class PingHandlerImpi implements PingHandler<User> {
    @Override
    public boolean handler(Ping msg, User user) {
        user.send(new Pong(msg.getTime()));
        return false;
    }
}
