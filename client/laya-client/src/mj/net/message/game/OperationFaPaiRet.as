package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 自己的发牌的信息
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class OperationFaPaiRet implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=13;
		
		public static function create(opt:String, pai:int):OperationFaPaiRet
		{
			const operationFaPaiRet:OperationFaPaiRet = new OperationFaPaiRet();
			operationFaPaiRet._opt = opt;
			operationFaPaiRet._pai = pai;
			return operationFaPaiRet;
		}
	
		/**
	 	 * OUT:打牌,AN_GANG:暗杠牌,XIAO_MING_GANG:暗杠牌,HU:胡牌
	 	 */
		protected var _opt:String;
		protected var _pai:int;
		
		public function OperationFaPaiRet()
		{
		}
			
		public function decode(in0:Input):void
		{
			_opt = in0.readString();
			_pai = in0.readInt();
		}
		
		public function encode(out:Output):void
		{
			out.writeString(opt);
			out.writeInt(pai);
		}
		

		/**
		 * OUT:打牌,AN_GANG:暗杠牌,XIAO_MING_GANG:暗杠牌,HU:胡牌
		 */
		public function get opt():String
		{
			return _opt;
		}
		/**
		 * OUT:打牌,AN_GANG:暗杠牌,XIAO_MING_GANG:暗杠牌,HU:胡牌
		 */
		public function set opt(opt:String):void
		{
			_opt = opt;
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
			return "OperationFaPaiRet [opt=" + _opt + ",pai=" + _pai + ", ]";
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