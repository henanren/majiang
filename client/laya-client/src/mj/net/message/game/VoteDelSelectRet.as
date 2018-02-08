package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	public class VoteDelSelectRet implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=22;
		
		public static function create(result:Boolean, userId:int):VoteDelSelectRet
		{
			const voteDelSelectRet:VoteDelSelectRet = new VoteDelSelectRet();
			voteDelSelectRet._result = result;
			voteDelSelectRet._userId = userId;
			return voteDelSelectRet;
		}
	
		protected var _result:Boolean;
		protected var _userId:int;
		
		public function VoteDelSelectRet()
		{
		}
			
		public function decode(in0:Input):void
		{
			_result = in0.readBoolean();
			_userId = in0.readInt();
		}
		
		public function encode(out:Output):void
		{
			out.writeBoolean(result);
			out.writeInt(userId);
		}
		

		public function get result():Boolean
		{
			return _result;
		}
		public function set result(result:Boolean):void
		{
			_result = result;
		}

		public function get userId():int
		{
			return _userId;
		}
		public function set userId(userId:int):void
		{
			_userId = userId;
		}
		
		public function toString():String
		{
			return "VoteDelSelectRet [result=" + _result + ",userId=" + _userId + ", ]";
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