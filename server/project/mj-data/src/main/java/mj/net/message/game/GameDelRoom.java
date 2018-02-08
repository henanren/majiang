package mj.net.message.game;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 解散了房间，房间被删除
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class GameDelRoom extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 3;
	
	private boolean isEnd;
	private boolean isStart;
	
	public GameDelRoom(){
		
	}
	
	public GameDelRoom(boolean isEnd, boolean isStart){
		this.isEnd = isEnd;
		this.isStart = isStart;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		isEnd = in.readBoolean();
		isStart = in.readBoolean();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeBoolean(getIsEnd());
		out.writeBoolean(getIsStart());
	}

	public boolean getIsEnd() {
		return isEnd;
	}
	
	public void setIsEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public boolean getIsStart() {
		return isStart;
	}
	
	public void setIsStart(boolean isStart) {
		this.isStart = isStart;
	}

	
	@Override
	public String toString() {
		return "GameDelRoom [isEnd=" + isEnd + ",isStart=" + isStart + ", ]";
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
