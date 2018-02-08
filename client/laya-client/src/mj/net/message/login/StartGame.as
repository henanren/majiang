package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 进入房间成功,游戏开始
	 * 前端收到这个消息开始发送命令加载 游戏信息,
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class StartGame implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=23;
		
		
		public function StartGame()
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
			return "StartGame [ ]";
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