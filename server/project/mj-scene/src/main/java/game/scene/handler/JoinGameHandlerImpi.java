package game.scene.handler;

import game.scene.room.SceneUser;
import mj.net.handler.game.GameJoinRoomHandler;
import mj.net.message.game.GameJoinRoom;
import org.springframework.stereotype.Component;

/**
 * @author zuoge85@gmail.com on 16/10/18.
 */
@Component
public class JoinGameHandlerImpi implements GameJoinRoomHandler<SceneUser> {
    @Override
    public boolean handler(GameJoinRoom msg, SceneUser user) {
        user.getRoom().joinGame(user);
        return false;
    }
}
