package mj.net.message.login;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 登陆结果
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class RepeatLoginRet extends AbstractMessage{
	public static final int TYPE			 = 7;
	public static final int ID				 = 17;
	
	
	public RepeatLoginRet(){
		
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
	}

	
	@Override
	public String toString() {
		return "RepeatLoginRet [ ]";
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
