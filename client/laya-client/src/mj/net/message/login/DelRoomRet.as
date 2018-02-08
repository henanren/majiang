package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 解散房间结果
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class DelRoomRet implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=3;
		
		public static function create(result:Boolean):DelRoomRet
		{
			const delRoomRet:DelRoomRet = new DelRoomRet();
			delRoomRet._result = result;
			return delRoomRet;
		}
	
		protected var _result:Boolean;
		
		public function DelRoomRet()
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
			return "DelRoomRet [result=" + _result + ", ]";
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