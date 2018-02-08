package mj.net.message.login;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

public class Radio extends AbstractMessage{
	public static final int TYPE			 = 7;
	public static final int ID				 = 16;
	
	/**
	 * 广播（跑马灯）
	 */
	private String radio;
	
	public Radio(){
		
	}
	
	public Radio(String radio){
		this.radio = radio;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		radio = in.readString();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeString(getRadio());
	}

	/**
	 * 广播（跑马灯）
	 */
	public String getRadio() {
		return radio;
	}
	
	/**
	 * 广播（跑马灯）
	 */
	public void setRadio(String radio) {
		this.radio = radio;
	}

	
	@Override
	public String toString() {
		return "Radio [radio=" + radio + ", ]";
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
