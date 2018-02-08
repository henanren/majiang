package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 创建房间
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class JoinRoom implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=6;
		
		public static function create(roomCheckId:String):JoinRoom
		{
			const joinRoom:JoinRoom = new JoinRoom();
			joinRoom._roomCheckId = roomCheckId;
			return joinRoom;
		}
	
		protected var _roomCheckId:String;
		
		public function JoinRoom()
		{
		}
			
		public function decode(in0:Input):void
		{
			_roomCheckId = in0.readString();
		}
		
		public function encode(out:Output):void
		{
			out.writeString(roomCheckId);
		}
		

		public function get roomCheckId():String
		{
			return _roomCheckId;
		}
		public function set roomCheckId(roomCheckId:String):void
		{
			_roomCheckId = roomCheckId;
		}
		
		public function toString():String
		{
			return "JoinRoom [roomCheckId=" + _roomCheckId + ", ]";
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