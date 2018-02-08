package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 用户退出游戏
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class GameExitUser implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=4;
		
		public static function create(userId:int):GameExitUser
		{
			const gameExitUser:GameExitUser = new GameExitUser();
			gameExitUser._userId = userId;
			return gameExitUser;
		}
	
		protected var _userId:int;
		
		public function GameExitUser()
		{
		}
			
		public function decode(in0:Input):void
		{
			_userId = in0.readInt();
		}
		
		public function encode(out:Output):void
		{
			out.writeInt(userId);
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
			return "GameExitUser [userId=" + _userId + ", ]";
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