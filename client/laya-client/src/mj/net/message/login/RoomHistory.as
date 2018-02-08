package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	public class RoomHistory implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=18;
		
		public static function create(roomCheckId:String, startDate:String, chapterNums:int, userNames:Array, scores:Array):RoomHistory
		{
			const roomHistory:RoomHistory = new RoomHistory();
			roomHistory._roomCheckId = roomCheckId;
			roomHistory._startDate = startDate;
			roomHistory._chapterNums = chapterNums;
			roomHistory._userNames = userNames;
			roomHistory._scores = scores;
			return roomHistory;
		}
	
		protected var _roomCheckId:String;
		protected var _startDate:String;
		protected var _chapterNums:int;
		protected var _userNames:Array;
		protected var _scores:Array;
		
		public function RoomHistory()
		{
		}
			
		public function decode(in0:Input):void
		{
			_roomCheckId = in0.readString();
			_startDate = in0.readString();
			_chapterNums = in0.readInt();
			_userNames = in0.readStringArray();
			_scores = in0.readIntArray();
		}
		
		public function encode(out:Output):void
		{
			out.writeString(roomCheckId);
			out.writeString(startDate);
			out.writeInt(chapterNums);
			out.writeStringArray(userNames);
			out.writeIntArray(scores);
		}
		

		public function get roomCheckId():String
		{
			return _roomCheckId;
		}
		public function set roomCheckId(roomCheckId:String):void
		{
			_roomCheckId = roomCheckId;
		}

		public function get startDate():String
		{
			return _startDate;
		}
		public function set startDate(startDate:String):void
		{
			_startDate = startDate;
		}

		public function get chapterNums():int
		{
			return _chapterNums;
		}
		public function set chapterNums(chapterNums:int):void
		{
			_chapterNums = chapterNums;
		}

		public function get userNames():Array
		{
			return _userNames;
		}
		public function set userNames(userNames:Array):void
		{
			_userNames = userNames;
		}

		public function get scores():Array
		{
			return _scores;
		}
		public function set scores(scores:Array):void
		{
			_scores = scores;
		}
		
		public function toString():String
		{
			return "RoomHistory [roomCheckId=" + _roomCheckId + ",startDate=" + _startDate + ",chapterNums=" + _chapterNums + ",userNames=" + _userNames + ",scores=" + _scores + ", ]";
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