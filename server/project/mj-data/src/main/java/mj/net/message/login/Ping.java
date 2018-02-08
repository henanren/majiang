package mj.net.message.login;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * ping
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class Ping extends AbstractMessage{
	public static final int TYPE			 = 7;
	public static final int ID				 = 14;
	
	private String time;
	
	public Ping(){
		
	}
	
	public Ping(String time){
		this.time = time;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		time = in.readString();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeString(getTime());
	}

	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}

	
	@Override
	public String toString() {
		return "Ping [time=" + time + ", ]";
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
