package mj.net.message.login;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * pong
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class Pay extends AbstractMessage{
	public static final int TYPE			 = 7;
	public static final int ID				 = 13;
	
	private int gold;
	
	public Pay(){
		
	}
	
	public Pay(int gold){
		this.gold = gold;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		gold = in.readInt();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeInt(getGold());
	}

	public int getGold() {
		return gold;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}

	
	@Override
	public String toString() {
		return "Pay [gold=" + gold + ", ]";
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
