package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 出牌回复
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class OperationOutRet implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=15;
		
		public static function create(pai:int):OperationOutRet
		{
			const operationOutRet:OperationOutRet = new OperationOutRet();
			operationOutRet._pai = pai;
			return operationOutRet;
		}
	
		protected var _pai:int;
		
		public function OperationOutRet()
		{
		}
			
		public function decode(in0:Input):void
		{
			_pai = in0.readInt();
		}
		
		public function encode(out:Output):void
		{
			out.writeInt(pai);
		}
		

		public function get pai():int
		{
			return _pai;
		}
		public function set pai(pai:int):void
		{
			_pai = pai;
		}
		
		public function toString():String
		{
			return "OperationOutRet [pai=" + _pai + ", ]";
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