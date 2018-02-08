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
	public class OperationFaPai implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=12;
		
		public static function create(index:int, pai:int, anGang:Array, mingGang:Array, hu:Boolean):OperationFaPai
		{
			const operationFaPai:OperationFaPai = new OperationFaPai();
			operationFaPai._index = index;
			operationFaPai._pai = pai;
			operationFaPai._anGang = anGang;
			operationFaPai._mingGang = mingGang;
			operationFaPai._hu = hu;
			return operationFaPai;
		}
	
		/**
	 	 * 位置
	 	 */
		protected var _index:int;
		protected var _pai:int;
		protected var _anGang:Array;
		protected var _mingGang:Array;
		protected var _hu:Boolean;
		
		public function OperationFaPai()
		{
		}
			
		public function decode(in0:Input):void
		{
			_index = in0.readInt();
			_pai = in0.readInt();
			_anGang = in0.readIntArray();
			_mingGang = in0.readIntArray();
			_hu = in0.readBoolean();
		}
		
		public function encode(out:Output):void
		{
			out.writeInt(index);
			out.writeInt(pai);
			out.writeIntArray(anGang);
			out.writeIntArray(mingGang);
			out.writeBoolean(hu);
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

		public function get pai():int
		{
			return _pai;
		}
		public function set pai(pai:int):void
		{
			_pai = pai;
		}

		public function get anGang():Array
		{
			return _anGang;
		}
		public function set anGang(anGang:Array):void
		{
			_anGang = anGang;
		}

		public function get mingGang():Array
		{
			return _mingGang;
		}
		public function set mingGang(mingGang:Array):void
		{
			_mingGang = mingGang;
		}

		public function get hu():Boolean
		{
			return _hu;
		}
		public function set hu(hu:Boolean):void
		{
			_hu = hu;
		}
		
		public function toString():String
		{
			return "OperationFaPai [index=" + _index + ",pai=" + _pai + ",anGang=" + _anGang + ",mingGang=" + _mingGang + ",hu=" + _hu + ", ]";
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