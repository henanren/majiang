package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 退出房间
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class ExitRoom implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=4;
		
		
		public function ExitRoom()
		{
		}
			
		public function decode(in0:Input):void
		{
		}
		
		public function encode(out:Output):void
		{
		}
		
		
		public function toString():String
		{
			return "ExitRoom [ ]";
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