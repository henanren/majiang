package mj.net.handler.login;

import mj.net.message.login.RoomHistoryList;
import mj.net.handler.MessageHandler;

public interface RoomHistoryListHandler <U> extends MessageHandler<RoomHistoryList, U> {
	/**
	 * @return 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
	 */
	@Override
    boolean handler(RoomHistoryList msg, U user);
}