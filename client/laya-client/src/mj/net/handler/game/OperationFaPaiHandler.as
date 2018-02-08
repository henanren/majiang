package mj.net.handler.game
{
	import com.isnowfox.core.SingletonError;
	import com.isnowfox.core.io.message.Message;
	import com.isnowfox.core.io.message.MessageHandler;

	import mj.manager.GameManager;

	import mj.net.message.game.OperationFaPai;
	
	/**
 	 * 一局麻将的信息
 	 * 
 	 * 消息处理器！
 	 * <b>生成器生成代码，生成后不会在覆盖!</b>
 	 * @author isnowfox消息生成器
 	 */
	public final class OperationFaPaiHandler implements MessageHandler
	{
		public static const instance:OperationFaPaiHandler = new OperationFaPaiHandler();
		
		public function OperationFaPaiHandler()
		{
			if(instance!=null){
				throw new SingletonError("ResourceManager 是单例模式");
			}
		}
		
		public function handler(msg:Message):Boolean
		{
			return inHandler(OperationFaPai(msg));
		}
		
		/**
		 * @return 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
		 */
		private function inHandler(msg:OperationFaPai):Boolean
		{
			GameManager.instance.optFaPai(msg);
			return false;
		}
	}
}