package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 准备就绪,通知服务器可以开始发送房间信息了
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class GameJoinRoom implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=6;
		
		
		public function GameJoinRoom()
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
			return "GameJoinRoom [ ]";
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