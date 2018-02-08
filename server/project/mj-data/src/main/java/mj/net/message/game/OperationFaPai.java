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
public class OperationFaPai extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 12;
	
	/**
	 * 位置
	 */
	private int index;
	private int pai;
	private int[] anGang;
	private int[] mingGang;
	private boolean hu;
	
	public OperationFaPai(){
		
	}
	
	public OperationFaPai(int index, int pai, int[] anGang, int[] mingGang, boolean hu){
		this.index = index;
		this.pai = pai;
		this.anGang = anGang;
		this.mingGang = mingGang;
		this.hu = hu;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		index = in.readInt();
		pai = in.readInt();
		anGang = in.readIntArray();
		mingGang = in.readIntArray();
		hu = in.readBoolean();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeInt(getIndex());
		out.writeInt(getPai());
		out.writeIntArray(getAnGang());
		out.writeIntArray(getMingGang());
		out.writeBoolean(getHu());
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

	public int[] getAnGang() {
		return anGang;
	}
	
	public void setAnGang(int[] anGang) {
		this.anGang = anGang;
	}

	public int[] getMingGang() {
		return mingGang;
	}
	
	public void setMingGang(int[] mingGang) {
		this.mingGang = mingGang;
	}

	public boolean getHu() {
		return hu;
	}
	
	public void setHu(boolean hu) {
		this.hu = hu;
	}

	
	@Override
	public String toString() {
		return "OperationFaPai [index=" + index + ",pai=" + pai + ",anGang=" + anGang + ",mingGang=" + mingGang + ",hu=" + hu + ", ]";
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
