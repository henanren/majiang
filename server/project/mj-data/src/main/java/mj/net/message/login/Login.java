package mj.net.message.login;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 登陆信息
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class Login extends AbstractMessage{
	public static final int TYPE			 = 7;
	public static final int ID				 = 8;
	
	/**
	 * SMS 短信登录，WEIXIN_CLIENT 微信客户端，TOKEN token登录
	 */
	private String type;
	private String openId;
	private String code;
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String latitude;
	
	public Login(){
		
	}
	
	public Login(String type, String openId, String code, String longitude, String latitude){
		this.type = type;
		this.openId = openId;
		this.code = code;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		type = in.readString();
		openId = in.readString();
		code = in.readString();
		longitude = in.readString();
		latitude = in.readString();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeString(getType());
		out.writeString(getOpenId());
		out.writeString(getCode());
		out.writeString(getLongitude());
		out.writeString(getLatitude());
	}

	/**
	 * SMS 短信登录，WEIXIN_CLIENT 微信客户端，TOKEN token登录
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * SMS 短信登录，WEIXIN_CLIENT 微信客户端，TOKEN token登录
	 */
	public void setType(String type) {
		this.type = type;
	}

	public String getOpenId() {
		return openId;
	}
	
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 经度
	 */
	public String getLongitude() {
		return longitude;
	}
	
	/**
	 * 经度
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * 纬度
	 */
	public String getLatitude() {
		return latitude;
	}
	
	/**
	 * 纬度
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	
	@Override
	public String toString() {
		return "Login [type=" + type + ",openId=" + openId + ",code=" + code + ",longitude=" + longitude + ",latitude=" + latitude + ", ]";
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
