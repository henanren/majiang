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
public class GameChapterEnd extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 0;
	
	private int huPaiIndex;
	private int fangPaoIndex;
	private int zaMaType;
	private int[] zaMaPai;
	private int zaMaFan;
	private java.util.ArrayList<mj.net.message.game.GameFanResult> fanResults;
	
	public GameChapterEnd(){
		
	}
	
	public GameChapterEnd(int huPaiIndex, int fangPaoIndex, int zaMaType, int[] zaMaPai, int zaMaFan, java.util.ArrayList<mj.net.message.game.GameFanResult> fanResults){
		this.huPaiIndex = huPaiIndex;
		this.fangPaoIndex = fangPaoIndex;
		this.zaMaType = zaMaType;
		this.zaMaPai = zaMaPai;
		this.zaMaFan = zaMaFan;
		this.fanResults = fanResults;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		huPaiIndex = in.readInt();
		fangPaoIndex = in.readInt();
		zaMaType = in.readInt();
		zaMaPai = in.readIntArray();
		zaMaFan = in.readInt();
		
		int fanResultsLen = in.readInt();
		if(fanResultsLen == -1){
			fanResults = null;
		}else{
			fanResults = new java.util.ArrayList<mj.net.message.game.GameFanResult>(fanResultsLen);
			for(int i = 0; i < fanResultsLen; i++){
				GameFanResult fanResultsItem = new GameFanResult();
				fanResultsItem.decode(in);
				fanResults.add(fanResultsItem);
			}
		}
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeInt(getHuPaiIndex());
		out.writeInt(getFangPaoIndex());
		out.writeInt(getZaMaType());
		out.writeIntArray(getZaMaPai());
		out.writeInt(getZaMaFan());
		
		if(fanResults == null){
			out.writeInt(-1);
		}else{
			java.util.ArrayList<mj.net.message.game.GameFanResult> fanResultsList = getFanResults();
			int fanResultsLen = fanResultsList.size();
			out.writeInt(fanResultsLen);
			for(GameFanResult fanResultsItem: fanResultsList){
				fanResultsItem.encode(out);
			}
		}
	}

	public int getHuPaiIndex() {
		return huPaiIndex;
	}
	
	public void setHuPaiIndex(int huPaiIndex) {
		this.huPaiIndex = huPaiIndex;
	}

	public int getFangPaoIndex() {
		return fangPaoIndex;
	}
	
	public void setFangPaoIndex(int fangPaoIndex) {
		this.fangPaoIndex = fangPaoIndex;
	}

	public int getZaMaType() {
		return zaMaType;
	}
	
	public void setZaMaType(int zaMaType) {
		this.zaMaType = zaMaType;
	}

	public int[] getZaMaPai() {
		return zaMaPai;
	}
	
	public void setZaMaPai(int[] zaMaPai) {
		this.zaMaPai = zaMaPai;
	}

	public int getZaMaFan() {
		return zaMaFan;
	}
	
	public void setZaMaFan(int zaMaFan) {
		this.zaMaFan = zaMaFan;
	}

	public java.util.ArrayList<mj.net.message.game.GameFanResult> getFanResults() {
		return fanResults;
	}
	
	public void setFanResults(java.util.ArrayList<mj.net.message.game.GameFanResult> fanResults) {
		this.fanResults = fanResults;
	}

	
	public void addFanResults(GameFanResult fanResults) {
		if(this.fanResults == null){
			this.fanResults = new java.util.ArrayList<mj.net.message.game.GameFanResult>();
		}
		this.fanResults.add(fanResults);
	}
	
	@Override
	public String toString() {
		return "GameChapterEnd [huPaiIndex=" + huPaiIndex + ",fangPaoIndex=" + fangPaoIndex + ",zaMaType=" + zaMaType + ",zaMaPai=" + zaMaPai + ",zaMaFan=" + zaMaFan + ",fanResults=" + fanResults + ", ]";
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
