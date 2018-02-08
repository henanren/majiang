package mj.net.message.game;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 用户退出游戏
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class GameExitUser extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 4;
	
	private int userId;
	
	public GameExitUser(){
		
	}
	
	public GameExitUser(int userId){
		this.userId = userId;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		userId = in.readInt();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeInt(getUserId());
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	@Override
	public String toString() {
		return "GameExitUser [userId=" + userId + ", ]";
	}
	
	@Override
	public final int getMessageType() {
		return TYPE;
	}

	@Override
	public final int getMessageId() {
		return ID;
	}
}
