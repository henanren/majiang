package mj.net.message
{
	import com.isnowfox.core.SingletonError;
	import com.isnowfox.core.io.message.Message;
	import com.isnowfox.core.io.message.MessageFactory;
			
	import mj.net.message.game.GameChapterEnd;
	import mj.net.message.game.GameChapterStart;
	import mj.net.message.game.GameChapterStartRet;
	import mj.net.message.game.GameDelRoom;
	import mj.net.message.game.GameExitUser;
	import mj.net.message.game.GameFanResult;
	import mj.net.message.game.GameJoinRoom;
	import mj.net.message.game.GameRoomInfo;
	import mj.net.message.game.GameUserInfo;
	import mj.net.message.game.MajiangChapterMsg;
	import mj.net.message.game.OperationCPGH;
	import mj.net.message.game.OperationCPGHRet;
	import mj.net.message.game.OperationFaPai;
	import mj.net.message.game.OperationFaPaiRet;
	import mj.net.message.game.OperationOut;
	import mj.net.message.game.OperationOutRet;
	import mj.net.message.game.SyncOpt;
	import mj.net.message.game.SyncOptTime;
	import mj.net.message.game.TingPai;
	import mj.net.message.game.UserOffline;
	import mj.net.message.game.UserPlaceMsg;
	import mj.net.message.game.VoteDelSelect;
	import mj.net.message.game.VoteDelSelectRet;
	import mj.net.message.game.VoteDelStart;
			
	import mj.net.message.login.CreateRoom;
	import mj.net.message.login.CreateRoomRet;
	import mj.net.message.login.DelRoom;
	import mj.net.message.login.DelRoomRet;
	import mj.net.message.login.ExitRoom;
	import mj.net.message.login.ExitRoomRet;
	import mj.net.message.login.JoinRoom;
	import mj.net.message.login.JoinRoomRet;
	import mj.net.message.login.Login;
	import mj.net.message.login.LoginError;
	import mj.net.message.login.LoginRet;
	import mj.net.message.login.Notice;
	import mj.net.message.login.OptionEntry;
	import mj.net.message.login.Pay;
	import mj.net.message.login.Ping;
	import mj.net.message.login.Pong;
	import mj.net.message.login.Radio;
	import mj.net.message.login.RepeatLoginRet;
	import mj.net.message.login.RoomHistory;
	import mj.net.message.login.RoomHistoryList;
	import mj.net.message.login.RoomHistoryListRet;
	import mj.net.message.login.SendAuthCode;
	import mj.net.message.login.SendAuthCodeRet;
	import mj.net.message.login.StartGame;
	import mj.net.message.login.SysSetting;
	
	public class MessageFactoryImpi implements MessageFactory
	{
		public static const instance:MessageFactoryImpi = new MessageFactoryImpi();
		
		private const vector:Array = [];
		
		public function MessageFactoryImpi()
		{
			if(instance!=null){
				throw new SingletonError("ResourceManager 是单例模式");
			}
			
			vector[1] = [];
			vector[1][0] = mj.net.message.game.GameChapterEnd;
			vector[1][1] = mj.net.message.game.GameChapterStart;
			vector[1][2] = mj.net.message.game.GameChapterStartRet;
			vector[1][3] = mj.net.message.game.GameDelRoom;
			vector[1][4] = mj.net.message.game.GameExitUser;
			vector[1][5] = mj.net.message.game.GameFanResult;
			vector[1][6] = mj.net.message.game.GameJoinRoom;
			vector[1][7] = mj.net.message.game.GameRoomInfo;
			vector[1][8] = mj.net.message.game.GameUserInfo;
			vector[1][9] = mj.net.message.game.MajiangChapterMsg;
			vector[1][10] = mj.net.message.game.OperationCPGH;
			vector[1][11] = mj.net.message.game.OperationCPGHRet;
			vector[1][12] = mj.net.message.game.OperationFaPai;
			vector[1][13] = mj.net.message.game.OperationFaPaiRet;
			vector[1][14] = mj.net.message.game.OperationOut;
			vector[1][15] = mj.net.message.game.OperationOutRet;
			vector[1][16] = mj.net.message.game.SyncOpt;
			vector[1][17] = mj.net.message.game.SyncOptTime;
			vector[1][18] = mj.net.message.game.TingPai;
			vector[1][19] = mj.net.message.game.UserOffline;
			vector[1][20] = mj.net.message.game.UserPlaceMsg;
			vector[1][21] = mj.net.message.game.VoteDelSelect;
			vector[1][22] = mj.net.message.game.VoteDelSelectRet;
			vector[1][23] = mj.net.message.game.VoteDelStart;
			
			vector[7] = [];
			vector[7][0] = mj.net.message.login.CreateRoom;
			vector[7][1] = mj.net.message.login.CreateRoomRet;
			vector[7][2] = mj.net.message.login.DelRoom;
			vector[7][3] = mj.net.message.login.DelRoomRet;
			vector[7][4] = mj.net.message.login.ExitRoom;
			vector[7][5] = mj.net.message.login.ExitRoomRet;
			vector[7][6] = mj.net.message.login.JoinRoom;
			vector[7][7] = mj.net.message.login.JoinRoomRet;
			vector[7][8] = mj.net.message.login.Login;
			vector[7][9] = mj.net.message.login.LoginError;
			vector[7][10] = mj.net.message.login.LoginRet;
			vector[7][11] = mj.net.message.login.Notice;
			vector[7][12] = mj.net.message.login.OptionEntry;
			vector[7][13] = mj.net.message.login.Pay;
			vector[7][14] = mj.net.message.login.Ping;
			vector[7][15] = mj.net.message.login.Pong;
			vector[7][16] = mj.net.message.login.Radio;
			vector[7][17] = mj.net.message.login.RepeatLoginRet;
			vector[7][18] = mj.net.message.login.RoomHistory;
			vector[7][19] = mj.net.message.login.RoomHistoryList;
			vector[7][20] = mj.net.message.login.RoomHistoryListRet;
			vector[7][21] = mj.net.message.login.SendAuthCode;
			vector[7][22] = mj.net.message.login.SendAuthCodeRet;
			vector[7][23] = mj.net.message.login.StartGame;
			vector[7][24] = mj.net.message.login.SysSetting;
		}
		
		public function getMessage(type:int, id:int):Message
		{
			return new vector[type][id];
		}

		public function getMessageClass(type:int, id:int):Class
        {
            return vector[type][id];
        }
	}
}