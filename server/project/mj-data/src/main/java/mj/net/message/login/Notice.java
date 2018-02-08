package mj.net.message.login;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 通知
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class Notice extends AbstractMessage{
	public static final int TYPE			 = 7;
	public static final int ID				 = 11;
	
	/**
	 * 语言文件的key,或者内容字符串
	 */
	private String key;
	private String[] args;
	/**
	 * 0:横屏实时通知,1:悬停错误提示
	 */
	private int type;
	/**
	 * 是否需要重新启动游戏
	 */
	private boolean reboot;
	
	public Notice(){
		
	}
	
	public Notice(String key, String[] args, int type, boolean reboot){
		this.key = key;
		this.args = args;
		this.type = type;
		this.reboot = reboot;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		key = in.readString();
		args = in.readStringArray();
		type = in.readInt();
		reboot = in.readBoolean();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeString(getKey());
		out.writeStringArray(getArgs());
		out.writeInt(getType());
		out.writeBoolean(getReboot());
	}

	/**
	 * 语言文件的key,或者内容字符串
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * 语言文件的key,或者内容字符串
	 */
	public void setKey(String key) {
		this.key = key;
	}

	public String[] getArgs() {
		return args;
	}
	
	public void setArgs(String[] args) {
		this.args = args;
	}

	/**
	 * 0:横屏实时通知,1:悬停错误提示
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * 0:横屏实时通知,1:悬停错误提示
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * 是否需要重新启动游戏
	 */
	public boolean getReboot() {
		return reboot;
	}
	
	/**
	 * 是否需要重新启动游戏
	 */
	public void setReboot(boolean reboot) {
		this.reboot = reboot;
	}

	
	@Override
	public String toString() {
		return "Notice [key=" + key + ",args=" + args + ",type=" + type + ",reboot=" + reboot + ", ]";
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
