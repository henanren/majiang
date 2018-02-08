package mj.net.message.game;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

/**
 * 同步操作
 * 
 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 * @author isnowfox消息生成器
 */
public class SyncOptTime extends AbstractMessage{
	public static final int TYPE			 = 1;
	public static final int ID				 = 17;
	
	/**
	 * 位置
	 */
	private int index;
	/**
	 * 毫秒
	 */
	private int leftTime;
	
	public SyncOptTime(){
		
	}
	
	public SyncOptTime(int index, int leftTime){
		this.index = index;
		this.leftTime = leftTime;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		index = in.readInt();
		leftTime = in.readInt();
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		out.writeInt(getIndex());
		out.writeInt(getLeftTime());
	}

	/**
	 * 位置
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * 位置
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 毫秒
	 */
	public int getLeftTime() {
		return leftTime;
	}
	
	/**
	 * 毫秒
	 */
	public void setLeftTime(int leftTime) {
		this.leftTime = leftTime;
	}

	
	@Override
	public String toString() {
		return "SyncOptTime [index=" + index + ",leftTime=" + leftTime + ", ]";
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
