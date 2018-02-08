package mj.net.message.game;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

public class VoteDelSelectRet extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 22;
	
	private boolean result;
	private int userId;
	
	public VoteDelSelectRet(){
		
	}
	
	public VoteDelSelectRet(boolean result, int userId){
		this.result = result;
		this.userId = userId;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		result = in.readBoolean();
		userId = in.readInt();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeBoolean(getResult());
		out.writeInt(getUserId());
	}

	public boolean getResult() {
		return result;
	}
	
	public void setResult(boolean result) {
		this.result = result;
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	@Override
	public String toString() {
		return "VoteDelSelectRet [result=" + result + ",userId=" + userId + ", ]";
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
