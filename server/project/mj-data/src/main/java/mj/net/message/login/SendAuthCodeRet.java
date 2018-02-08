package mj.net.message.login;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 发送验证码回执
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class SendAuthCodeRet extends AbstractMessage{
	public static final int TYPE			 = 7;
	public static final int ID				 = 22;
	
	private boolean success;
	
	public SendAuthCodeRet(){
		
	}
	
	public SendAuthCodeRet(boolean success){
		this.success = success;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		success = in.readBoolean();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeBoolean(getSuccess());
	}

	public boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}

	
	@Override
	public String toString() {
		return "SendAuthCodeRet [success=" + success + ", ]";
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
