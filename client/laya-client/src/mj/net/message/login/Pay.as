package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * pong
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class Pay implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=13;
		
		public static function create(gold:int):Pay
		{
			const pay:Pay = new Pay();
			pay._gold = gold;
			return pay;
		}
	
		protected var _gold:int;
		
		public function Pay()
		{
		}
			
		public function decode(in0:Input):void
		{
			_gold = in0.readInt();
		}
		
		public function encode(out:Output):void
		{
			out.writeInt(gold);
		}
		

		public function get gold():int
		{
			return _gold;
		}
		public function set gold(gold:int):void
		{
			_gold = gold;
		}
		
		public function toString():String
		{
			return "Pay [gold=" + _gold + ", ]";
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