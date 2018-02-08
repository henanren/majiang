package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	
	import mj.net.message.login.RoomHistory;

	import laya.utils.*;
	
	public class RoomHistoryListRet implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=20;
		
		public static function create(list:Array):RoomHistoryListRet
		{
			const roomHistoryListRet:RoomHistoryListRet = new RoomHistoryListRet();
			roomHistoryListRet._list = list;
			return roomHistoryListRet;
		}
	
		protected var _list:Array;
		
		public function RoomHistoryListRet()
		{
		}
			
		public function decode(in0:Input):void
		{
		
			var listLen:int = in0.readInt();
			if(listLen == -1)
			{
				_list = null;
			}
			else
			{
				_list = new Array();
				for(var listI:int = 0; listI < listLen; listI++)
				{
					var listItem:RoomHistory = new RoomHistory();
					listItem.decode(in0);
					_list[listI] = (listItem);
				}
			}
		}
		
		public function encode(out:Output):void
		{
		
			if(list == null){
				out.writeInt(-1);
			}else{
				var listLen:int = list.length;
				out.writeInt(listLen);
				for(var listI:int = 0; listI < listLen; listI++)
				{
					list[listI].encode(out);
				}
			}
		}
		

		public function get list():Array
		{
			return _list;
		}
		public function set list(list:Array):void
		{
			_list = list;
		}
		
		public function toString():String
		{
			return "RoomHistoryListRet [list=" + _list + ", ]";
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