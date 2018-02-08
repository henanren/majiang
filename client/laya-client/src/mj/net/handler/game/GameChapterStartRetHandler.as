package mj.net.handler.game
{
	import com.isnowfox.core.SingletonError;
	import com.isnowfox.core.io.message.Message;
	import com.isnowfox.core.io.message.MessageHandler;
	
	import mj.net.message.game.GameChapterStartRet;
    import mj.ui.dialog.Wait;

    public final class GameChapterStartRetHandler implements MessageHandler
	{
		public static const instance:GameChapterStartRetHandler = new GameChapterStartRetHandler();
		
		public function GameChapterStartRetHandler()
		{
			if(instance!=null){
				throw new SingletonError("ResourceManager 是单例模式");
			}
		}
		
		public function handler(msg:Message):Boolean
		{
			return inHandler(GameChapterStartRet(msg));
		}
		
		/**
		 * @return 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
		 */
		private function inHandler(msg:GameChapterStartRet):Boolean
		{
			Wait.close();
			return false;
		}
	}
}