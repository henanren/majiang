package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 通知
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class Notice implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=11;
		
		public static function create(key:String, args:Array, type:int, reboot:Boolean):Notice
		{
			const notice:Notice = new Notice();
			notice._key = key;
			notice._args = args;
			notice._type = type;
			notice._reboot = reboot;
			return notice;
		}
	
		/**
	 	 * 语言文件的key,或者内容字符串
	 	 */
		protected var _key:String;
		protected var _args:Array;
		/**
	 	 * 0:横屏实时通知,1:悬停错误提示
	 	 */
		protected var _type:int;
		/**
	 	 * 是否需要重新启动游戏
	 	 */
		protected var _reboot:Boolean;
		
		public function Notice()
		{
		}
			
		public function decode(in0:Input):void
		{
			_key = in0.readString();
			_args = in0.readStringArray();
			_type = in0.readInt();
			_reboot = in0.readBoolean();
		}
		
		public function encode(out:Output):void
		{
			out.writeString(key);
			out.writeStringArray(args);
			out.writeInt(type);
			out.writeBoolean(reboot);
		}
		

		/**
		 * 语言文件的key,或者内容字符串
		 */
		public function get key():String
		{
			return _key;
		}
		/**
		 * 语言文件的key,或者内容字符串
		 */
		public function set key(key:String):void
		{
			_key = key;
		}

		public function get args():Array
		{
			return _args;
		}
		public function set args(args:Array):void
		{
			_args = args;
		}

		/**
		 * 0:横屏实时通知,1:悬停错误提示
		 */
		public function get type():int
		{
			return _type;
		}
		/**
		 * 0:横屏实时通知,1:悬停错误提示
		 */
		public function set type(type:int):void
		{
			_type = type;
		}

		/**
		 * 是否需要重新启动游戏
		 */
		public function get reboot():Boolean
		{
			return _reboot;
		}
		/**
		 * 是否需要重新启动游戏
		 */
		public function set reboot(reboot:Boolean):void
		{
			_reboot = reboot;
		}
		
		public function toString():String
		{
			return "Notice [key=" + _key + ",args=" + _args + ",type=" + _type + ",reboot=" + _reboot + ", ]";
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