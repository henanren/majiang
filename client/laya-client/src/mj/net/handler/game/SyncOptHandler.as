package mj.net.handler.game
{
	import com.isnowfox.core.SingletonError;
	import com.isnowfox.core.io.message.Message;
	import com.isnowfox.core.io.message.MessageHandler;

	import mj.manager.GameManager;

	import mj.net.message.game.SyncOpt;
	
	/**
 	 * 同步操作
	 * OUT:打牌,CHI:吃,PENG:碰,AN_GANG:暗杠牌,XIAO_MING_GANG:暗杠牌,DA_MING_GANG:暗杠牌,HU:胡牌
 	 * 
 	 * 消息处理器！
 	 * <b>生成器生成代码，生成后不会在覆盖!</b>
 	 * @author isnowfox消息生成器
 	 */
	public final class SyncOptHandler implements MessageHandler
	{
		public static const instance:SyncOptHandler = new SyncOptHandler();
		
		public function SyncOptHandler()
		{
			if(instance!=null){
				throw new SingletonError("ResourceManager 是单例模式");
			}
		}
		
		public function handler(msg:Message):Boolean
		{
			return inHandler(SyncOpt(msg));
		}
		
		/**
		 * @return 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
		 */
		private function inHandler(msg:SyncOpt):Boolean
		{
			GameManager.instance.syncOpt(msg);
			return false;
		}
	}
}