package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	public class OptionEntry implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=12;
		
		public static function create(key:String, value:String):OptionEntry
		{
			const optionEntry:OptionEntry = new OptionEntry();
			optionEntry._key = key;
			optionEntry._value = value;
			return optionEntry;
		}
	
		protected var _key:String;
		protected var _value:String;
		
		public function OptionEntry()
		{
		}
			
		public function decode(in0:Input):void
		{
			_key = in0.readString();
			_value = in0.readString();
		}
		
		public function encode(out:Output):void
		{
			out.writeString(key);
			out.writeString(value);
		}
		

		public function get key():String
		{
			return _key;
		}
		public function set key(key:String):void
		{
			_key = key;
		}

		public function get value():String
		{
			return _value;
		}
		public function set value(value:String):void
		{
			_value = value;
		}
		
		public function toString():String
		{
			return "OptionEntry [key=" + _key + ",value=" + _value + ", ]";
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