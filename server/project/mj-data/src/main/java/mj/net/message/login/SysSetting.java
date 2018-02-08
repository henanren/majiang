package mj.net.message.login;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

public class SysSetting extends AbstractMessage{
	public static final int TYPE			 = 7;
	public static final int ID				 = 24;
	
	/**
	 * 广播（跑马灯）
	 */
	private String radio;
	private String notice;
	private String payInfo;
	private String agreement;
	
	public SysSetting(){
		
	}
	
	public SysSetting(String radio, String notice, String payInfo, String agreement){
		this.radio = radio;
		this.notice = notice;
		this.payInfo = payInfo;
		this.agreement = agreement;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		radio = in.readString();
		notice = in.readString();
		payInfo = in.readString();
		agreement = in.readString();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeString(getRadio());
		out.writeString(getNotice());
		out.writeString(getPayInfo());
		out.writeString(getAgreement());
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

	public String getNotice() {
		return notice;
	}
	
	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getPayInfo() {
		return payInfo;
	}
	
	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public String getAgreement() {
		return agreement;
	}
	
	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	
	@Override
	public String toString() {
		return "SysSetting [radio=" + radio + ",notice=" + notice + ",payInfo=" + payInfo + ",agreement=" + agreement + ", ]";
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
