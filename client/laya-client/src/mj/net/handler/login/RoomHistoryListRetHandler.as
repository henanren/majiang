package mj.net.handler.login
{
	import com.isnowfox.core.SingletonError;
	import com.isnowfox.core.io.message.Message;
	import com.isnowfox.core.io.message.MessageHandler;
	
	import mj.net.message.login.RoomHistoryListRet;
    import mj.ui.dialog.RoomHistoryListDialog;
    import mj.ui.dialog.Wait;

    public final class RoomHistoryListRetHandler implements MessageHandler
	{
		public static const instance:RoomHistoryListRetHandler = new RoomHistoryListRetHandler();
		
		public function RoomHistoryListRetHandler()
		{
			if(instance!=null){
				throw new SingletonError("ResourceManager 是单例模式");
			}
		}
		
		public function handler(msg:Message):Boolean
		{
			return inHandler(RoomHistoryListRet(msg));
		}
		
		/**
		 * @return 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
		 */
		private function inHandler(msg:RoomHistoryListRet):Boolean
		{
			Wait.close();
			RoomHistoryListDialog.showDialog(msg);
			return false;
		}
	}
}