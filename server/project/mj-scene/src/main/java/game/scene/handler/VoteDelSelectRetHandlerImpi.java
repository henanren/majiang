package game.scene.handler;

import game.scene.room.SceneUser;
import mj.net.handler.game.VoteDelSelectRetHandler;
import mj.net.message.game.VoteDelSelectRet;
import org.springframework.stereotype.Component;

/**
 * @author zuoge85@gmail.com on 2017/1/18.
 */
@Component
public class VoteDelSelectRetHandlerImpi  implements VoteDelSelectRetHandler<SceneUser> {


    @Override
    public boolean handler(VoteDelSelectRet msg, SceneUser user) {
        user.getRoom().voteDelSelect(msg, user);
        return false;
    }
}