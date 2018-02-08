package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 同步操作
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class SyncOptTime implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=17;
		
		public static function create(index:int, leftTime:int):SyncOptTime
		{
			const syncOptTime:SyncOptTime = new SyncOptTime();
			syncOptTime._index = index;
			syncOptTime._leftTime = leftTime;
			return syncOptTime;
		}
	
		/**
	 	 * 位置
	 	 */
		protected var _index:int;
		/**
	 	 * 毫秒
	 	 */
		protected var _leftTime:int;
		
		public function SyncOptTime()
		{
		}
			
		public function decode(in0:Input):void
		{
			_index = in0.readInt();
			_leftTime = in0.readInt();
		}
		
		public function encode(out:Output):void
		{
			out.writeInt(index);
			out.writeInt(leftTime);
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
		 * 毫秒
		 */
		public function get leftTime():int
		{
			return _leftTime;
		}
		/**
		 * 毫秒
		 */
		public function set leftTime(leftTime:int):void
		{
			_leftTime = leftTime;
		}
		
		public function toString():String
		{
			return "SyncOptTime [index=" + _index + ",leftTime=" + _leftTime + ", ]";
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