package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 登陆信息
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class Login implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=8;
		
		public static function create(type:String, openId:String, code:String, longitude:String, latitude:String):Login
		{
			const login:Login = new Login();
			login._type = type;
			login._openId = openId;
			login._code = code;
			login._longitude = longitude;
			login._latitude = latitude;
			return login;
		}
	
		/**
	 	 * SMS 短信登录，WEIXIN_CLIENT 微信客户端，TOKEN token登录
	 	 */
		protected var _type:String;
		protected var _openId:String;
		protected var _code:String;
		/**
	 	 * 经度
	 	 */
		protected var _longitude:String;
		/**
	 	 * 纬度
	 	 */
		protected var _latitude:String;
		
		public function Login()
		{
		}
			
		public function decode(in0:Input):void
		{
			_type = in0.readString();
			_openId = in0.readString();
			_code = in0.readString();
			_longitude = in0.readString();
			_latitude = in0.readString();
		}
		
		public function encode(out:Output):void
		{
			out.writeString(type);
			out.writeString(openId);
			out.writeString(code);
			out.writeString(longitude);
			out.writeString(latitude);
		}
		

		/**
		 * SMS 短信登录，WEIXIN_CLIENT 微信客户端，TOKEN token登录
		 */
		public function get type():String
		{
			return _type;
		}
		/**
		 * SMS 短信登录，WEIXIN_CLIENT 微信客户端，TOKEN token登录
		 */
		public function set type(type:String):void
		{
			_type = type;
		}

		public function get openId():String
		{
			return _openId;
		}
		public function set openId(openId:String):void
		{
			_openId = openId;
		}

		public function get code():String
		{
			return _code;
		}
		public function set code(code:String):void
		{
			_code = code;
		}

		/**
		 * 经度
		 */
		public function get longitude():String
		{
			return _longitude;
		}
		/**
		 * 经度
		 */
		public function set longitude(longitude:String):void
		{
			_longitude = longitude;
		}

		/**
		 * 纬度
		 */
		public function get latitude():String
		{
			return _latitude;
		}
		/**
		 * 纬度
		 */
		public function set latitude(latitude:String):void
		{
			_latitude = latitude;
		}
		
		public function toString():String
		{
			return "Login [type=" + _type + ",openId=" + _openId + ",code=" + _code + ",longitude=" + _longitude + ",latitude=" + _latitude + ", ]";
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