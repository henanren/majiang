package game.scene.handler;

import game.scene.room.SceneUser;
import mj.net.handler.game.GameJoinRoomHandler;
import mj.net.handler.game.VoteDelStartHandler;
import mj.net.message.game.GameJoinRoom;
import mj.net.message.game.VoteDelStart;
import org.springframework.stereotype.Component;

/**
 * @author zuoge85@gmail.com on 2017/1/18.
 */
@Component
public class VoteDelStartHandlerImpi  implements VoteDelStartHandler<SceneUser> {

    @Override
    public boolean handler(VoteDelStart msg, SceneUser user) {
        user.getRoom().voteDelStart(msg, user);
        return false;
    }
}
