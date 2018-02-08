package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	public class GameChapterStartRet implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=2;
		
		
		public function GameChapterStartRet()
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
			return "GameChapterStartRet [ ]";
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