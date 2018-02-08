package mj.net.message.game;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 出牌回复
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class OperationOutRet extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 15;
	
	private int pai;
	
	public OperationOutRet(){
		
	}
	
	public OperationOutRet(int pai){
		this.pai = pai;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		pai = in.readInt();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeInt(getPai());
	}

	public int getPai() {
		return pai;
	}
	
	public void setPai(int pai) {
		this.pai = pai;
	}

	
	@Override
	public String toString() {
		return "OperationOutRet [pai=" + pai + ", ]";
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
