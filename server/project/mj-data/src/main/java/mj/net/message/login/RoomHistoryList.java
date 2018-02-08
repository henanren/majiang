package mj.net.message.login;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

public class RoomHistoryList extends AbstractMessage{
	public static final int TYPE			 = 7;
	public static final int ID				 = 19;
	
	
	public RoomHistoryList(){
		
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
	}

	
	@Override
	public String toString() {
		return "RoomHistoryList [ ]";
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
