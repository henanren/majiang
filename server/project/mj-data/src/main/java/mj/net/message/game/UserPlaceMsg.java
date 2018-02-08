package mj.net.message.game;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 一局麻将的信息
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class UserPlaceMsg extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 20;
	
	private int[] shouPai;
	/**
	 * 别人的信息只显示手牌数量
	 */
	private int shouPaiLen;
	/**
	 * 已经显示的暗杠，如果是自己的则全部显示,不是自己的且如果还不能显示，那么传递-1
	 */
	private int[] anGang;
	private int[] xiaoMingGang;
	private int[] daMingGang;
	private int[] peng;
	/**
	 * 3个一组一组
	 */
	private int[] chi;
	/**
	 * 打出的牌
	 */
	private int[] outPai;
	
	public UserPlaceMsg(){
		
	}
	
	public UserPlaceMsg(int[] shouPai, int shouPaiLen, int[] anGang, int[] xiaoMingGang, int[] daMingGang, int[] peng, int[] chi, int[] outPai){
		this.shouPai = shouPai;
		this.shouPaiLen = shouPaiLen;
		this.anGang = anGang;
		this.xiaoMingGang = xiaoMingGang;
		this.daMingGang = daMingGang;
		this.peng = peng;
		this.chi = chi;
		this.outPai = outPai;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		shouPai = in.readIntArray();
		shouPaiLen = in.readInt();
		anGang = in.readIntArray();
		xiaoMingGang = in.readIntArray();
		daMingGang = in.readIntArray();
		peng = in.readIntArray();
		chi = in.readIntArray();
		outPai = in.readIntArray();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeIntArray(getShouPai());
		out.writeInt(getShouPaiLen());
		out.writeIntArray(getAnGang());
		out.writeIntArray(getXiaoMingGang());
		out.writeIntArray(getDaMingGang());
		out.writeIntArray(getPeng());
		out.writeIntArray(getChi());
		out.writeIntArray(getOutPai());
	}

	public int[] getShouPai() {
		return shouPai;
	}
	
	public void setShouPai(int[] shouPai) {
		this.shouPai = shouPai;
	}

	/**
	 * 别人的信息只显示手牌数量
	 */
	public int getShouPaiLen() {
		return shouPaiLen;
	}
	
	/**
	 * 别人的信息只显示手牌数量
	 */
	public void setShouPaiLen(int shouPaiLen) {
		this.shouPaiLen = shouPaiLen;
	}

	/**
	 * 已经显示的暗杠，如果是自己的则全部显示,不是自己的且如果还不能显示，那么传递-1
	 */
	public int[] getAnGang() {
		return anGang;
	}
	
	/**
	 * 已经显示的暗杠，如果是自己的则全部显示,不是自己的且如果还不能显示，那么传递-1
	 */
	public void setAnGang(int[] anGang) {
		this.anGang = anGang;
	}

	public int[] getXiaoMingGang() {
		return xiaoMingGang;
	}
	
	public void setXiaoMingGang(int[] xiaoMingGang) {
		this.xiaoMingGang = xiaoMingGang;
	}

	public int[] getDaMingGang() {
		return daMingGang;
	}
	
	public void setDaMingGang(int[] daMingGang) {
		this.daMingGang = daMingGang;
	}

	public int[] getPeng() {
		return peng;
	}
	
	public void setPeng(int[] peng) {
		this.peng = peng;
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

	/**
	 * 打出的牌
	 */
	public int[] getOutPai() {
		return outPai;
	}
	
	/**
	 * 打出的牌
	 */
	public void setOutPai(int[] outPai) {
		this.outPai = outPai;
	}

	
	@Override
	public String toString() {
		return "UserPlaceMsg [shouPai=" + shouPai + ",shouPaiLen=" + shouPaiLen + ",anGang=" + anGang + ",xiaoMingGang=" + xiaoMingGang + ",daMingGang=" + daMingGang + ",peng=" + peng + ",chi=" + chi + ",outPai=" + outPai + ", ]";
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
