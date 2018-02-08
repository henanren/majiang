package mj.net.handler.login
{
	import com.isnowfox.core.SingletonError;
	import com.isnowfox.core.io.message.Message;
	import com.isnowfox.core.io.message.MessageHandler;

    import mj.manager.UiManager;

    import mj.net.message.login.Radio;
	
	public final class RadioHandler implements MessageHandler
	{
		public static const instance:RadioHandler = new RadioHandler();
		
		public function RadioHandler()
		{
			if(instance!=null){
				throw new SingletonError("ResourceManager 是单例模式");
			}
		}
		
		public function handler(msg:Message):Boolean
		{
			return inHandler(Radio(msg));
		}
		
		/**
		 * @return 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
		 */
		private function inHandler(msg:Radio):Boolean
		{
			UiManager.instance.changeRadio(msg.radio);
			return false;
		}
	}
}