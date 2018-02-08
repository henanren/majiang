package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 通知客户端"吃碰杠胡"
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class OperationCPGH implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=10;
		
		public static function create(index:int, chi:Array, isPeng:Boolean, isGang:Boolean, isHu:Boolean, pai:int):OperationCPGH
		{
			const operationCPGH:OperationCPGH = new OperationCPGH();
			operationCPGH._index = index;
			operationCPGH._chi = chi;
			operationCPGH._isPeng = isPeng;
			operationCPGH._isGang = isGang;
			operationCPGH._isHu = isHu;
			operationCPGH._pai = pai;
			return operationCPGH;
		}
	
		/**
	 	 * 位置
	 	 */
		protected var _index:int;
		/**
	 	 * 3个一组一组
	 	 */
		protected var _chi:Array;
		protected var _isPeng:Boolean;
		protected var _isGang:Boolean;
		protected var _isHu:Boolean;
		protected var _pai:int;
		
		public function OperationCPGH()
		{
		}
			
		public function decode(in0:Input):void
		{
			_index = in0.readInt();
			_chi = in0.readIntArray();
			_isPeng = in0.readBoolean();
			_isGang = in0.readBoolean();
			_isHu = in0.readBoolean();
			_pai = in0.readInt();
		}
		
		public function encode(out:Output):void
		{
			out.writeInt(index);
			out.writeIntArray(chi);
			out.writeBoolean(isPeng);
			out.writeBoolean(isGang);
			out.writeBoolean(isHu);
			out.writeInt(pai);
		}
		

		/**
		 * 位置
		 */
		public function get index():int
		{
			return _index;
		}
		/**
		 * 位置
		 */
		public function set index(index:int):void
		{
			_index = index;
		}

		/**
		 * 3个一组一组
		 */
		public function get chi():Array
		{
			return _chi;
		}
		/**
		 * 3个一组一组
		 */
		public function set chi(chi:Array):void
		{
			_chi = chi;
		}

		public function get isPeng():Boolean
		{
			return _isPeng;
		}
		public function set isPeng(isPeng:Boolean):void
		{
			_isPeng = isPeng;
		}

		public function get isGang():Boolean
		{
			return _isGang;
		}
		public function set isGang(isGang:Boolean):void
		{
			_isGang = isGang;
		}

		public function get isHu():Boolean
		{
			return _isHu;
		}
		public function set isHu(isHu:Boolean):void
		{
			_isHu = isHu;
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
			return "OperationCPGH [index=" + _index + ",chi=" + _chi + ",isPeng=" + _isPeng + ",isGang=" + _isGang + ",isHu=" + _isHu + ",pai=" + _pai + ", ]";
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