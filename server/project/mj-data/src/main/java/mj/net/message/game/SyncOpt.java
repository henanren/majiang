package mj.net.message.game;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 同步操作
 * FA:发牌，OUT:打牌,OUT_OK:打牌成功，没人用这个哎,CHI:吃,PENG:碰,AN_GANG:暗杠牌,XIAO_MING_GANG:暗杠牌,DA_MING_GANG:暗杠牌,HU:胡牌
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class SyncOpt extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 16;
	
	private String opt;
	/**
	 * 位置
	 */
	private int index;
	private int pai;
	private int[] chi;
	
	public SyncOpt(){
		
	}
	
	public SyncOpt(String opt, int index, int pai, int[] chi){
		this.opt = opt;
		this.index = index;
		this.pai = pai;
		this.chi = chi;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		opt = in.readString();
		index = in.readInt();
		pai = in.readInt();
		chi = in.readIntArray();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeString(getOpt());
		out.writeInt(getIndex());
		out.writeInt(getPai());
		out.writeIntArray(getChi());
	}

	public String getOpt() {
		return opt;
	}
	
	public void setOpt(String opt) {
		this.opt = opt;
	}

	/**
	 * 位置
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * 位置
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	public int getPai() {
		return pai;
	}
	
	public void setPai(int pai) {
		this.pai = pai;
	}

	public int[] getChi() {
		return chi;
	}
	
	public void setChi(int[] chi) {
		this.chi = chi;
	}

	
	@Override
	public String toString() {
		return "SyncOpt [opt=" + opt + ",index=" + index + ",pai=" + pai + ",chi=" + chi + ", ]";
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
