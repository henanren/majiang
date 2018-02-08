package game.scene.handler;

import game.scene.room.SceneUser;
import mj.net.handler.game.GameChapterStartHandler;
import mj.net.message.game.GameChapterStart;
import org.springframework.stereotype.Component;

/**
 * @author zuoge85@gmail.com on 2016/10/24.
 */
@Component
public class GameChapterStartHandlerImpi implements GameChapterStartHandler<SceneUser> {
    @Override
    public boolean handler(GameChapterStart msg, SceneUser user) {
        user.getRoom().chapterStart(user);
        return false;
    }
}
