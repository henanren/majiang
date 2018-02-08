package mj.net.handler.game
{
	import com.isnowfox.core.SingletonError;
	import com.isnowfox.core.io.message.Message;
	import com.isnowfox.core.io.message.MessageHandler;

	import mj.manager.GameManager;

	import mj.net.message.game.SyncOptTime;
	
	/**
 	 * 同步操作
 	 * 
 	 * 消息处理器！
 	 * <b>生成器生成代码，生成后不会在覆盖!</b>
 	 * @author isnowfox消息生成器
 	 */
	public final class SyncOptTimeHandler implements MessageHandler
	{
		public static const instance:SyncOptTimeHandler = new SyncOptTimeHandler();
		
		public function SyncOptTimeHandler()
		{
			if(instance!=null){
				throw new SingletonError("ResourceManager 是单例模式");
			}
		}
		
		public function handler(msg:Message):Boolean
		{
			return inHandler(SyncOptTime(msg));
		}
		
		/**
		 * @return 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
		 */
		private function inHandler(msg:SyncOptTime):Boolean
		{
			GameManager.instance.syncOptTime(msg);
			return false;
		}
	}
}