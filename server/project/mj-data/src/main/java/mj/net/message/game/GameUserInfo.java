package mj.net.message.game;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 同步游戏信息
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class GameUserInfo extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 8;
	
	private String userName;
	private String avatar;
	/**
	 * 0:女生,1:男生,2:未知
	 */
	private int sex;
	private int gold;
	private int score;
	private int locationIndex;
	private int userId;
	private boolean online;
	private String ip;
	private String user0Distance;
	private String user1Distance;
	private String user2Distance;
	private String user3Distance;
	
	public GameUserInfo(){
		
	}
	
	public GameUserInfo(String userName, String avatar, int sex, int gold, int score, int locationIndex, int userId, boolean online, String ip, String user0Distance, String user1Distance, String user2Distance, String user3Distance){
		this.userName = userName;
		this.avatar = avatar;
		this.sex = sex;
		this.gold = gold;
		this.score = score;
		this.locationIndex = locationIndex;
		this.userId = userId;
		this.online = online;
		this.ip = ip;
		this.user0Distance = user0Distance;
		this.user1Distance = user1Distance;
		this.user2Distance = user2Distance;
		this.user3Distance = user3Distance;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		userName = in.readString();
		avatar = in.readString();
		sex = in.readInt();
		gold = in.readInt();
		score = in.readInt();
		locationIndex = in.readInt();
		userId = in.readInt();
		online = in.readBoolean();
		ip = in.readString();
		user0Distance = in.readString();
		user1Distance = in.readString();
		user2Distance = in.readString();
		user3Distance = in.readString();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeString(getUserName());
		out.writeString(getAvatar());
		out.writeInt(getSex());
		out.writeInt(getGold());
		out.writeInt(getScore());
		out.writeInt(getLocationIndex());
		out.writeInt(getUserId());
		out.writeBoolean(getOnline());
		out.writeString(getIp());
		out.writeString(getUser0Distance());
		out.writeString(getUser1Distance());
		out.writeString(getUser2Distance());
		out.writeString(getUser3Distance());
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * 0:女生,1:男生,2:未知
	 */
	public int getSex() {
		return sex;
	}
	
	/**
	 * 0:女生,1:男生,2:未知
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getGold() {
		return gold;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public int getLocationIndex() {
		return locationIndex;
	}
	
	public void setLocationIndex(int locationIndex) {
		this.locationIndex = locationIndex;
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean getOnline() {
		return online;
	}
	
	public void setOnline(boolean online) {
		this.online = online;
	}

	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUser0Distance() {
		return user0Distance;
	}
	
	public void setUser0Distance(String user0Distance) {
		this.user0Distance = user0Distance;
	}

	public String getUser1Distance() {
		return user1Distance;
	}
	
	public void setUser1Distance(String user1Distance) {
		this.user1Distance = user1Distance;
	}

	public String getUser2Distance() {
		return user2Distance;
	}
	
	public void setUser2Distance(String user2Distance) {
		this.user2Distance = user2Distance;
	}

	public String getUser3Distance() {
		return user3Distance;
	}
	
	public void setUser3Distance(String user3Distance) {
		this.user3Distance = user3Distance;
	}

	
	@Override
	public String toString() {
		return "GameUserInfo [userName=" + userName + ",avatar=" + avatar + ",sex=" + sex + ",gold=" + gold + ",score=" + score + ",locationIndex=" + locationIndex + ",userId=" + userId + ",online=" + online + ",ip=" + ip + ",user0Distance=" + user0Distance + ",user1Distance=" + user1Distance + ",user2Distance=" + user2Distance + ",user3Distance=" + user3Distance + ", ]";
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
