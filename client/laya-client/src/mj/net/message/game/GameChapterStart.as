package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	public class GameChapterStart implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=1;
		
		
		public function GameChapterStart()
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
			return "GameChapterStart [ ]";
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