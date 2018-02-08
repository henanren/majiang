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
public class MajiangChapterMsg extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 9;
	
	/**
	 * 剩余张数
	 */
	private int freeLength;
	/**
	 * 保留张数
	 */
	private int baoliuLength;
	/**
	 * 会儿牌
	 */
	private int[] huiEr;
	/**
	 * 变化类型！！
	 */
	private String bianType;
	/**
	 * 变化类型！！
	 */
	private int bianSource;
	private java.util.ArrayList<mj.net.message.game.UserPlaceMsg> userPlace;
	/**
	 * 当前操作用户
	 */
	private int currentIndex;
	/**
	 * 局数, 0开始
	 */
	private int chapterNums;
	/**
	 * 局数, 0开始
	 */
	private int chapterNumsMax;
	/**
	 * 圈index 0 东 1南 2西 3北 逆时针顺序
	 */
	private int quanIndex;
	/**
	 * 庄index 0 东 1南 2西 3北 逆时针顺序
	 */
	private int zhuangIndex;
	private mj.net.message.game.OperationCPGH optCpgh;
	private mj.net.message.game.OperationFaPai optFaPai;
	private mj.net.message.game.OperationOut optOut;
	private mj.net.message.game.SyncOptTime syncOptTime;
	private mj.net.message.game.GameChapterEnd gameChapterEnd;
	private mj.net.message.game.TingPai tingPai;
	
	public MajiangChapterMsg(){
		
	}
	
	public MajiangChapterMsg(int freeLength, int baoliuLength, int[] huiEr, String bianType, int bianSource, java.util.ArrayList<mj.net.message.game.UserPlaceMsg> userPlace, int currentIndex, int chapterNums, int chapterNumsMax, int quanIndex, int zhuangIndex, mj.net.message.game.OperationCPGH optCpgh, mj.net.message.game.OperationFaPai optFaPai, mj.net.message.game.OperationOut optOut, mj.net.message.game.SyncOptTime syncOptTime, mj.net.message.game.GameChapterEnd gameChapterEnd, mj.net.message.game.TingPai tingPai){
		this.freeLength = freeLength;
		this.baoliuLength = baoliuLength;
		this.huiEr = huiEr;
		this.bianType = bianType;
		this.bianSource = bianSource;
		this.userPlace = userPlace;
		this.currentIndex = currentIndex;
		this.chapterNums = chapterNums;
		this.chapterNumsMax = chapterNumsMax;
		this.quanIndex = quanIndex;
		this.zhuangIndex = zhuangIndex;
		this.optCpgh = optCpgh;
		this.optFaPai = optFaPai;
		this.optOut = optOut;
		this.syncOptTime = syncOptTime;
		this.gameChapterEnd = gameChapterEnd;
		this.tingPai = tingPai;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		freeLength = in.readInt();
		baoliuLength = in.readInt();
		huiEr = in.readIntArray();
		bianType = in.readString();
		bianSource = in.readInt();
		
		int userPlaceLen = in.readInt();
		if(userPlaceLen == -1){
			userPlace = null;
		}else{
			userPlace = new java.util.ArrayList<mj.net.message.game.UserPlaceMsg>(userPlaceLen);
			for(int i = 0; i < userPlaceLen; i++){
				UserPlaceMsg userPlaceItem = new UserPlaceMsg();
				userPlaceItem.decode(in);
				userPlace.add(userPlaceItem);
			}
		}
		currentIndex = in.readInt();
		chapterNums = in.readInt();
		chapterNumsMax = in.readInt();
		quanIndex = in.readInt();
		zhuangIndex = in.readInt();
		
		boolean optCpghIsNotNull = in.readBoolean();
		if(optCpghIsNotNull){
			optCpgh = new mj.net.message.game.OperationCPGH();
			optCpgh.decode(in);
		}else{
			optCpgh = null;
		}
		
		boolean optFaPaiIsNotNull = in.readBoolean();
		if(optFaPaiIsNotNull){
			optFaPai = new mj.net.message.game.OperationFaPai();
			optFaPai.decode(in);
		}else{
			optFaPai = null;
		}
		
		boolean optOutIsNotNull = in.readBoolean();
		if(optOutIsNotNull){
			optOut = new mj.net.message.game.OperationOut();
			optOut.decode(in);
		}else{
			optOut = null;
		}
		
		boolean syncOptTimeIsNotNull = in.readBoolean();
		if(syncOptTimeIsNotNull){
			syncOptTime = new mj.net.message.game.SyncOptTime();
			syncOptTime.decode(in);
		}else{
			syncOptTime = null;
		}
		
		boolean gameChapterEndIsNotNull = in.readBoolean();
		if(gameChapterEndIsNotNull){
			gameChapterEnd = new mj.net.message.game.GameChapterEnd();
			gameChapterEnd.decode(in);
		}else{
			gameChapterEnd = null;
		}
		
		boolean tingPaiIsNotNull = in.readBoolean();
		if(tingPaiIsNotNull){
			tingPai = new mj.net.message.game.TingPai();
			tingPai.decode(in);
		}else{
			tingPai = null;
		}
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeInt(getFreeLength());
		out.writeInt(getBaoliuLength());
		out.writeIntArray(getHuiEr());
		out.writeString(getBianType());
		out.writeInt(getBianSource());
		
		if(userPlace == null){
			out.writeInt(-1);
		}else{
			java.util.ArrayList<mj.net.message.game.UserPlaceMsg> userPlaceList = getUserPlace();
			int userPlaceLen = userPlaceList.size();
			out.writeInt(userPlaceLen);
			for(UserPlaceMsg userPlaceItem: userPlaceList){
				userPlaceItem.encode(out);
			}
		}
		out.writeInt(getCurrentIndex());
		out.writeInt(getChapterNums());
		out.writeInt(getChapterNumsMax());
		out.writeInt(getQuanIndex());
		out.writeInt(getZhuangIndex());
		mj.net.message.game.OperationCPGH optCpghItem = getOptCpgh();
		if(optCpghItem  == null){
			out.writeBoolean(false);
		}else{
			out.writeBoolean(true);
			optCpghItem.encode(out);
		}
		mj.net.message.game.OperationFaPai optFaPaiItem = getOptFaPai();
		if(optFaPaiItem  == null){
			out.writeBoolean(false);
		}else{
			out.writeBoolean(true);
			optFaPaiItem.encode(out);
		}
		mj.net.message.game.OperationOut optOutItem = getOptOut();
		if(optOutItem  == null){
			out.writeBoolean(false);
		}else{
			out.writeBoolean(true);
			optOutItem.encode(out);
		}
		mj.net.message.game.SyncOptTime syncOptTimeItem = getSyncOptTime();
		if(syncOptTimeItem  == null){
			out.writeBoolean(false);
		}else{
			out.writeBoolean(true);
			syncOptTimeItem.encode(out);
		}
		mj.net.message.game.GameChapterEnd gameChapterEndItem = getGameChapterEnd();
		if(gameChapterEndItem  == null){
			out.writeBoolean(false);
		}else{
			out.writeBoolean(true);
			gameChapterEndItem.encode(out);
		}
		mj.net.message.game.TingPai tingPaiItem = getTingPai();
		if(tingPaiItem  == null){
			out.writeBoolean(false);
		}else{
			out.writeBoolean(true);
			tingPaiItem.encode(out);
		}
	}

	/**
	 * 剩余张数
	 */
	public int getFreeLength() {
		return freeLength;
	}
	
	/**
	 * 剩余张数
	 */
	public void setFreeLength(int freeLength) {
		this.freeLength = freeLength;
	}

	/**
	 * 保留张数
	 */
	public int getBaoliuLength() {
		return baoliuLength;
	}
	
	/**
	 * 保留张数
	 */
	public void setBaoliuLength(int baoliuLength) {
		this.baoliuLength = baoliuLength;
	}

	/**
	 * 会儿牌
	 */
	public int[] getHuiEr() {
		return huiEr;
	}
	
	/**
	 * 会儿牌
	 */
	public void setHuiEr(int[] huiEr) {
		this.huiEr = huiEr;
	}

	/**
	 * 变化类型！！
	 */
	public String getBianType() {
		return bianType;
	}
	
	/**
	 * 变化类型！！
	 */
	public void setBianType(String bianType) {
		this.bianType = bianType;
	}

	/**
	 * 变化类型！！
	 */
	public int getBianSource() {
		return bianSource;
	}
	
	/**
	 * 变化类型！！
	 */
	public void setBianSource(int bianSource) {
		this.bianSource = bianSource;
	}

	public java.util.ArrayList<mj.net.message.game.UserPlaceMsg> getUserPlace() {
		return userPlace;
	}
	
	public void setUserPlace(java.util.ArrayList<mj.net.message.game.UserPlaceMsg> userPlace) {
		this.userPlace = userPlace;
	}

	/**
	 * 当前操作用户
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}
	
	/**
	 * 当前操作用户
	 */
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	/**
	 * 局数, 0开始
	 */
	public int getChapterNums() {
		return chapterNums;
	}
	
	/**
	 * 局数, 0开始
	 */
	public void setChapterNums(int chapterNums) {
		this.chapterNums = chapterNums;
	}

	/**
	 * 局数, 0开始
	 */
	public int getChapterNumsMax() {
		return chapterNumsMax;
	}
	
	/**
	 * 局数, 0开始
	 */
	public void setChapterNumsMax(int chapterNumsMax) {
		this.chapterNumsMax = chapterNumsMax;
	}

	/**
	 * 圈index 0 东 1南 2西 3北 逆时针顺序
	 */
	public int getQuanIndex() {
		return quanIndex;
	}
	
	/**
	 * 圈index 0 东 1南 2西 3北 逆时针顺序
	 */
	public void setQuanIndex(int quanIndex) {
		this.quanIndex = quanIndex;
	}

	/**
	 * 庄index 0 东 1南 2西 3北 逆时针顺序
	 */
	public int getZhuangIndex() {
		return zhuangIndex;
	}
	
	/**
	 * 庄index 0 东 1南 2西 3北 逆时针顺序
	 */
	public void setZhuangIndex(int zhuangIndex) {
		this.zhuangIndex = zhuangIndex;
	}

	public mj.net.message.game.OperationCPGH getOptCpgh() {
		return optCpgh;
	}
	
	public void setOptCpgh(mj.net.message.game.OperationCPGH optCpgh) {
		this.optCpgh = optCpgh;
	}

	public mj.net.message.game.OperationFaPai getOptFaPai() {
		return optFaPai;
	}
	
	public void setOptFaPai(mj.net.message.game.OperationFaPai optFaPai) {
		this.optFaPai = optFaPai;
	}

	public mj.net.message.game.OperationOut getOptOut() {
		return optOut;
	}
	
	public void setOptOut(mj.net.message.game.OperationOut optOut) {
		this.optOut = optOut;
	}

	public mj.net.message.game.SyncOptTime getSyncOptTime() {
		return syncOptTime;
	}
	
	public void setSyncOptTime(mj.net.message.game.SyncOptTime syncOptTime) {
		this.syncOptTime = syncOptTime;
	}

	public mj.net.message.game.GameChapterEnd getGameChapterEnd() {
		return gameChapterEnd;
	}
	
	public void setGameChapterEnd(mj.net.message.game.GameChapterEnd gameChapterEnd) {
		this.gameChapterEnd = gameChapterEnd;
	}

	public mj.net.message.game.TingPai getTingPai() {
		return tingPai;
	}
	
	public void setTingPai(mj.net.message.game.TingPai tingPai) {
		this.tingPai = tingPai;
	}

	
	public void addUserPlace(UserPlaceMsg userPlace) {
		if(this.userPlace == null){
			this.userPlace = new java.util.ArrayList<mj.net.message.game.UserPlaceMsg>();
		}
		this.userPlace.add(userPlace);
	}
	
	@Override
	public String toString() {
		return "MajiangChapterMsg [freeLength=" + freeLength + ",baoliuLength=" + baoliuLength + ",huiEr=" + huiEr + ",bianType=" + bianType + ",bianSource=" + bianSource + ",userPlace=" + userPlace + ",currentIndex=" + currentIndex + ",chapterNums=" + chapterNums + ",chapterNumsMax=" + chapterNumsMax + ",quanIndex=" + quanIndex + ",zhuangIndex=" + zhuangIndex + ",optCpgh=" + optCpgh + ",optFaPai=" + optFaPai + ",optOut=" + optOut + ",syncOptTime=" + syncOptTime + ",gameChapterEnd=" + gameChapterEnd + ",tingPai=" + tingPai + ", ]";
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
