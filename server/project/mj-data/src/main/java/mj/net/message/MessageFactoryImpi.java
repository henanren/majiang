package mj.net.message;

import net.sf.cglib.core.Constants;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastConstructor;

import com.isnowfox.core.net.message.AbstractMessageFactory;

/**
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public final class MessageFactoryImpi extends AbstractMessageFactory {


	public static final int TYPE_GAME                                     			= 1;
	public static final int TYPE_LOGIN                                    			= 7;

	private static final MessageFactoryImpi instance = new MessageFactoryImpi();
	public static final MessageFactoryImpi getInstance(){
		return instance;
	}

	private MessageFactoryImpi(){}
	
	@Override
	protected FastConstructor[][] init() {
		FastConstructor[][] array = new FastConstructor[8][];
		
		array[1] = new FastConstructor[24];
		array[1][0] = FastClass.create(mj.net.message.game.GameChapterEnd.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][1] = FastClass.create(mj.net.message.game.GameChapterStart.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][2] = FastClass.create(mj.net.message.game.GameChapterStartRet.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][3] = FastClass.create(mj.net.message.game.GameDelRoom.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][4] = FastClass.create(mj.net.message.game.GameExitUser.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][5] = FastClass.create(mj.net.message.game.GameFanResult.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][6] = FastClass.create(mj.net.message.game.GameJoinRoom.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][7] = FastClass.create(mj.net.message.game.GameRoomInfo.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][8] = FastClass.create(mj.net.message.game.GameUserInfo.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][9] = FastClass.create(mj.net.message.game.MajiangChapterMsg.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][10] = FastClass.create(mj.net.message.game.OperationCPGH.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][11] = FastClass.create(mj.net.message.game.OperationCPGHRet.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][12] = FastClass.create(mj.net.message.game.OperationFaPai.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][13] = FastClass.create(mj.net.message.game.OperationFaPaiRet.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][14] = FastClass.create(mj.net.message.game.OperationOut.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][15] = FastClass.create(mj.net.message.game.OperationOutRet.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][16] = FastClass.create(mj.net.message.game.SyncOpt.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][17] = FastClass.create(mj.net.message.game.SyncOptTime.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][18] = FastClass.create(mj.net.message.game.TingPai.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][19] = FastClass.create(mj.net.message.game.UserOffline.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][20] = FastClass.create(mj.net.message.game.UserPlaceMsg.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][21] = FastClass.create(mj.net.message.game.VoteDelSelect.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][22] = FastClass.create(mj.net.message.game.VoteDelSelectRet.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[1][23] = FastClass.create(mj.net.message.game.VoteDelStart.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		
		array[7] = new FastConstructor[25];
		array[7][0] = FastClass.create(mj.net.message.login.CreateRoom.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][1] = FastClass.create(mj.net.message.login.CreateRoomRet.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][2] = FastClass.create(mj.net.message.login.DelRoom.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][3] = FastClass.create(mj.net.message.login.DelRoomRet.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][4] = FastClass.create(mj.net.message.login.ExitRoom.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][5] = FastClass.create(mj.net.message.login.ExitRoomRet.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][6] = FastClass.create(mj.net.message.login.JoinRoom.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][7] = FastClass.create(mj.net.message.login.JoinRoomRet.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][8] = FastClass.create(mj.net.message.login.Login.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][9] = FastClass.create(mj.net.message.login.LoginError.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][10] = FastClass.create(mj.net.message.login.LoginRet.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][11] = FastClass.create(mj.net.message.login.Notice.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][12] = FastClass.create(mj.net.message.login.OptionEntry.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][13] = FastClass.create(mj.net.message.login.Pay.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][14] = FastClass.create(mj.net.message.login.Ping.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][15] = FastClass.create(mj.net.message.login.Pong.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][16] = FastClass.create(mj.net.message.login.Radio.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][17] = FastClass.create(mj.net.message.login.RepeatLoginRet.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][18] = FastClass.create(mj.net.message.login.RoomHistory.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][19] = FastClass.create(mj.net.message.login.RoomHistoryList.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][20] = FastClass.create(mj.net.message.login.RoomHistoryListRet.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][21] = FastClass.create(mj.net.message.login.SendAuthCode.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][22] = FastClass.create(mj.net.message.login.SendAuthCodeRet.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][23] = FastClass.create(mj.net.message.login.StartGame.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		array[7][24] = FastClass.create(mj.net.message.login.SysSetting.class).getConstructor(Constants.EMPTY_CLASS_ARRAY);
		return array;
	}
}
