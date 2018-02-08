package mj.net.message.game;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

public class GameChapterStartRet extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 2;
	
	
	public GameChapterStartRet(){
		
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
	}

	
	@Override
	public String toString() {
		return "GameChapterStartRet [ ]";
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
