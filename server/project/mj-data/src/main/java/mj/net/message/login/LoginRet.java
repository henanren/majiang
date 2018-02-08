package mj.net.message.login;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 登陆结果
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class LoginRet extends AbstractMessage{
	public static final int TYPE			 = 7;
	public static final int ID				 = 10;
	
	/**
	 * id
	 */
	private int id;
	/**
	 * 昵称
	 */
	private String name;
	/**
	 * 昵称
	 */
	private String openId;
	/**
	 * uuid
	 */
	private String uuid;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 0:女生,1:男生,2:未知
	 */
	private int sex;
	/**
	 * 如果用户进入过房间,未主动退出房间id
	 */
	private String roomCheckId;
	private int gold;
	private String loginToken;
	private String ip;
	
	public LoginRet(){
		
	}
	
	public LoginRet(int id, String name, String openId, String uuid, String avatar, int sex, String roomCheckId, int gold, String loginToken, String ip){
		this.id = id;
		this.name = name;
		this.openId = openId;
		this.uuid = uuid;
		this.avatar = avatar;
		this.sex = sex;
		this.roomCheckId = roomCheckId;
		this.gold = gold;
		this.loginToken = loginToken;
		this.ip = ip;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		id = in.readInt();
		name = in.readString();
		openId = in.readString();
		uuid = in.readString();
		avatar = in.readString();
		sex = in.readInt();
		roomCheckId = in.readString();
		gold = in.readInt();
		loginToken = in.readString();
		ip = in.readString();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeInt(getId());
		out.writeString(getName());
		out.writeString(getOpenId());
		out.writeString(getUuid());
		out.writeString(getAvatar());
		out.writeInt(getSex());
		out.writeString(getRoomCheckId());
		out.writeInt(getGold());
		out.writeString(getLoginToken());
		out.writeString(getIp());
	}

	/**
	 * id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 昵称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 昵称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 昵称
	 */
	public String getOpenId() {
		return openId;
	}
	
	/**
	 * 昵称
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * uuid
	 */
	public String getUuid() {
		return uuid;
	}
	
	/**
	 * uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 头像
	 */
	public String getAvatar() {
		return avatar;
	}
	
	/**
	 * 头像
	 */
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

	/**
	 * 如果用户进入过房间,未主动退出房间id
	 */
	public String getRoomCheckId() {
		return roomCheckId;
	}
	
	/**
	 * 如果用户进入过房间,未主动退出房间id
	 */
	public void setRoomCheckId(String roomCheckId) {
		this.roomCheckId = roomCheckId;
	}

	public int getGold() {
		return gold;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}

	public String getLoginToken() {
		return loginToken;
	}
	
	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	
	@Override
	public String toString() {
		return "LoginRet [id=" + id + ",name=" + name + ",openId=" + openId + ",uuid=" + uuid + ",avatar=" + avatar + ",sex=" + sex + ",roomCheckId=" + roomCheckId + ",gold=" + gold + ",loginToken=" + loginToken + ",ip=" + ip + ", ]";
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
