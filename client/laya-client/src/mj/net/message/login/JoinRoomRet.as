package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 进入房间结果信息
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class JoinRoomRet implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=7;
		
		public static function create(result:Boolean):JoinRoomRet
		{
			const joinRoomRet:JoinRoomRet = new JoinRoomRet();
			joinRoomRet._result = result;
			return joinRoomRet;
		}
	
		protected var _result:Boolean;
		
		public function JoinRoomRet()
		{
		}
			
		public function decode(in0:Input):void
		{
			_result = in0.readBoolean();
		}
		
		public function encode(out:Output):void
		{
			out.writeBoolean(result);
		}
		

		public function get result():Boolean
		{
			return _result;
		}
		public function set result(result:Boolean):void
		{
			_result = result;
		}
		
		public function toString():String
		{
			return "JoinRoomRet [result=" + _result + ", ]";
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