package mj.net.message.game;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 通知用户出牌
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class TingPai extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 18;
	
	private int[] pais;
	
	public TingPai(){
		
	}
	
	public TingPai(int[] pais){
		this.pais = pais;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		pais = in.readIntArray();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeIntArray(getPais());
	}

	public int[] getPais() {
		return pais;
	}
	
	public void setPais(int[] pais) {
		this.pais = pais;
	}

	
	@Override
	public String toString() {
		return "TingPai [pais=" + pais + ", ]";
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
