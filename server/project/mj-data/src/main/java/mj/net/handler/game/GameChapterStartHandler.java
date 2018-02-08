package mj.net.handler.game;

import mj.net.message.game.GameChapterStart;
import mj.net.handler.MessageHandler;

public interface GameChapterStartHandler <U> extends MessageHandler<GameChapterStart, U> {
	/**
	 * @return 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
	 */
	@Override
    boolean handler(GameChapterStart msg, U user);
}