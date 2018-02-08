package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 发送验证码回执
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class SendAuthCodeRet implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=22;
		
		public static function create(success:Boolean):SendAuthCodeRet
		{
			const sendAuthCodeRet:SendAuthCodeRet = new SendAuthCodeRet();
			sendAuthCodeRet._success = success;
			return sendAuthCodeRet;
		}
	
		protected var _success:Boolean;
		
		public function SendAuthCodeRet()
		{
		}
			
		public function decode(in0:Input):void
		{
			_success = in0.readBoolean();
		}
		
		public function encode(out:Output):void
		{
			out.writeBoolean(success);
		}
		

		public function get success():Boolean
		{
			return _success;
		}
		public function set success(success:Boolean):void
		{
			_success = success;
		}
		
		public function toString():String
		{
			return "SendAuthCodeRet [success=" + _success + ", ]";
		}
		
		public function getMessageType():int
		{
			return TYPE;
		}
		
		public function getMessageId():int
		{
			return ID;
		}
	}
}