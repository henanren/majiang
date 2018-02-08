package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 登陆结果
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class LoginRet implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=10;
		
		public static function create(id:int, name:String, openId:String, uuid:String, avatar:String, sex:int, roomCheckId:String, gold:int, loginToken:String, ip:String):LoginRet
		{
			const loginRet:LoginRet = new LoginRet();
			loginRet._id = id;
			loginRet._name = name;
			loginRet._openId = openId;
			loginRet._uuid = uuid;
			loginRet._avatar = avatar;
			loginRet._sex = sex;
			loginRet._roomCheckId = roomCheckId;
			loginRet._gold = gold;
			loginRet._loginToken = loginToken;
			loginRet._ip = ip;
			return loginRet;
		}
	
		/**
	 	 * id
	 	 */
		protected var _id:int;
		/**
	 	 * 昵称
	 	 */
		protected var _name:String;
		/**
	 	 * 昵称
	 	 */
		protected var _openId:String;
		/**
	 	 * uuid
	 	 */
		protected var _uuid:String;
		/**
	 	 * 头像
	 	 */
		protected var _avatar:String;
		/**
	 	 * 0:女生,1:男生,2:未知
	 	 */
		protected var _sex:int;
		/**
	 	 * 如果用户进入过房间,未主动退出房间id
	 	 */
		protected var _roomCheckId:String;
		protected var _gold:int;
		protected var _loginToken:String;
		protected var _ip:String;
		
		public function LoginRet()
		{
		}
			
		public function decode(in0:Input):void
		{
			_id = in0.readInt();
			_name = in0.readString();
			_openId = in0.readString();
			_uuid = in0.readString();
			_avatar = in0.readString();
			_sex = in0.readInt();
			_roomCheckId = in0.readString();
			_gold = in0.readInt();
			_loginToken = in0.readString();
			_ip = in0.readString();
		}
		
		public function encode(out:Output):void
		{
			out.writeInt(id);
			out.writeString(name);
			out.writeString(openId);
			out.writeString(uuid);
			out.writeString(avatar);
			out.writeInt(sex);
			out.writeString(roomCheckId);
			out.writeInt(gold);
			out.writeString(loginToken);
			out.writeString(ip);
		}
		

		/**
		 * id
		 */
		public function get id():int
		{
			return _id;
		}
		/**
		 * id
		 */
		public function set id(id:int):void
		{
			_id = id;
		}

		/**
		 * 昵称
		 */
		public function get name():String
		{
			return _name;
		}
		/**
		 * 昵称
		 */
		public function set name(name:String):void
		{
			_name = name;
		}

		/**
		 * 昵称
		 */
		public function get openId():String
		{
			return _openId;
		}
		/**
		 * 昵称
		 */
		public function set openId(openId:String):void
		{
			_openId = openId;
		}

		/**
		 * uuid
		 */
		public function get uuid():String
		{
			return _uuid;
		}
		/**
		 * uuid
		 */
		public function set uuid(uuid:String):void
		{
			_uuid = uuid;
		}

		/**
		 * 头像
		 */
		public function get avatar():String
		{
			return _avatar;
		}
		/**
		 * 头像
		 */
		public function set avatar(avatar:String):void
		{
			_avatar = avatar;
		}

		/**
		 * 0:女生,1:男生,2:未知
		 */
		public function get sex():int
		{
			return _sex;
		}
		/**
		 * 0:女生,1:男生,2:未知
		 */
		public function set sex(sex:int):void
		{
			_sex = sex;
		}

		/**
		 * 如果用户进入过房间,未主动退出房间id
		 */
		public function get roomCheckId():String
		{
			return _roomCheckId;
		}
		/**
		 * 如果用户进入过房间,未主动退出房间id
		 */
		public function set roomCheckId(roomCheckId:String):void
		{
			_roomCheckId = roomCheckId;
		}

		public function get gold():int
		{
			return _gold;
		}
		public function set gold(gold:int):void
		{
			_gold = gold;
		}

		public function get loginToken():String
		{
			return _loginToken;
		}
		public function set loginToken(loginToken:String):void
		{
			_loginToken = loginToken;
		}

		public function get ip():String
		{
			return _ip;
		}
		public function set ip(ip:String):void
		{
			_ip = ip;
		}
		
		public function toString():String
		{
			return "LoginRet [id=" + _id + ",name=" + _name + ",openId=" + _openId + ",uuid=" + _uuid + ",avatar=" + _avatar + ",sex=" + _sex + ",roomCheckId=" + _roomCheckId + ",gold=" + _gold + ",loginToken=" + _loginToken + ",ip=" + _ip + ", ]";
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