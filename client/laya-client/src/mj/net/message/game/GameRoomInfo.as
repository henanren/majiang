package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	
	import mj.net.message.game.GameUserInfo;
	import mj.net.message.game.MajiangChapterMsg;

	import laya.utils.*;
	
	/**
 	 * 同步游戏信息
	 * 0东 1南 2西 3北 逆时针顺序
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class GameRoomInfo implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=7;
		
		public static function create(sceneUser:Array, start:Boolean, roomCheckId:String, leftChapterNums:int, createUserId:int, chapter:mj.net.message.game.MajiangChapterMsg):GameRoomInfo
		{
			const gameRoomInfo:GameRoomInfo = new GameRoomInfo();
			gameRoomInfo._sceneUser = sceneUser;
			gameRoomInfo._start = start;
			gameRoomInfo._roomCheckId = roomCheckId;
			gameRoomInfo._leftChapterNums = leftChapterNums;
			gameRoomInfo._createUserId = createUserId;
			gameRoomInfo._chapter = chapter;
			return gameRoomInfo;
		}
	
		protected var _sceneUser:Array;
		protected var _start:Boolean;
		protected var _roomCheckId:String;
		protected var _leftChapterNums:int;
		protected var _createUserId:int;
		protected var _chapter:mj.net.message.game.MajiangChapterMsg;
		
		public function GameRoomInfo()
		{
		}
			
		public function decode(in0:Input):void
		{
		
			var sceneUserLen:int = in0.readInt();
			if(sceneUserLen == -1)
			{
				_sceneUser = null;
			}
			else
			{
				_sceneUser = new Array();
				for(var sceneUserI:int = 0; sceneUserI < sceneUserLen; sceneUserI++)
				{
					var sceneUserItem:GameUserInfo = new GameUserInfo();
					sceneUserItem.decode(in0);
					_sceneUser[sceneUserI] = (sceneUserItem);
				}
			}
			_start = in0.readBoolean();
			_roomCheckId = in0.readString();
			_leftChapterNums = in0.readInt();
			_createUserId = in0.readInt();
		
			var chapterIsNotNull:Boolean = in0.readBoolean();
			if(chapterIsNotNull)
			{
				_chapter = new mj.net.message.game.MajiangChapterMsg();
				_chapter.decode(in0);
			}
			else
			{
				_chapter = null;
			}
		}
		
		public function encode(out:Output):void
		{
		
			if(sceneUser == null){
				out.writeInt(-1);
			}else{
				var sceneUserLen:int = sceneUser.length;
				out.writeInt(sceneUserLen);
				for(var sceneUserI:int = 0; sceneUserI < sceneUserLen; sceneUserI++)
				{
					sceneUser[sceneUserI].encode(out);
				}
			}
			out.writeBoolean(start);
			out.writeString(roomCheckId);
			out.writeInt(leftChapterNums);
			out.writeInt(createUserId);
		
			if(chapter == null){
				out.writeBoolean(false);
			}else{
				out.writeBoolean(true);
				chapter.encode(out);
			}
		}
		

		public function get sceneUser():Array
		{
			return _sceneUser;
		}
		public function set sceneUser(sceneUser:Array):void
		{
			_sceneUser = sceneUser;
		}

		public function get start():Boolean
		{
			return _start;
		}
		public function set start(start:Boolean):void
		{
			_start = start;
		}

		public function get roomCheckId():String
		{
			return _roomCheckId;
		}
		public function set roomCheckId(roomCheckId:String):void
		{
			_roomCheckId = roomCheckId;
		}

		public function get leftChapterNums():int
		{
			return _leftChapterNums;
		}
		public function set leftChapterNums(leftChapterNums:int):void
		{
			_leftChapterNums = leftChapterNums;
		}

		public function get createUserId():int
		{
			return _createUserId;
		}
		public function set createUserId(createUserId:int):void
		{
			_createUserId = createUserId;
		}

		public function get chapter():mj.net.message.game.MajiangChapterMsg
		{
			return _chapter;
		}
		public function set chapter(chapter:mj.net.message.game.MajiangChapterMsg):void
		{
			_chapter = chapter;
		}
		
		public function toString():String
		{
			return "GameRoomInfo [sceneUser=" + _sceneUser + ",start=" + _start + ",roomCheckId=" + _roomCheckId + ",leftChapterNums=" + _leftChapterNums + ",createUserId=" + _createUserId + ",chapter=" + _chapter + ", ]";
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