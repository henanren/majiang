package mj.net.message.login;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

public class OptionEntry extends AbstractMessage{
	public static final int TYPE			 = 7;
	public static final int ID				 = 12;
	
	private String key;
	private String value;
	
	public OptionEntry(){
		
	}
	
	public OptionEntry(String key, String value){
		this.key = key;
		this.value = value;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		key = in.readString();
		value = in.readString();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeString(getKey());
		out.writeString(getValue());
	}

	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	
	@Override
	public String toString() {
		return "OptionEntry [key=" + key + ",value=" + value + ", ]";
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
