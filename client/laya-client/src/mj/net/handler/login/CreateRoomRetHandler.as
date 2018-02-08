package mj.net.handler.login
{
	import com.isnowfox.core.SingletonError;
	import com.isnowfox.core.io.message.Message;
	import com.isnowfox.core.io.message.MessageHandler;

	import mj.manager.UserManager;

	import mj.net.message.login.CreateRoomRet;
	
	/**
 	 * 创建房间结果
 	 * 
 	 * 消息处理器！
 	 * <b>生成器生成代码，生成后不会在覆盖!</b>
 	 * @author isnowfox消息生成器
 	 */
	public final class CreateRoomRetHandler implements MessageHandler
	{
		public static const instance:CreateRoomRetHandler = new CreateRoomRetHandler();
		
		public function CreateRoomRetHandler()
		{
			if(instance!=null){
				throw new SingletonError("ResourceManager 是单例模式");
			}
		}
		
		public function handler(msg:Message):Boolean
		{
			return inHandler(CreateRoomRet(msg));
		}
		
		/**
		 * @return 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
		 */
		private function inHandler(msg:CreateRoomRet):Boolean
		{
			UserManager.instance.createRet(msg.result, msg.roomCheckId);
			return false;
		}
	}
}