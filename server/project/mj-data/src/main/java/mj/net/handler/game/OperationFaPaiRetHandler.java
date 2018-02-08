package mj.net.handler.game;

import mj.net.message.game.OperationFaPaiRet;
import mj.net.handler.MessageHandler;

/**
 * 自己的发牌的信息
 * 
 * 消息处理器接口
 * 添加属性回被spring注入！默认注入为类型注入！
 * <b>生成器生成代码!</b>
 * @author isnowfox消息生成器
 */
public interface OperationFaPaiRetHandler <U> extends MessageHandler<OperationFaPaiRet, U> {
	/**
	 * @return 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
	 */
	@Override
    boolean handler(OperationFaPaiRet msg, U user);
}