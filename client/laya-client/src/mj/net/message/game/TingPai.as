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
	public class TingPai implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=18;
		
		public static function create(pais:Array):TingPai
		{
			const tingPai:TingPai = new TingPai();
			tingPai._pais = pais;
			return tingPai;
		}
	
		protected var _pais:Array;
		
		public function TingPai()
		{
		}
			
		public function decode(in0:Input):void
		{
			_pais = in0.readIntArray();
		}
		
		public function encode(out:Output):void
		{
			out.writeIntArray(pais);
		}
		

		public function get pais():Array
		{
			return _pais;
		}
		public function set pais(pais:Array):void
		{
			_pais = pais;
		}
		
		public function toString():String
		{
			return "TingPai [pais=" + _pais + ", ]";
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