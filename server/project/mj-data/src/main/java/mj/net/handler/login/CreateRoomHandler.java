package mj.net.handler.login;

import mj.net.message.login.CreateRoom;
import mj.net.handler.MessageHandler;

/**
 * 创建房间
 * 
 * 消息处理器接口
 * 添加属性回被spring注入！默认注入为类型注入！
 * <b>生成器生成代码!</b>
 * @author isnowfox消息生成器
 */
public interface CreateRoomHandler <U> extends MessageHandler<CreateRoom, U> {
	/**
	 * @return 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
	 */
	@Override
    boolean handler(CreateRoom msg, U user);
}