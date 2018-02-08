package mj.net.message.login;

import java.io.IOException;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.Output;
import com.isnowfox.core.io.ProtocolException;

import com.isnowfox.core.net.message.AbstractMessage;

public class RoomHistoryListRet extends AbstractMessage{
	public static final int TYPE			 = 7;
	public static final int ID				 = 20;
	
	private java.util.ArrayList<mj.net.message.login.RoomHistory> list;
	
	public RoomHistoryListRet(){
		
	}
	
	public RoomHistoryListRet(java.util.ArrayList<mj.net.message.login.RoomHistory> list){
		this.list = list;
	}
	
	@Override
	public void decode(Input in)  throws IOException, ProtocolException {
		
		int listLen = in.readInt();
		if(listLen == -1){
			list = null;
		}else{
			list = new java.util.ArrayList<mj.net.message.login.RoomHistory>(listLen);
			for(int i = 0; i < listLen; i++){
				RoomHistory listItem = new RoomHistory();
				listItem.decode(in);
				list.add(listItem);
			}
		}
	}

	@Override
	public void encode(Output out)  throws IOException, ProtocolException {
		
		if(list == null){
			out.writeInt(-1);
		}else{
			java.util.ArrayList<mj.net.message.login.RoomHistory> listList = getList();
			int listLen = listList.size();
			out.writeInt(listLen);
			for(RoomHistory listItem: listList){
				listItem.encode(out);
			}
		}
	}

	public java.util.ArrayList<mj.net.message.login.RoomHistory> getList() {
		return list;
	}
	
	public void setList(java.util.ArrayList<mj.net.message.login.RoomHistory> list) {
		this.list = list;
	}

	
	public void addList(RoomHistory list) {
		if(this.list == null){
			this.list = new java.util.ArrayList<mj.net.message.login.RoomHistory>();
		}
		this.list.add(list);
	}
	
	@Override
	public String toString() {
		return "RoomHistoryListRet [list=" + list + ", ]";
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
