package mj.net.message.game;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 自己的发牌的信息
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class OperationFaPaiRet extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 13;
	
	/**
	 * OUT:打牌,AN_GANG:暗杠牌,XIAO_MING_GANG:暗杠牌,HU:胡牌
	 */
	private String opt;
	private int pai;
	
	public OperationFaPaiRet(){
		
	}
	
	public OperationFaPaiRet(String opt, int pai){
		this.opt = opt;
		this.pai = pai;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		opt = in.readString();
		pai = in.readInt();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeString(getOpt());
		out.writeInt(getPai());
	}

	/**
	 * OUT:打牌,AN_GANG:暗杠牌,XIAO_MING_GANG:暗杠牌,HU:胡牌
	 */
	public String getOpt() {
		return opt;
	}
	
	/**
	 * OUT:打牌,AN_GANG:暗杠牌,XIAO_MING_GANG:暗杠牌,HU:胡牌
	 */
	public void setOpt(String opt) {
		this.opt = opt;
	}

	public int getPai() {
		return pai;
	}
	
	public void setPai(int pai) {
		this.pai = pai;
	}

	
	@Override
	public String toString() {
		return "OperationFaPaiRet [opt=" + opt + ",pai=" + pai + ", ]";
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
