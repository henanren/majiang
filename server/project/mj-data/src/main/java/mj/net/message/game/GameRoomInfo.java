package mj.net.message.game;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 同步游戏信息
 * 0东 1南 2西 3北 逆时针顺序
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class GameRoomInfo extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 7;
	
	private java.util.ArrayList<mj.net.message.game.GameUserInfo> sceneUser;
	private boolean start;
	private String roomCheckId;
	private int leftChapterNums;
	private int createUserId;
	private mj.net.message.game.MajiangChapterMsg chapter;
	
	public GameRoomInfo(){
		
	}
	
	public GameRoomInfo(java.util.ArrayList<mj.net.message.game.GameUserInfo> sceneUser, boolean start, String roomCheckId, int leftChapterNums, int createUserId, mj.net.message.game.MajiangChapterMsg chapter){
		this.sceneUser = sceneUser;
		this.start = start;
		this.roomCheckId = roomCheckId;
		this.leftChapterNums = leftChapterNums;
		this.createUserId = createUserId;
		this.chapter = chapter;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		
		int sceneUserLen = in.readInt();
		if(sceneUserLen == -1){
			sceneUser = null;
		}else{
			sceneUser = new java.util.ArrayList<mj.net.message.game.GameUserInfo>(sceneUserLen);
			for(int i = 0; i < sceneUserLen; i++){
				GameUserInfo sceneUserItem = new GameUserInfo();
				sceneUserItem.decode(in);
				sceneUser.add(sceneUserItem);
			}
		}
		start = in.readBoolean();
		roomCheckId = in.readString();
		leftChapterNums = in.readInt();
		createUserId = in.readInt();
		
		boolean chapterIsNotNull = in.readBoolean();
		if(chapterIsNotNull){
			chapter = new mj.net.message.game.MajiangChapterMsg();
			chapter.decode(in);
		}else{
			chapter = null;
		}
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		
		if(sceneUser == null){
			out.writeInt(-1);
		}else{
			java.util.ArrayList<mj.net.message.game.GameUserInfo> sceneUserList = getSceneUser();
			int sceneUserLen = sceneUserList.size();
			out.writeInt(sceneUserLen);
			for(GameUserInfo sceneUserItem: sceneUserList){
				sceneUserItem.encode(out);
			}
		}
		out.writeBoolean(getStart());
		out.writeString(getRoomCheckId());
		out.writeInt(getLeftChapterNums());
		out.writeInt(getCreateUserId());
		mj.net.message.game.MajiangChapterMsg chapterItem = getChapter();
		if(chapterItem  == null){
			out.writeBoolean(false);
		}else{
			out.writeBoolean(true);
			chapterItem.encode(out);
		}
	}

	public java.util.ArrayList<mj.net.message.game.GameUserInfo> getSceneUser() {
		return sceneUser;
	}
	
	public void setSceneUser(java.util.ArrayList<mj.net.message.game.GameUserInfo> sceneUser) {
		this.sceneUser = sceneUser;
	}

	public boolean getStart() {
		return start;
	}
	
	public void setStart(boolean start) {
		this.start = start;
	}

	public String getRoomCheckId() {
		return roomCheckId;
	}
	
	public void setRoomCheckId(String roomCheckId) {
		this.roomCheckId = roomCheckId;
	}

	public int getLeftChapterNums() {
		return leftChapterNums;
	}
	
	public void setLeftChapterNums(int leftChapterNums) {
		this.leftChapterNums = leftChapterNums;
	}

	public int getCreateUserId() {
		return createUserId;
	}
	
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public mj.net.message.game.MajiangChapterMsg getChapter() {
		return chapter;
	}
	
	public void setChapter(mj.net.message.game.MajiangChapterMsg chapter) {
		this.chapter = chapter;
	}

	
	public void addSceneUser(GameUserInfo sceneUser) {
		if(this.sceneUser == null){
			this.sceneUser = new java.util.ArrayList<mj.net.message.game.GameUserInfo>();
		}
		this.sceneUser.add(sceneUser);
	}
	
	@Override
	public String toString() {
		return "GameRoomInfo [sceneUser=" + sceneUser + ",start=" + start + ",roomCheckId=" + roomCheckId + ",leftChapterNums=" + leftChapterNums + ",createUserId=" + createUserId + ",chapter=" + chapter + ", ]";
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
