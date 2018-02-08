package mj.net.handler.login
{
	import com.isnowfox.core.SingletonError;
	import com.isnowfox.core.io.message.Message;
	import com.isnowfox.core.io.message.MessageHandler;

	import mj.GlobalResource;

	import mj.net.message.login.Notice;
	import mj.ui.dialog.Dialog;
	import mj.ui.dialog.Wait;

	/**
 	 * 通知
 	 * 
 	 * 消息处理器！
 	 * <b>生成器生成代码，生成后不会在覆盖!</b>
 	 * @author isnowfox消息生成器
 	 */
	public final class NoticeHandler implements MessageHandler
	{
		public static const instance:NoticeHandler = new NoticeHandler();
		
		public function NoticeHandler()
		{
			if(instance!=null){
				throw new SingletonError("ResourceManager 是单例模式");
			}
		}
		
		public function handler(msg:Message):Boolean
		{
			return inHandler(Notice(msg));
		}
		
		/**
		 * @return 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
		 */
		private function inHandler(msg:Notice):Boolean
		{
			var message:String = GlobalResource.instance.locale.format(msg.key, msg.args);
			if(msg.key == "room.notEnoughUser"){
				Wait.close();
			}
			Dialog.showMessage(message);
			return false;
		}
	}
}