package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 通知客户端"吃碰杠胡" 回复
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class OperationCPGHRet implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=11;
		
		public static function create(opt:String, chi:Array):OperationCPGHRet
		{
			const operationCPGHRet:OperationCPGHRet = new OperationCPGHRet();
			operationCPGHRet._opt = opt;
			operationCPGHRet._chi = chi;
			return operationCPGHRet;
		}
	
		protected var _opt:String;
		/**
	 	 * 如果是吃牌，回复吃的组合
	 	 */
		protected var _chi:Array;
		
		public function OperationCPGHRet()
		{
		}
			
		public function decode(in0:Input):void
		{
			_opt = in0.readString();
			_chi = in0.readIntArray();
		}
		
		public function encode(out:Output):void
		{
			out.writeString(opt);
			out.writeIntArray(chi);
		}
		

		public function get opt():String
		{
			return _opt;
		}
		public function set opt(opt:String):void
		{
			_opt = opt;
		}

		/**
		 * 如果是吃牌，回复吃的组合
		 */
		public function get chi():Array
		{
			return _chi;
		}
		/**
		 * 如果是吃牌，回复吃的组合
		 */
		public function set chi(chi:Array):void
		{
			_chi = chi;
		}
		
		public function toString():String
		{
			return "OperationCPGHRet [opt=" + _opt + ",chi=" + _chi + ", ]";
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