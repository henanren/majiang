package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 解散了房间，房间被删除
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class GameDelRoom implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=3;
		
		public static function create(isEnd:Boolean, isStart:Boolean):GameDelRoom
		{
			const gameDelRoom:GameDelRoom = new GameDelRoom();
			gameDelRoom._isEnd = isEnd;
			gameDelRoom._isStart = isStart;
			return gameDelRoom;
		}
	
		protected var _isEnd:Boolean;
		protected var _isStart:Boolean;
		
		public function GameDelRoom()
		{
		}
			
		public function decode(in0:Input):void
		{
			_isEnd = in0.readBoolean();
			_isStart = in0.readBoolean();
		}
		
		public function encode(out:Output):void
		{
			out.writeBoolean(isEnd);
			out.writeBoolean(isStart);
		}
		

		public function get isEnd():Boolean
		{
			return _isEnd;
		}
		public function set isEnd(isEnd:Boolean):void
		{
			_isEnd = isEnd;
		}

		public function get isStart():Boolean
		{
			return _isStart;
		}
		public function set isStart(isStart:Boolean):void
		{
			_isStart = isStart;
		}
		
		public function toString():String
		{
			return "GameDelRoom [isEnd=" + _isEnd + ",isStart=" + _isStart + ", ]";
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