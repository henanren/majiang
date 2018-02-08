package game.boss.handler;

import game.boss.model.User;
import game.boss.services.RoomService;
import mj.net.handler.login.JoinRoomHandler;
import mj.net.message.login.JoinRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zuoge85@gmail.com on 16/10/18.
 */
@Component
public class JoinRoomHandlerImpi implements JoinRoomHandler<User> {
    @Autowired
    private RoomService roomService;

    @Override
    public boolean handler(JoinRoom msg, User user) {
        roomService.joinRoom(msg.getRoomCheckId(), user);
        return false;
    }
}
