package game.boss.handler;

import game.boss.model.User;
import game.boss.services.RoomService;
import mj.net.handler.login.CreateRoomHandler;
import mj.net.message.login.CreateRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zuoge85@gmail.com on 16/10/3.
 */
@Component
public class CreateRoomHandlerImpi implements CreateRoomHandler<User> {
    @Autowired
    private RoomService roomService;

    @Override
    public boolean handler(CreateRoom msg, User user) {
        roomService.createRoom(msg, user);
        return false;
    }
}
