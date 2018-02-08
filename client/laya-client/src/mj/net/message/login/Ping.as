package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * ping
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class Ping implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=14;
		
		public static function create(time:String):Ping
		{
			const ping:Ping = new Ping();
			ping._time = time;
			return ping;
		}
	
		protected var _time:String;
		
		public function Ping()
		{
		}
			
		public function decode(in0:Input):void
		{
			_time = in0.readString();
		}
		
		public function encode(out:Output):void
		{
			out.writeString(time);
		}
		

		public function get time():String
		{
			return _time;
		}
		public function set time(time:String):void
		{
			_time = time;
		}
		
		public function toString():String
		{
			return "Ping [time=" + _time + ", ]";
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