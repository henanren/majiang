package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 同步游戏信息
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class GameUserInfo implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=8;
		
		public static function create(userName:String, avatar:String, sex:int, gold:int, score:int, locationIndex:int, userId:int, online:Boolean, ip:String, user0Distance:String, user1Distance:String, user2Distance:String, user3Distance:String):GameUserInfo
		{
			const gameUserInfo:GameUserInfo = new GameUserInfo();
			gameUserInfo._userName = userName;
			gameUserInfo._avatar = avatar;
			gameUserInfo._sex = sex;
			gameUserInfo._gold = gold;
			gameUserInfo._score = score;
			gameUserInfo._locationIndex = locationIndex;
			gameUserInfo._userId = userId;
			gameUserInfo._online = online;
			gameUserInfo._ip = ip;
			gameUserInfo._user0Distance = user0Distance;
			gameUserInfo._user1Distance = user1Distance;
			gameUserInfo._user2Distance = user2Distance;
			gameUserInfo._user3Distance = user3Distance;
			return gameUserInfo;
		}
	
		protected var _userName:String;
		protected var _avatar:String;
		/**
	 	 * 0:女生,1:男生,2:未知
	 	 */
		protected var _sex:int;
		protected var _gold:int;
		protected var _score:int;
		protected var _locationIndex:int;
		protected var _userId:int;
		protected var _online:Boolean;
		protected var _ip:String;
		protected var _user0Distance:String;
		protected var _user1Distance:String;
		protected var _user2Distance:String;
		protected var _user3Distance:String;
		
		public function GameUserInfo()
		{
		}
			
		public function decode(in0:Input):void
		{
			_userName = in0.readString();
			_avatar = in0.readString();
			_sex = in0.readInt();
			_gold = in0.readInt();
			_score = in0.readInt();
			_locationIndex = in0.readInt();
			_userId = in0.readInt();
			_online = in0.readBoolean();
			_ip = in0.readString();
			_user0Distance = in0.readString();
			_user1Distance = in0.readString();
			_user2Distance = in0.readString();
			_user3Distance = in0.readString();
		}
		
		public function encode(out:Output):void
		{
			out.writeString(userName);
			out.writeString(avatar);
			out.writeInt(sex);
			out.writeInt(gold);
			out.writeInt(score);
			out.writeInt(locationIndex);
			out.writeInt(userId);
			out.writeBoolean(online);
			out.writeString(ip);
			out.writeString(user0Distance);
			out.writeString(user1Distance);
			out.writeString(user2Distance);
			out.writeString(user3Distance);
		}
		

		public function get userName():String
		{
			return _userName;
		}
		public function set userName(userName:String):void
		{
			_userName = userName;
		}

		public function get avatar():String
		{
			return _avatar;
		}
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

		public function get gold():int
		{
			return _gold;
		}
		public function set gold(gold:int):void
		{
			_gold = gold;
		}

		public function get score():int
		{
			return _score;
		}
		public function set score(score:int):void
		{
			_score = score;
		}

		public function get locationIndex():int
		{
			return _locationIndex;
		}
		public function set locationIndex(locationIndex:int):void
		{
			_locationIndex = locationIndex;
		}

		public function get userId():int
		{
			return _userId;
		}
		public function set userId(userId:int):void
		{
			_userId = userId;
		}

		public function get online():Boolean
		{
			return _online;
		}
		public function set online(online:Boolean):void
		{
			_online = online;
		}

		public function get ip():String
		{
			return _ip;
		}
		public function set ip(ip:String):void
		{
			_ip = ip;
		}

		public function get user0Distance():String
		{
			return _user0Distance;
		}
		public function set user0Distance(user0Distance:String):void
		{
			_user0Distance = user0Distance;
		}

		public function get user1Distance():String
		{
			return _user1Distance;
		}
		public function set user1Distance(user1Distance:String):void
		{
			_user1Distance = user1Distance;
		}

		public function get user2Distance():String
		{
			return _user2Distance;
		}
		public function set user2Distance(user2Distance:String):void
		{
			_user2Distance = user2Distance;
		}

		public function get user3Distance():String
		{
			return _user3Distance;
		}
		public function set user3Distance(user3Distance:String):void
		{
			_user3Distance = user3Distance;
		}
		
		public function toString():String
		{
			return "GameUserInfo [userName=" + _userName + ",avatar=" + _avatar + ",sex=" + _sex + ",gold=" + _gold + ",score=" + _score + ",locationIndex=" + _locationIndex + ",userId=" + _userId + ",online=" + _online + ",ip=" + _ip + ",user0Distance=" + _user0Distance + ",user1Distance=" + _user1Distance + ",user2Distance=" + _user2Distance + ",user3Distance=" + _user3Distance + ", ]";
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