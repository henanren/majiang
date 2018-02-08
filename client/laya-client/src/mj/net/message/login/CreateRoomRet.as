package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 创建房间结果
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class CreateRoomRet implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=1;
		
		public static function create(result:Boolean, roomCheckId:String):CreateRoomRet
		{
			const createRoomRet:CreateRoomRet = new CreateRoomRet();
			createRoomRet._result = result;
			createRoomRet._roomCheckId = roomCheckId;
			return createRoomRet;
		}
	
		protected var _result:Boolean;
		protected var _roomCheckId:String;
		
		public function CreateRoomRet()
		{
		}
			
		public function decode(in0:Input):void
		{
			_result = in0.readBoolean();
			_roomCheckId = in0.readString();
		}
		
		public function encode(out:Output):void
		{
			out.writeBoolean(result);
			out.writeString(roomCheckId);
		}
		

		public function get result():Boolean
		{
			return _result;
		}
		public function set result(result:Boolean):void
		{
			_result = result;
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
			return "CreateRoomRet [result=" + _result + ",roomCheckId=" + _roomCheckId + ", ]";
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