package mj.net.message.login;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 发送验证码
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class SendAuthCode extends AbstractMessage{
	public static final int TYPE			 = 7;
	public static final int ID				 = 21;
	
	private String phone;
	
	public SendAuthCode(){
		
	}
	
	public SendAuthCode(String phone){
		this.phone = phone;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		phone = in.readString();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeString(getPhone());
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	@Override
	public String toString() {
		return "SendAuthCode [phone=" + phone + ", ]";
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
