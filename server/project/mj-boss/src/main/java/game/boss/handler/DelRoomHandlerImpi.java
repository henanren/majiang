package game.boss.handler;

import game.boss.model.User;
import game.boss.services.RoomService;
import mj.net.handler.login.DelRoomHandler;
import mj.net.message.login.DelRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zuoge85@gmail.com on 2016/10/23.
 */
@Component
public class DelRoomHandlerImpi implements DelRoomHandler<User> {
    @Autowired
    private RoomService roomService;

    @Override
    public boolean handler(DelRoom msg, User user) {
        roomService.delRoom(user.getUserId(), user, false);
        return false;
    }
}
