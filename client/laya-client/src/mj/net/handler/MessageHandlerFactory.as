package mj.net.handler
{
	import com.isnowfox.core.SingletonError;
	import com.isnowfox.core.io.message.MessageHandler;
			
	import mj.net.handler.game.GameChapterEndHandler;
	import mj.net.handler.game.GameChapterStartRetHandler;
	import mj.net.handler.game.GameDelRoomHandler;
	import mj.net.handler.game.GameExitUserHandler;
	import mj.net.handler.game.GameRoomInfoHandler;
	import mj.net.handler.game.GameUserInfoHandler;
	import mj.net.handler.game.MajiangChapterMsgHandler;
	import mj.net.handler.game.OperationCPGHHandler;
	import mj.net.handler.game.OperationFaPaiHandler;
	import mj.net.handler.game.OperationOutHandler;
	import mj.net.handler.game.SyncOptHandler;
	import mj.net.handler.game.SyncOptTimeHandler;
	import mj.net.handler.game.TingPaiHandler;
	import mj.net.handler.game.UserOfflineHandler;
	import mj.net.handler.game.UserPlaceMsgHandler;
	import mj.net.handler.game.VoteDelSelectHandler;
			
	import mj.net.handler.login.CreateRoomRetHandler;
	import mj.net.handler.login.DelRoomRetHandler;
	import mj.net.handler.login.ExitRoomRetHandler;
	import mj.net.handler.login.JoinRoomRetHandler;
	import mj.net.handler.login.LoginErrorHandler;
	import mj.net.handler.login.LoginRetHandler;
	import mj.net.handler.login.NoticeHandler;
	import mj.net.handler.login.PayHandler;
	import mj.net.handler.login.PongHandler;
	import mj.net.handler.login.RadioHandler;
	import mj.net.handler.login.RepeatLoginRetHandler;
	import mj.net.handler.login.RoomHistoryListRetHandler;
	import mj.net.handler.login.SendAuthCodeRetHandler;
	import mj.net.handler.login.StartGameHandler;
	import mj.net.handler.login.SysSettingHandler;

	public class MessageHandlerFactory
	{
		public static const instance:MessageHandlerFactory = new MessageHandlerFactory();
		
		private const vector:Array = [];
		
		
		public function MessageHandlerFactory()
		{
			if(instance!=null){
				throw new SingletonError("ResourceManager 是单例模式");
			}
			
			vector[1] = [];
			vector[1][0] = mj.net.handler.game.GameChapterEndHandler.instance;
			vector[1][2] = mj.net.handler.game.GameChapterStartRetHandler.instance;
			vector[1][3] = mj.net.handler.game.GameDelRoomHandler.instance;
			vector[1][4] = mj.net.handler.game.GameExitUserHandler.instance;
			vector[1][7] = mj.net.handler.game.GameRoomInfoHandler.instance;
			vector[1][8] = mj.net.handler.game.GameUserInfoHandler.instance;
			vector[1][9] = mj.net.handler.game.MajiangChapterMsgHandler.instance;
			vector[1][10] = mj.net.handler.game.OperationCPGHHandler.instance;
			vector[1][12] = mj.net.handler.game.OperationFaPaiHandler.instance;
			vector[1][14] = mj.net.handler.game.OperationOutHandler.instance;
			vector[1][16] = mj.net.handler.game.SyncOptHandler.instance;
			vector[1][17] = mj.net.handler.game.SyncOptTimeHandler.instance;
			vector[1][18] = mj.net.handler.game.TingPaiHandler.instance;
			vector[1][19] = mj.net.handler.game.UserOfflineHandler.instance;
			vector[1][20] = mj.net.handler.game.UserPlaceMsgHandler.instance;
			vector[1][21] = mj.net.handler.game.VoteDelSelectHandler.instance;
			
			vector[7] = [];
			vector[7][1] = mj.net.handler.login.CreateRoomRetHandler.instance;
			vector[7][3] = mj.net.handler.login.DelRoomRetHandler.instance;
			vector[7][5] = mj.net.handler.login.ExitRoomRetHandler.instance;
			vector[7][7] = mj.net.handler.login.JoinRoomRetHandler.instance;
			vector[7][9] = mj.net.handler.login.LoginErrorHandler.instance;
			vector[7][10] = mj.net.handler.login.LoginRetHandler.instance;
			vector[7][11] = mj.net.handler.login.NoticeHandler.instance;
			vector[7][13] = mj.net.handler.login.PayHandler.instance;
			vector[7][15] = mj.net.handler.login.PongHandler.instance;
			vector[7][16] = mj.net.handler.login.RadioHandler.instance;
			vector[7][17] = mj.net.handler.login.RepeatLoginRetHandler.instance;
			vector[7][20] = mj.net.handler.login.RoomHistoryListRetHandler.instance;
			vector[7][22] = mj.net.handler.login.SendAuthCodeRetHandler.instance;
			vector[7][23] = mj.net.handler.login.StartGameHandler.instance;
			vector[7][24] = mj.net.handler.login.SysSettingHandler.instance;
		}
		
		public function getHandler(type:int, id:int):MessageHandler
		{
			return vector[type][id];
		}
	}
}