package game.scene.handler;

import game.scene.room.SceneUser;
import mj.net.handler.game.OperationFaPaiRetHandler;
import mj.net.handler.game.OperationOutRetHandler;
import mj.net.message.game.OperationFaPaiRet;
import mj.net.message.game.OperationOutRet;
import org.springframework.stereotype.Component;

/**
 * @author zuoge85@gmail.com on 2016/10/31.
 */
@Component
public class OperationOutRetHandlerImpi implements OperationOutRetHandler<SceneUser> {
    @Override
    public boolean handler(OperationOutRet msg, SceneUser user) {
        user.getRoom().outRet(user, msg);
        return false;
    }
}
