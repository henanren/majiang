package mj.net.handler.login
{
	import com.isnowfox.core.SingletonError;
	import com.isnowfox.core.io.message.Message;
	import com.isnowfox.core.io.message.MessageHandler;
	
	import mj.net.message.login.SendAuthCodeRet;
	import mj.ui.dialog.PhoneDialog;

	/**
 	 * 发送验证码回执
 	 * 
 	 * 消息处理器！
 	 * <b>生成器生成代码，生成后不会在覆盖!</b>
 	 * @author isnowfox消息生成器
 	 */
	public final class SendAuthCodeRetHandler implements MessageHandler
	{
		public static const instance:SendAuthCodeRetHandler = new SendAuthCodeRetHandler();
		
		public function SendAuthCodeRetHandler()
		{
			if(instance!=null){
				throw new SingletonError("ResourceManager 是单例模式");
			}
		}
		
		public function handler(msg:Message):Boolean
		{
			return inHandler(SendAuthCodeRet(msg));
		}
		
		/**
		 * @return 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
		 */
		private function inHandler(msg:SendAuthCodeRet):Boolean
		{
			if(PhoneDialog.phoneDialog){
				PhoneDialog.phoneDialog.sendRet(msg.success);
			}
			return false;
		}
	}
}