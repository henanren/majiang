package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	public class Radio implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=16;
		
		public static function create(radio:String):Radio
		{
			const radio:Radio = new Radio();
			radio._radio = radio;
			return radio;
		}
	
		/**
	 	 * 广播（跑马灯）
	 	 */
		protected var _radio:String;
		
		public function Radio()
		{
		}
			
		public function decode(in0:Input):void
		{
			_radio = in0.readString();
		}
		
		public function encode(out:Output):void
		{
			out.writeString(radio);
		}
		

		/**
		 * 广播（跑马灯）
		 */
		public function get radio():String
		{
			return _radio;
		}
		/**
		 * 广播（跑马灯）
		 */
		public function set radio(radio:String):void
		{
			_radio = radio;
		}
		
		public function toString():String
		{
			return "Radio [radio=" + _radio + ", ]";
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