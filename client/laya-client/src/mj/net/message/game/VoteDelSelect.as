package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	public class VoteDelSelect implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=21;
		
		public static function create(userId:int, userName:String):VoteDelSelect
		{
			const voteDelSelect:VoteDelSelect = new VoteDelSelect();
			voteDelSelect._userId = userId;
			voteDelSelect._userName = userName;
			return voteDelSelect;
		}
	
		protected var _userId:int;
		protected var _userName:String;
		
		public function VoteDelSelect()
		{
		}
			
		public function decode(in0:Input):void
		{
			_userId = in0.readInt();
			_userName = in0.readString();
		}
		
		public function encode(out:Output):void
		{
			out.writeInt(userId);
			out.writeString(userName);
		}
		

		public function get userId():int
		{
			return _userId;
		}
		public function set userId(userId:int):void
		{
			_userId = userId;
		}

		public function get userName():String
		{
			return _userName;
		}
		public function set userName(userName:String):void
		{
			_userName = userName;
		}
		
		public function toString():String
		{
			return "VoteDelSelect [userId=" + _userId + ",userName=" + _userName + ", ]";
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