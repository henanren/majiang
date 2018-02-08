package mj.net.message.game;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 通知客户端"吃碰杠胡" 回复
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class OperationCPGHRet extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 11;
	
	private String opt;
	/**
	 * 如果是吃牌，回复吃的组合
	 */
	private int[] chi;
	
	public OperationCPGHRet(){
		
	}
	
	public OperationCPGHRet(String opt, int[] chi){
		this.opt = opt;
		this.chi = chi;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		opt = in.readString();
		chi = in.readIntArray();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeString(getOpt());
		out.writeIntArray(getChi());
	}

	public String getOpt() {
		return opt;
	}
	
	public void setOpt(String opt) {
		this.opt = opt;
	}

	/**
	 * 如果是吃牌，回复吃的组合
	 */
	public int[] getChi() {
		return chi;
	}
	
	/**
	 * 如果是吃牌，回复吃的组合
	 */
	public void setChi(int[] chi) {
		this.chi = chi;
	}

	
	@Override
	public String toString() {
		return "OperationCPGHRet [opt=" + opt + ",chi=" + chi + ", ]";
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
