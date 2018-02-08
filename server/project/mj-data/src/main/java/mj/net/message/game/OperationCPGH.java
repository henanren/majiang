package mj.net.message.game;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 通知客户端"吃碰杠胡"
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class OperationCPGH extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 10;
	
	/**
	 * 位置
	 */
	private int index;
	/**
	 * 3个一组一组
	 */
	private int[] chi;
	private boolean isPeng;
	private boolean isGang;
	private boolean isHu;
	private int pai;
	
	public OperationCPGH(){
		
	}
	
	public OperationCPGH(int index, int[] chi, boolean isPeng, boolean isGang, boolean isHu, int pai){
		this.index = index;
		this.chi = chi;
		this.isPeng = isPeng;
		this.isGang = isGang;
		this.isHu = isHu;
		this.pai = pai;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		index = in.readInt();
		chi = in.readIntArray();
		isPeng = in.readBoolean();
		isGang = in.readBoolean();
		isHu = in.readBoolean();
		pai = in.readInt();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeInt(getIndex());
		out.writeIntArray(getChi());
		out.writeBoolean(getIsPeng());
		out.writeBoolean(getIsGang());
		out.writeBoolean(getIsHu());
		out.writeInt(getPai());
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

	/**
	 * 3个一组一组
	 */
	public int[] getChi() {
		return chi;
	}
	
	/**
	 * 3个一组一组
	 */
	public void setChi(int[] chi) {
		this.chi = chi;
	}

	public boolean getIsPeng() {
		return isPeng;
	}
	
	public void setIsPeng(boolean isPeng) {
		this.isPeng = isPeng;
	}

	public boolean getIsGang() {
		return isGang;
	}
	
	public void setIsGang(boolean isGang) {
		this.isGang = isGang;
	}

	public boolean getIsHu() {
		return isHu;
	}
	
	public void setIsHu(boolean isHu) {
		this.isHu = isHu;
	}

	public int getPai() {
		return pai;
	}
	
	public void setPai(int pai) {
		this.pai = pai;
	}

	
	@Override
	public String toString() {
		return "OperationCPGH [index=" + index + ",chi=" + chi + ",isPeng=" + isPeng + ",isGang=" + isGang + ",isHu=" + isHu + ",pai=" + pai + ", ]";
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
