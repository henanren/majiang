package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 通知用户出牌
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class OperationOut implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=14;
		
		public static function create(index:int):OperationOut
		{
			const operationOut:OperationOut = new OperationOut();
			operationOut._index = index;
			return operationOut;
		}
	
		protected var _index:int;
		
		public function OperationOut()
		{
		}
			
		public function decode(in0:Input):void
		{
			_index = in0.readInt();
		}
		
		public function encode(out:Output):void
		{
			out.writeInt(index);
		}
		

		public function get index():int
		{
			return _index;
		}
		public function set index(index:int):void
		{
			_index = index;
		}
		
		public function toString():String
		{
			return "OperationOut [index=" + _index + ", ]";
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