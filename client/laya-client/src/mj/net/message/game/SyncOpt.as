package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 同步操作
	 * FA:发牌，OUT:打牌,OUT_OK:打牌成功，没人用这个哎,CHI:吃,PENG:碰,AN_GANG:暗杠牌,XIAO_MING_GANG:暗杠牌,DA_MING_GANG:暗杠牌,HU:胡牌
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class SyncOpt implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=16;
		
		public static function create(opt:String, index:int, pai:int, chi:Array):SyncOpt
		{
			const syncOpt:SyncOpt = new SyncOpt();
			syncOpt._opt = opt;
			syncOpt._index = index;
			syncOpt._pai = pai;
			syncOpt._chi = chi;
			return syncOpt;
		}
	
		protected var _opt:String;
		/**
	 	 * 位置
	 	 */
		protected var _index:int;
		protected var _pai:int;
		protected var _chi:Array;
		
		public function SyncOpt()
		{
		}
			
		public function decode(in0:Input):void
		{
			_opt = in0.readString();
			_index = in0.readInt();
			_pai = in0.readInt();
			_chi = in0.readIntArray();
		}
		
		public function encode(out:Output):void
		{
			out.writeString(opt);
			out.writeInt(index);
			out.writeInt(pai);
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

		public function get chi():Array
		{
			return _chi;
		}
		public function set chi(chi:Array):void
		{
			_chi = chi;
		}
		
		public function toString():String
		{
			return "SyncOpt [opt=" + _opt + ",index=" + _index + ",pai=" + _pai + ",chi=" + _chi + ", ]";
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