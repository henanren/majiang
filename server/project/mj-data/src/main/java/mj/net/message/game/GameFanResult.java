package mj.net.message.game;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 牌局结束
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class GameFanResult extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 5;
	
	private int queTou;
	private int[] shunZi;
	private int[] keZi;
	private int[] shouPai;
	/**
	 * 会儿变化
	 */
	private int[] huiErBian;
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
	private String baseFanType;
	private String fanString;
	private int fan;
	private String userName;
	/**
	 * 改变值
	 */
	private int score;
	private int guaFengXiaYu;
	
	public GameFanResult(){
		
	}
	
	public GameFanResult(int queTou, int[] shunZi, int[] keZi, int[] shouPai, int[] huiErBian, int[] anGang, int[] xiaoMingGang, int[] daMingGang, int[] peng, int[] chi, String baseFanType, String fanString, int fan, String userName, int score, int guaFengXiaYu){
		this.queTou = queTou;
		this.shunZi = shunZi;
		this.keZi = keZi;
		this.shouPai = shouPai;
		this.huiErBian = huiErBian;
		this.anGang = anGang;
		this.xiaoMingGang = xiaoMingGang;
		this.daMingGang = daMingGang;
		this.peng = peng;
		this.chi = chi;
		this.baseFanType = baseFanType;
		this.fanString = fanString;
		this.fan = fan;
		this.userName = userName;
		this.score = score;
		this.guaFengXiaYu = guaFengXiaYu;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		queTou = in.readInt();
		shunZi = in.readIntArray();
		keZi = in.readIntArray();
		shouPai = in.readIntArray();
		huiErBian = in.readIntArray();
		anGang = in.readIntArray();
		xiaoMingGang = in.readIntArray();
		daMingGang = in.readIntArray();
		peng = in.readIntArray();
		chi = in.readIntArray();
		baseFanType = in.readString();
		fanString = in.readString();
		fan = in.readInt();
		userName = in.readString();
		score = in.readInt();
		guaFengXiaYu = in.readInt();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeInt(getQueTou());
		out.writeIntArray(getShunZi());
		out.writeIntArray(getKeZi());
		out.writeIntArray(getShouPai());
		out.writeIntArray(getHuiErBian());
		out.writeIntArray(getAnGang());
		out.writeIntArray(getXiaoMingGang());
		out.writeIntArray(getDaMingGang());
		out.writeIntArray(getPeng());
		out.writeIntArray(getChi());
		out.writeString(getBaseFanType());
		out.writeString(getFanString());
		out.writeInt(getFan());
		out.writeString(getUserName());
		out.writeInt(getScore());
		out.writeInt(getGuaFengXiaYu());
	}

	public int getQueTou() {
		return queTou;
	}
	
	public void setQueTou(int queTou) {
		this.queTou = queTou;
	}

	public int[] getShunZi() {
		return shunZi;
	}
	
	public void setShunZi(int[] shunZi) {
		this.shunZi = shunZi;
	}

	public int[] getKeZi() {
		return keZi;
	}
	
	public void setKeZi(int[] keZi) {
		this.keZi = keZi;
	}

	public int[] getShouPai() {
		return shouPai;
	}
	
	public void setShouPai(int[] shouPai) {
		this.shouPai = shouPai;
	}

	/**
	 * 会儿变化
	 */
	public int[] getHuiErBian() {
		return huiErBian;
	}
	
	/**
	 * 会儿变化
	 */
	public void setHuiErBian(int[] huiErBian) {
		this.huiErBian = huiErBian;
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

	public String getBaseFanType() {
		return baseFanType;
	}
	
	public void setBaseFanType(String baseFanType) {
		this.baseFanType = baseFanType;
	}

	public String getFanString() {
		return fanString;
	}
	
	public void setFanString(String fanString) {
		this.fanString = fanString;
	}

	public int getFan() {
		return fan;
	}
	
	public void setFan(int fan) {
		this.fan = fan;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 改变值
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * 改变值
	 */
	public void setScore(int score) {
		this.score = score;
	}

	public int getGuaFengXiaYu() {
		return guaFengXiaYu;
	}
	
	public void setGuaFengXiaYu(int guaFengXiaYu) {
		this.guaFengXiaYu = guaFengXiaYu;
	}

	
	@Override
	public String toString() {
		return "GameFanResult [queTou=" + queTou + ",shunZi=" + shunZi + ",keZi=" + keZi + ",shouPai=" + shouPai + ",huiErBian=" + huiErBian + ",anGang=" + anGang + ",xiaoMingGang=" + xiaoMingGang + ",daMingGang=" + daMingGang + ",peng=" + peng + ",chi=" + chi + ",baseFanType=" + baseFanType + ",fanString=" + fanString + ",fan=" + fan + ",userName=" + userName + ",score=" + score + ",guaFengXiaYu=" + guaFengXiaYu + ", ]";
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
