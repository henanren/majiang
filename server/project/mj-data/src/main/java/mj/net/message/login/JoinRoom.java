package mj.net.message.login;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 创建房间
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class JoinRoom extends AbstractMessage{
	public static final int TYPE			 = 7;
	public static final int ID				 = 6;
	
	private String roomCheckId;
	
	public JoinRoom(){
		
	}
	
	public JoinRoom(String roomCheckId){
		this.roomCheckId = roomCheckId;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		roomCheckId = in.readString();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeString(getRoomCheckId());
	}

	public String getRoomCheckId() {
		return roomCheckId;
	}
	
	public void setRoomCheckId(String roomCheckId) {
		this.roomCheckId = roomCheckId;
	}

	
	@Override
	public String toString() {
		return "JoinRoom [roomCheckId=" + roomCheckId + ", ]";
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
