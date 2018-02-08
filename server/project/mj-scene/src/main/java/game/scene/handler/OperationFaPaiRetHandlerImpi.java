package game.scene.handler;

import game.scene.room.SceneUser;
import mj.net.handler.game.OperationFaPaiRetHandler;
import mj.net.message.game.OperationFaPaiRet;
import org.springframework.stereotype.Component;

/**
 * @author zuoge85@gmail.com on 2016/10/31.
 */
@Component
public class OperationFaPaiRetHandlerImpi implements OperationFaPaiRetHandler<SceneUser> {
    @Override
    public boolean handler(OperationFaPaiRet msg, SceneUser user) {
        user.getRoom().faPaiRet(user, msg);
        return false;
    }
}
