package  game.boss.dao.entity;

import java.beans.Transient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import org.forkjoin.core.dao.UniqueInfo;


import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;

import  org.forkjoin.core.dao.EntityObject;
import  org.forkjoin.core.dao.KeyObject;
import  org.forkjoin.core.dao.TableInfo;

public class RoomResultDO extends EntityObject<RoomResultDO, RoomResultDO.Key>{

	/**result id*/
	private int id;
	/**房间id*/
	private int roomId;
	/**房间的check-id,进入id,可以重复,但是不允许同时活跃状态的id 相同*/
	private String roomCheckId;
	private int sceneId;
	private java.util.Date createDate;
	/**用户0*/
	private int user0;
	/**用户1*/
	private int user1;
	/**用户2*/
	private int user2;
	/**用户3*/
	private int user3;
	/**用户0*/
	private String userName0;
	/**用户1*/
	private String userName1;
	/**用户2*/
	private String userName2;
	/**用户3*/
	private String userName3;
	/**积分0*/
	private int score0;
	/**积分1*/
	private int score1;
	/**积分2*/
	private int score2;
	/**积分3*/
	private int score3;
	private boolean huPai;
	private int huPaiIndex;
	private int zhuangIndex;
	private boolean isZiMo;
	/**会儿*/
	private int[] huiEr;
	private boolean gangShangHua;
	private boolean lastPai;
	private int fangPaoIndex;
	/**剩余牌*/
	private int[] left;
	/**用户的牌信息*/
	private mj.data.UserPaiInfo[] userPaiInfos;

	public static class Key implements KeyObject<RoomResultDO, RoomResultDO.Key>{
    	private int id;

		public Key() {
   		}

		public Key(int id) {
			this.id = id;
		}

		@JsonIgnore
		@Transient
		@Override
		public Object[] getQueryParams() {
			return new Object[]{
				getId()
			};
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}

        @Override
        public String toStringKey(){
            return String.valueOf(getId());

        }

		@Override
		public Table getTableInfo() {
			return TABLE_INFO;
		}

		@Override
		public String toString() {
			return "RoomResult[id:"+ id+ "]";
		}
	}

	@Override
	public Key getKey() {
		return new Key() {

			public int getId() {
				return id;
			}

			@Override
			public String toString() {
				return "RoomResult[id:"+ id+ "]";
			}
		};
	}




	public RoomResultDO() {
    }

	public RoomResultDO(int roomId,String roomCheckId,int sceneId,java.util.Date createDate,int user0,int user1,int user2,int user3,String userName0,String userName1,String userName2,String userName3,int score0,int score1,int score2,int score3,boolean huPai,int huPaiIndex,int zhuangIndex,boolean isZiMo,int[] huiEr,boolean gangShangHua,boolean lastPai,int fangPaoIndex,int[] left,mj.data.UserPaiInfo[] userPaiInfos) {
		this.roomId = roomId;
		this.roomCheckId = roomCheckId;
		this.sceneId = sceneId;
		this.createDate = createDate;
		this.user0 = user0;
		this.user1 = user1;
		this.user2 = user2;
		this.user3 = user3;
		this.userName0 = userName0;
		this.userName1 = userName1;
		this.userName2 = userName2;
		this.userName3 = userName3;
		this.score0 = score0;
		this.score1 = score1;
		this.score2 = score2;
		this.score3 = score3;
		this.huPai = huPai;
		this.huPaiIndex = huPaiIndex;
		this.zhuangIndex = zhuangIndex;
		this.isZiMo = isZiMo;
		this.huiEr = huiEr;
		this.gangShangHua = gangShangHua;
		this.lastPai = lastPai;
		this.fangPaoIndex = fangPaoIndex;
		this.left = left;
		this.userPaiInfos = userPaiInfos;
	}


	public RoomResultDO newInstance(){
		return new RoomResultDO();
	}

    public void setKey(Object key){
        this.id = ((Number)key).intValue();
    }

	/**
	 * result id
	 **/
	public int getId() {
		return id;
	}

	/**
	 * result id
	 **/
	public void setId(int id) {
		this.id = id;
		changeProperty("id",id);
	}

	/**
	 * 房间id
	 **/
	public int getRoomId() {
		return roomId;
	}

	/**
	 * 房间id
	 **/
	public void setRoomId(int roomId) {
		this.roomId = roomId;
		changeProperty("roomId",roomId);
	}

	/**
	 * 房间的check-id,进入id,可以重复,但是不允许同时活跃状态的id 相同
	 **/
	public String getRoomCheckId() {
		return roomCheckId;
	}

