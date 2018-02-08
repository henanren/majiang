package game.scene.handler;

import game.scene.room.SceneUser;
import mj.net.handler.game.OperationCPGHRetHandler;
import mj.net.handler.game.OperationFaPaiRetHandler;
import mj.net.message.game.OperationCPGHRet;
import mj.net.message.game.OperationFaPaiRet;
import org.springframework.stereotype.Component;

/**
 * @author zuoge85@gmail.com on 2016/10/31.
 */
@Component
public class OperationCPGHRetHandlerImpi implements OperationCPGHRetHandler<SceneUser> {
    @Override
    public boolean handler(OperationCPGHRet msg, SceneUser user) {
        user.getRoom().cpghRet(user, msg);
        return false;
    }
}