	/**
	 * 房间的check-id,进入id,可以重复,但是不允许同时活跃状态的id 相同
	 **/
	public void setRoomCheckId(String roomCheckId) {
		this.roomCheckId = roomCheckId;
		changeProperty("roomCheckId",roomCheckId);
	}

	public int getSceneId() {
		return sceneId;
	}

	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
		changeProperty("sceneId",sceneId);
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
		changeProperty("createDate",createDate);
	}

	/**
	 * 用户0
	 **/
	public int getUser0() {
		return user0;
	}

	/**
	 * 用户0
	 **/
	public void setUser0(int user0) {
		this.user0 = user0;
		changeProperty("user0",user0);
	}

	/**
	 * 用户1
	 **/
	public int getUser1() {
		return user1;
	}

	/**
	 * 用户1
	 **/
	public void setUser1(int user1) {
		this.user1 = user1;
		changeProperty("user1",user1);
	}

	/**
	 * 用户2
	 **/
	public int getUser2() {
		return user2;
	}

	/**
	 * 用户2
	 **/
	public void setUser2(int user2) {
		this.user2 = user2;
		changeProperty("user2",user2);
	}

	/**
	 * 用户3
	 **/
	public int getUser3() {
		return user3;
	}

	/**
	 * 用户3
	 **/
	public void setUser3(int user3) {
		this.user3 = user3;
		changeProperty("user3",user3);
	}

	/**
	 * 用户0
	 **/
	public String getUserName0() {
		return userName0;
	}

	/**
	 * 用户0
	 **/
	public void setUserName0(String userName0) {
		this.userName0 = userName0;
		changeProperty("userName0",userName0);
	}

	/**
	 * 用户1
	 **/
	public String getUserName1() {
		return userName1;
	}

	/**
	 * 用户1
	 **/
	public void setUserName1(String userName1) {
		this.userName1 = userName1;
		changeProperty("userName1",userName1);
	}

	/**
	 * 用户2
	 **/
	public String getUserName2() {
		return userName2;
	}

	/**
	 * 用户2
	 **/
	public void setUserName2(String userName2) {
		this.userName2 = userName2;
		changeProperty("userName2",userName2);
	}

	/**
	 * 用户3
	 **/
	public String getUserName3() {
		return userName3;
	}

	/**
	 * 用户3
	 **/
	public void setUserName3(String userName3) {
		this.userName3 = userName3;
		changeProperty("userName3",userName3);
	}

	/**
	 * 积分0
	 **/
	public int getScore0() {
		return score0;
	}

	/**
	 * 积分0
	 **/
	public void setScore0(int score0) {
		this.score0 = score0;
		changeProperty("score0",score0);
	}

	/**
	 * 积分1
	 **/
	public int getScore1() {
		return score1;
	}

	/**
	 * 积分1
	 **/
	public void setScore1(int score1) {
		this.score1 = score1;
		changeProperty("score1",score1);
	}

	/**
	 * 积分2
	 **/
	public int getScore2() {
		return score2;
	}

	/**
	 * 积分2
	 **/
	public void setScore2(int score2) {
		this.score2 = score2;
		changeProperty("score2",score2);
	}

	/**
	 * 积分3
	 **/
	public int getScore3() {
		return score3;
	}

	/**
	 * 积分3
	 **/
	public void setScore3(int score3) {
		this.score3 = score3;
		changeProperty("score3",score3);
	}

	public boolean getHuPai() {
		return huPai;
	}

	public void setHuPai(boolean huPai) {
		this.huPai = huPai;
		changeProperty("huPai",huPai);
	}

	public int getHuPaiIndex() {
		return huPaiIndex;
	}

	public void setHuPaiIndex(int huPaiIndex) {
		this.huPaiIndex = huPaiIndex;
		changeProperty("huPaiIndex",huPaiIndex);
	}

	public int getZhuangIndex() {
		return zhuangIndex;
	}

	public void setZhuangIndex(int zhuangIndex) {
		this.zhuangIndex = zhuangIndex;
		changeProperty("zhuangIndex",zhuangIndex);
	}

	public boolean getIsZiMo() {
		return isZiMo;
	}

	public void setIsZiMo(boolean isZiMo) {
		this.isZiMo = isZiMo;
		changeProperty("isZiMo",isZiMo);
	}

	/**
	 * 会儿
	 **/
	public int[] getHuiEr() {
		return huiEr;
	}

	/**
	 * 会儿
	 **/
	public void setHuiEr(int[] huiEr) {
		this.huiEr = huiEr;
		changeProperty("huiEr",huiEr);
	}

	public boolean getGangShangHua() {
		return gangShangHua;
	}

	public void setGangShangHua(boolean gangShangHua) {
		this.gangShangHua = gangShangHua;
		changeProperty("gangShangHua",gangShangHua);
	}

	public boolean getLastPai() {
		return lastPai;
	}

	public void setLastPai(boolean lastPai) {
		this.lastPai = lastPai;
		changeProperty("lastPai",lastPai);
	}

	public int getFangPaoIndex() {
		return fangPaoIndex;
	}

	public void setFangPaoIndex(int fangPaoIndex) {
		this.fangPaoIndex = fangPaoIndex;
		changeProperty("fangPaoIndex",fangPaoIndex);
	}

	/**
	 * 剩余牌
	 **/
	public int[] getLeft() {
		return left;
	}

	/**
	 * 剩余牌
	 **/
	public void setLeft(int[] left) {
		this.left = left;
		changeProperty("left",left);
	}

	/**
	 * 用户的牌信息
	 **/
	public mj.data.UserPaiInfo[] getUserPaiInfos() {
		return userPaiInfos;
	}

	/**
	 * 用户的牌信息
	 **/
	public void setUserPaiInfos(mj.data.UserPaiInfo[] userPaiInfos) {
		this.userPaiInfos = userPaiInfos;
		changeProperty("userPaiInfos",userPaiInfos);
	}

    @Override
    public Object get(String dbName){
        switch(dbName){
        case "id":
            return id;
        case "room_id":
            return roomId;
        case "room_check_id":
            return roomCheckId;
        case "scene_id":
            return sceneId;
        case "create_date":
            return createDate;
        case "user0":
            return user0;
        case "user1":
            return user1;
        case "user2":
            return user2;
        case "user3":
            return user3;
        case "userName0":
            return userName0;
        case "userName1":
            return userName1;
        case "userName2":
            return userName2;
        case "userName3":
            return userName3;
        case "score0":
            return score0;
        case "score1":
            return score1;
        case "score2":
            return score2;
        case "score3":
            return score3;
        case "hu_pai":
            return huPai;
        case "hu_pai_index":
            return huPaiIndex;
        case "zhuang_index":
            return zhuangIndex;
        case "is_zi_mo":
            return isZiMo;
        case "hui_er":
            return huiEr;
        case "gang_shang_hua":
            return gangShangHua;
        case "last_pai":
            return lastPai;
        case "fang_pao_index":
            return fangPaoIndex;
        case "left":
            return left;
        case "user_pai_infos":
            return userPaiInfos;
        default :
            return null;
        }
    }


	@Override
	public boolean set(String dbName, Object obj) {
		switch(dbName){
		case "id":
			id = (int)obj;
			return true;
		case "room_id":
			roomId = (int)obj;
			return true;
		case "room_check_id":
			roomCheckId = (String)obj;
			return true;
		case "scene_id":
			sceneId = (int)obj;
			return true;
		case "create_date":
			createDate = (java.util.Date)obj;
			return true;
		case "user0":
			user0 = (int)obj;
			return true;
		case "user1":
			user1 = (int)obj;
			return true;
		case "user2":
			user2 = (int)obj;
			return true;
		case "user3":
			user3 = (int)obj;
			return true;
		case "userName0":
			userName0 = (String)obj;
			return true;
		case "userName1":
			userName1 = (String)obj;
			return true;
		case "userName2":
			userName2 = (String)obj;
			return true;
		case "userName3":
			userName3 = (String)obj;
			return true;
		case "score0":
			score0 = (int)obj;
			return true;
		case "score1":
			score1 = (int)obj;
			return true;
		case "score2":
			score2 = (int)obj;
			return true;
		case "score3":
			score3 = (int)obj;
			return true;
		case "hu_pai":
			huPai = (boolean)obj;
			return true;
		case "hu_pai_index":
			huPaiIndex = (int)obj;
			return true;
		case "zhuang_index":
			zhuangIndex = (int)obj;
			return true;
		case "is_zi_mo":
			isZiMo = (boolean)obj;
			return true;
		case "hui_er":
			huiEr = (int[])obj;
			return true;
		case "gang_shang_hua":
			gangShangHua = (boolean)obj;
			return true;
		case "last_pai":
			lastPai = (boolean)obj;
			return true;
		case "fang_pao_index":
			fangPaoIndex = (int)obj;
			return true;
		case "left":
			left = (int[])obj;
			return true;
		case "user_pai_infos":
			userPaiInfos = (mj.data.UserPaiInfo[])obj;
			return true;
		default :
			return false;
		}
	}

	@Override
	public String toString() {
		return "RoomResult[id:"+ id+",roomId:"+ roomId+",roomCheckId:"+ (roomCheckId == null ?"null":roomCheckId.substring(0, Math.min(roomCheckId.length(), 64)))+",sceneId:"+ sceneId+",createDate:"+ createDate+",user0:"+ user0+",user1:"+ user1+",user2:"+ user2+",user3:"+ user3+",userName0:"+ (userName0 == null ?"null":userName0.substring(0, Math.min(userName0.length(), 64)))+",userName1:"+ (userName1 == null ?"null":userName1.substring(0, Math.min(userName1.length(), 64)))+",userName2:"+ (userName2 == null ?"null":userName2.substring(0, Math.min(userName2.length(), 64)))+",userName3:"+ (userName3 == null ?"null":userName3.substring(0, Math.min(userName3.length(), 64)))+",score0:"+ score0+",score1:"+ score1+",score2:"+ score2+",score3:"+ score3+",huPai:"+ huPai+",huPaiIndex:"+ huPaiIndex+",zhuangIndex:"+ zhuangIndex+",isZiMo:"+ isZiMo+",huiEr:"+ huiEr+",gangShangHua:"+ gangShangHua+",lastPai:"+ lastPai+",fangPaoIndex:"+ fangPaoIndex+",left:"+ left+",userPaiInfos:"+ userPaiInfos+ "]";
	}

	@Override
	@JsonIgnore
    @Transient
	public Table getTableInfo() {
		return TABLE_INFO;
	}


	public static final Table TABLE_INFO= new Table();

	public static final class Table extends TableInfo<RoomResultDO ,Key>{
		public static final String DB_TABLE_NAME = "room_result";
		private Map<String, UniqueInfo> uniqueMap;

		public static final String ID = "id";
		public static final String ROOM_ID = "room_id";
		public static final String ROOM_CHECK_ID = "room_check_id";
		public static final String SCENE_ID = "scene_id";
		public static final String CREATE_DATE = "create_date";
		public static final String USER0 = "user0";
		public static final String USER1 = "user1";
		public static final String USER2 = "user2";
		public static final String USER3 = "user3";
		public static final String USERNAME0 = "userName0";
		public static final String USERNAME1 = "userName1";
		public static final String USERNAME2 = "userName2";
		public static final String USERNAME3 = "userName3";
		public static final String SCORE0 = "score0";
		public static final String SCORE1 = "score1";
		public static final String SCORE2 = "score2";
		public static final String SCORE3 = "score3";
		public static final String HU_PAI = "hu_pai";
		public static final String HU_PAI_INDEX = "hu_pai_index";
		public static final String ZHUANG_INDEX = "zhuang_index";
		public static final String IS_ZI_MO = "is_zi_mo";
		public static final String HUI_ER = "hui_er";
		public static final String GANG_SHANG_HUA = "gang_shang_hua";
		public static final String LAST_PAI = "last_pai";
		public static final String FANG_PAO_INDEX = "fang_pao_index";
		public static final String LEFT = "left";
		public static final String USER_PAI_INFOS = "user_pai_infos";

        public static final String UNIQUE_PRIMARY = "PRIMARY";

		private Table(){
		    uniqueMap = new HashMap<>();
			super.initProperty("id", "id", int.class, new TypeReference<Integer>() {});
			super.initProperty("room_id", "roomId", int.class, new TypeReference<Integer>() {});
			super.initProperty("room_check_id", "roomCheckId", String.class, new TypeReference<String>() {});
			super.initProperty("scene_id", "sceneId", int.class, new TypeReference<Integer>() {});
			super.initProperty("create_date", "createDate", java.util.Date.class, new TypeReference<java.util.Date>() {});
			super.initProperty("user0", "user0", int.class, new TypeReference<Integer>() {});
			super.initProperty("user1", "user1", int.class, new TypeReference<Integer>() {});
			super.initProperty("user2", "user2", int.class, new TypeReference<Integer>() {});
			super.initProperty("user3", "user3", int.class, new TypeReference<Integer>() {});
			super.initProperty("userName0", "userName0", String.class, new TypeReference<String>() {});
			super.initProperty("userName1", "userName1", String.class, new TypeReference<String>() {});
			super.initProperty("userName2", "userName2", String.class, new TypeReference<String>() {});
			super.initProperty("userName3", "userName3", String.class, new TypeReference<String>() {});
			super.initProperty("score0", "score0", int.class, new TypeReference<Integer>() {});
			super.initProperty("score1", "score1", int.class, new TypeReference<Integer>() {});
			super.initProperty("score2", "score2", int.class, new TypeReference<Integer>() {});
			super.initProperty("score3", "score3", int.class, new TypeReference<Integer>() {});
			super.initProperty("hu_pai", "huPai", boolean.class, new TypeReference<Boolean>() {});
			super.initProperty("hu_pai_index", "huPaiIndex", int.class, new TypeReference<Integer>() {});
			super.initProperty("zhuang_index", "zhuangIndex", int.class, new TypeReference<Integer>() {});
			super.initProperty("is_zi_mo", "isZiMo", boolean.class, new TypeReference<Boolean>() {});
			super.initProperty("hui_er", "huiEr", int[].class, new TypeReference<int[]>() {});
			super.initProperty("gang_shang_hua", "gangShangHua", boolean.class, new TypeReference<Boolean>() {});
			super.initProperty("last_pai", "lastPai", boolean.class, new TypeReference<Boolean>() {});
			super.initProperty("fang_pao_index", "fangPaoIndex", int.class, new TypeReference<Integer>() {});
			super.initProperty("left", "left", int[].class, new TypeReference<int[]>() {});
			super.initProperty("user_pai_infos", "userPaiInfos", mj.data.UserPaiInfo[].class, new TypeReference<mj.data.UserPaiInfo[]>() {});
		}

		@Override public String getKeyUpdatePartialPrefixSql(){
			return "UPDATE `room_result` SET ";
		}

		@Override public String getKeyWhereByKeySql(){
			return " WHERE `id`=?";
		}

		@Override public String getKeyDeleteSql(){
			return "DELETE FROM `room_result` WHERE `id`=?";
		}

		@Override public Map<String, UniqueInfo> getUniques(){
            return uniqueMap;
		}

		@Override
        public UniqueInfo getUniques(String uniqueName){
            return uniqueMap.get(uniqueName);
        }

		@Override
		public int setPreparedStatement(RoomResultDO t, PreparedStatement ps, int i, boolean isSetUnique) throws SQLException {
			Object idPtr;
			idPtr = t.getId();

			Object roomIdPtr;
			roomIdPtr = t.getRoomId();

			ps.setObject(i++, roomIdPtr);
			Object roomCheckIdPtr;
			roomCheckIdPtr = t.getRoomCheckId();

			ps.setObject(i++, roomCheckIdPtr);
			Object sceneIdPtr;
			sceneIdPtr = t.getSceneId();

			ps.setObject(i++, sceneIdPtr);
			Object createDatePtr;
			createDatePtr = t.getCreateDate();

			ps.setObject(i++, createDatePtr);
			Object user0Ptr;
			user0Ptr = t.getUser0();

			ps.setObject(i++, user0Ptr);
			Object user1Ptr;
			user1Ptr = t.getUser1();

			ps.setObject(i++, user1Ptr);
			Object user2Ptr;
			user2Ptr = t.getUser2();

			ps.setObject(i++, user2Ptr);
			Object user3Ptr;
			user3Ptr = t.getUser3();

			ps.setObject(i++, user3Ptr);
			Object userName0Ptr;
			userName0Ptr = t.getUserName0();

			ps.setObject(i++, userName0Ptr);
			Object userName1Ptr;
			userName1Ptr = t.getUserName1();

			ps.setObject(i++, userName1Ptr);
			Object userName2Ptr;
			userName2Ptr = t.getUserName2();

			ps.setObject(i++, userName2Ptr);
			Object userName3Ptr;
			userName3Ptr = t.getUserName3();

			ps.setObject(i++, userName3Ptr);
			Object score0Ptr;
			score0Ptr = t.getScore0();

			ps.setObject(i++, score0Ptr);
			Object score1Ptr;
			score1Ptr = t.getScore1();

			ps.setObject(i++, score1Ptr);
			Object score2Ptr;
			score2Ptr = t.getScore2();

			ps.setObject(i++, score2Ptr);
			Object score3Ptr;
			score3Ptr = t.getScore3();

			ps.setObject(i++, score3Ptr);
			Object huPaiPtr;
			huPaiPtr = t.getHuPai();

			ps.setObject(i++, huPaiPtr);
			Object huPaiIndexPtr;
			huPaiIndexPtr = t.getHuPaiIndex();

			ps.setObject(i++, huPaiIndexPtr);
			Object zhuangIndexPtr;
			zhuangIndexPtr = t.getZhuangIndex();

			ps.setObject(i++, zhuangIndexPtr);
			Object isZiMoPtr;
			isZiMoPtr = t.getIsZiMo();

			ps.setObject(i++, isZiMoPtr);
			Object huiErPtr;
			if(t.getHuiEr() != null){
				huiErPtr = com.isnowfox.util.JsonUtils.serialize(t.getHuiEr());
			}else{
				huiErPtr = null;
			}

			ps.setObject(i++, huiErPtr);
			Object gangShangHuaPtr;
			gangShangHuaPtr = t.getGangShangHua();

			ps.setObject(i++, gangShangHuaPtr);
			Object lastPaiPtr;
			lastPaiPtr = t.getLastPai();

			ps.setObject(i++, lastPaiPtr);
			Object fangPaoIndexPtr;
			fangPaoIndexPtr = t.getFangPaoIndex();

			ps.setObject(i++, fangPaoIndexPtr);
			Object leftPtr;
			if(t.getLeft() != null){
				leftPtr = com.isnowfox.util.JsonUtils.serialize(t.getLeft());
			}else{
				leftPtr = null;
			}

			ps.setObject(i++, leftPtr);
			Object userPaiInfosPtr;
			if(t.getUserPaiInfos() != null){
				userPaiInfosPtr = com.isnowfox.util.JsonUtils.serialize(t.getUserPaiInfos());
			}else{
				userPaiInfosPtr = null;
			}

			ps.setObject(i++, userPaiInfosPtr);
			return i;
		}

		@Override
        public int setAllPreparedStatement(RoomResultDO t, PreparedStatement ps, int i) throws SQLException {
			Object idPtr;
				idPtr = t.getId();

			ps.setObject(i++,  idPtr);
			Object roomIdPtr;
				roomIdPtr = t.getRoomId();

			ps.setObject(i++,  roomIdPtr);
			Object roomCheckIdPtr;
				roomCheckIdPtr = t.getRoomCheckId();

			ps.setObject(i++,  roomCheckIdPtr);
			Object sceneIdPtr;
				sceneIdPtr = t.getSceneId();

			ps.setObject(i++,  sceneIdPtr);
			Object createDatePtr;
				createDatePtr = t.getCreateDate();

			ps.setObject(i++,  createDatePtr);
			Object user0Ptr;
				user0Ptr = t.getUser0();

			ps.setObject(i++,  user0Ptr);
			Object user1Ptr;
				user1Ptr = t.getUser1();

			ps.setObject(i++,  user1Ptr);
			Object user2Ptr;
				user2Ptr = t.getUser2();

			ps.setObject(i++,  user2Ptr);
			Object user3Ptr;
				user3Ptr = t.getUser3();

			ps.setObject(i++,  user3Ptr);
			Object userName0Ptr;
				userName0Ptr = t.getUserName0();

			ps.setObject(i++,  userName0Ptr);
			Object userName1Ptr;
				userName1Ptr = t.getUserName1();

			ps.setObject(i++,  userName1Ptr);
			Object userName2Ptr;
				userName2Ptr = t.getUserName2();

			ps.setObject(i++,  userName2Ptr);
			Object userName3Ptr;
				userName3Ptr = t.getUserName3();

			ps.setObject(i++,  userName3Ptr);
			Object score0Ptr;
				score0Ptr = t.getScore0();

			ps.setObject(i++,  score0Ptr);
			Object score1Ptr;
				score1Ptr = t.getScore1();

			ps.setObject(i++,  score1Ptr);
			Object score2Ptr;
				score2Ptr = t.getScore2();

			ps.setObject(i++,  score2Ptr);
			Object score3Ptr;
				score3Ptr = t.getScore3();

			ps.setObject(i++,  score3Ptr);
			Object huPaiPtr;
				huPaiPtr = t.getHuPai();

			ps.setObject(i++,  huPaiPtr);
			Object huPaiIndexPtr;
				huPaiIndexPtr = t.getHuPaiIndex();

			ps.setObject(i++,  huPaiIndexPtr);
			Object zhuangIndexPtr;
				zhuangIndexPtr = t.getZhuangIndex();

			ps.setObject(i++,  zhuangIndexPtr);
			Object isZiMoPtr;
				isZiMoPtr = t.getIsZiMo();

			ps.setObject(i++,  isZiMoPtr);
			Object huiErPtr;
			if(t.getHuiEr() != null){
				huiErPtr = com.isnowfox.util.JsonUtils.serialize(t.getHuiEr());
			}else{
				huiErPtr = null;
			}

			ps.setObject(i++,  huiErPtr);
			Object gangShangHuaPtr;
				gangShangHuaPtr = t.getGangShangHua();

			ps.setObject(i++,  gangShangHuaPtr);
			Object lastPaiPtr;
				lastPaiPtr = t.getLastPai();

			ps.setObject(i++,  lastPaiPtr);
			Object fangPaoIndexPtr;
				fangPaoIndexPtr = t.getFangPaoIndex();

			ps.setObject(i++,  fangPaoIndexPtr);
			Object leftPtr;
			if(t.getLeft() != null){
				leftPtr = com.isnowfox.util.JsonUtils.serialize(t.getLeft());
			}else{
				leftPtr = null;
			}

			ps.setObject(i++,  leftPtr);
			Object userPaiInfosPtr;
			if(t.getUserPaiInfos() != null){
				userPaiInfosPtr = com.isnowfox.util.JsonUtils.serialize(t.getUserPaiInfos());
			}else{
				userPaiInfosPtr = null;
			}

			ps.setObject(i++,  userPaiInfosPtr);
        	return i;
        }

		@Override
		public int setPreparedStatementKeys(RoomResultDO t, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, t.getId());
			return i;
		}

		@Override
		public int setKeyPreparedStatement(Key k, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, k.getId());
			return i;
		}

		@Override public String getInsertSql(){
			return "INSERT INTO `room_result` (`room_id`,`room_check_id`,`scene_id`,`create_date`,`user0`,`user1`,`user2`,`user3`,`userName0`,`userName1`,`userName2`,`userName3`,`score0`,`score1`,`score2`,`score3`,`hu_pai`,`hu_pai_index`,`zhuang_index`,`is_zi_mo`,`hui_er`,`gang_shang_hua`,`last_pai`,`fang_pao_index`,`left`,`user_pai_infos`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		}

		@Override public String getReplaceSql(){
        	return "REPLACE INTO `room_result` (`id`,`room_id`,`room_check_id`,`scene_id`,`create_date`,`user0`,`user1`,`user2`,`user3`,`userName0`,`userName1`,`userName2`,`userName3`,`score0`,`score1`,`score2`,`score3`,`hu_pai`,`hu_pai_index`,`zhuang_index`,`is_zi_mo`,`hui_er`,`gang_shang_hua`,`last_pai`,`fang_pao_index`,`left`,`user_pai_infos`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

		@Override public String getFastInsertPrefixSql(){
			return "INSERT INTO `room_result` (`room_id`,`room_check_id`,`scene_id`,`create_date`,`user0`,`user1`,`user2`,`user3`,`userName0`,`userName1`,`userName2`,`userName3`,`score0`,`score1`,`score2`,`score3`,`hu_pai`,`hu_pai_index`,`zhuang_index`,`is_zi_mo`,`hui_er`,`gang_shang_hua`,`last_pai`,`fang_pao_index`,`left`,`user_pai_infos`) VALUES ";
		}
		@Override public String getFastInsertValueItemsSql(){
			return " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		}
		@Override public String getUpdateSql(){
			return "UPDATE `room_result` SET `room_id`=?,`room_check_id`=?,`scene_id`=?,`create_date`=?,`user0`=?,`user1`=?,`user2`=?,`user3`=?,`userName0`=?,`userName1`=?,`userName2`=?,`userName3`=?,`score0`=?,`score1`=?,`score2`=?,`score3`=?,`hu_pai`=?,`hu_pai_index`=?,`zhuang_index`=?,`is_zi_mo`=?,`hui_er`=?,`gang_shang_hua`=?,`last_pai`=?,`fang_pao_index`=?,`left`=?,`user_pai_infos`=? WHERE `id`=?";
		}

		@Override public String getSelectByKeySql(){
			return "SELECT * FROM `room_result` WHERE `id`=? ORDER BY `id` DESC";
		}
		@Override public String getSelectCountSql(){
			return "SELECT count(*) FROM `room_result`";
		}
		@Override public String getFormatSelectSql(){
			return "SELECT %s FROM `room_result` ORDER BY `id` DESC";
		}
		@Override public String getFormatSelectPrefixSql(){
			return "SELECT %s FROM `room_result` ";
		}
		@Override public String getSelectPrefixSql(){
			return "SELECT * FROM `room_result` ";
		}
		@Override public String getOrderByIdDescSql(){
			return " ORDER BY `id` DESC";
		}
		@Override public String getDbTableName(){
			return DB_TABLE_NAME;
		}

		@Override public RowMapper<RoomResultDO> getRowMapper(){
			return new RowMapper<RoomResultDO>() {
				@Override
				public RoomResultDO mapRow(ResultSet rs, int rowNum) throws SQLException {
					RoomResultDO o=new RoomResultDO();
					o.id = rs.getInt("id");
					o.roomId = rs.getInt("room_id");
					o.roomCheckId = rs.getString("room_check_id");
					o.sceneId = rs.getInt("scene_id");
					o.createDate = rs.getTimestamp("create_date");
					o.user0 = rs.getInt("user0");
					o.user1 = rs.getInt("user1");
					o.user2 = rs.getInt("user2");
					o.user3 = rs.getInt("user3");
					o.userName0 = rs.getString("userName0");
					o.userName1 = rs.getString("userName1");
					o.userName2 = rs.getString("userName2");
					o.userName3 = rs.getString("userName3");
					o.score0 = rs.getInt("score0");
					o.score1 = rs.getInt("score1");
					o.score2 = rs.getInt("score2");
					o.score3 = rs.getInt("score3");
					o.huPai = rs.getBoolean("hu_pai");
					o.huPaiIndex = rs.getInt("hu_pai_index");
					o.zhuangIndex = rs.getInt("zhuang_index");
					o.isZiMo = rs.getBoolean("is_zi_mo");
					String huiErStr = rs.getString("hui_er");
					if (com.isnowfox.util.StringExpandUtils.isNotEmpty(huiErStr)) {
						o.huiEr =  com.isnowfox.util.JsonUtils.deserialize(huiErStr,new com.fasterxml.jackson.core.type.TypeReference<int[]>(){});
					}else{
						o.huiEr = null;
					}
					o.gangShangHua = rs.getBoolean("gang_shang_hua");
					o.lastPai = rs.getBoolean("last_pai");
					o.fangPaoIndex = rs.getInt("fang_pao_index");
					String leftStr = rs.getString("left");
					if (com.isnowfox.util.StringExpandUtils.isNotEmpty(leftStr)) {
						o.left =  com.isnowfox.util.JsonUtils.deserialize(leftStr,new com.fasterxml.jackson.core.type.TypeReference<int[]>(){});
					}else{
						o.left = null;
					}
					String userPaiInfosStr = rs.getString("user_pai_infos");
					if (com.isnowfox.util.StringExpandUtils.isNotEmpty(userPaiInfosStr)) {
						o.userPaiInfos =  com.isnowfox.util.JsonUtils.deserialize(userPaiInfosStr,new com.fasterxml.jackson.core.type.TypeReference<mj.data.UserPaiInfo[]>(){});
					}else{
						o.userPaiInfos = null;
					}
					return o;
				}
			};
		}

		@Override public <C extends RoomResultDO> RowMapper<C>  getRowMapper(final Class<C> cls){
			return new RowMapper<C>() {
				@Override
				public C mapRow(ResultSet rs, int rowNum) throws SQLException {
					C o;
					try{
						o = cls.newInstance();
						o.setId(rs.getInt("id"));
						o.setRoomId(rs.getInt("room_id"));
						o.setRoomCheckId(rs.getString("room_check_id"));
						o.setSceneId(rs.getInt("scene_id"));
						o.setCreateDate(rs.getTimestamp("create_date"));
						o.setUser0(rs.getInt("user0"));
						o.setUser1(rs.getInt("user1"));
						o.setUser2(rs.getInt("user2"));
						o.setUser3(rs.getInt("user3"));
						o.setUserName0(rs.getString("userName0"));
						o.setUserName1(rs.getString("userName1"));
						o.setUserName2(rs.getString("userName2"));
						o.setUserName3(rs.getString("userName3"));
						o.setScore0(rs.getInt("score0"));
						o.setScore1(rs.getInt("score1"));
						o.setScore2(rs.getInt("score2"));
						o.setScore3(rs.getInt("score3"));
						o.setHuPai(rs.getBoolean("hu_pai"));
						o.setHuPaiIndex(rs.getInt("hu_pai_index"));
						o.setZhuangIndex(rs.getInt("zhuang_index"));
						o.setIsZiMo(rs.getBoolean("is_zi_mo"));
						String huiErStr = rs.getString("hui_er");
						if (com.isnowfox.util.StringExpandUtils.isNotEmpty(huiErStr)) {
							o.setHuiEr(com.isnowfox.util.JsonUtils.deserialize(huiErStr,new com.fasterxml.jackson.core.type.TypeReference<int[]>(){}));
						}else{
							o.setHuiEr(null);
					}
						o.setGangShangHua(rs.getBoolean("gang_shang_hua"));
						o.setLastPai(rs.getBoolean("last_pai"));
						o.setFangPaoIndex(rs.getInt("fang_pao_index"));
						String leftStr = rs.getString("left");
						if (com.isnowfox.util.StringExpandUtils.isNotEmpty(leftStr)) {
							o.setLeft(com.isnowfox.util.JsonUtils.deserialize(leftStr,new com.fasterxml.jackson.core.type.TypeReference<int[]>(){}));
						}else{
							o.setLeft(null);
					}
						String userPaiInfosStr = rs.getString("user_pai_infos");
						if (com.isnowfox.util.StringExpandUtils.isNotEmpty(userPaiInfosStr)) {
							o.setUserPaiInfos(com.isnowfox.util.JsonUtils.deserialize(userPaiInfosStr,new com.fasterxml.jackson.core.type.TypeReference<mj.data.UserPaiInfo[]>(){}));
						}else{
							o.setUserPaiInfos(null);
					}
                        return o;
					} catch (InstantiationException | IllegalAccessException e) {
						throw new SQLException("必须实现默认构造函数",e);
					}
				}
			};
		}
	}
}
