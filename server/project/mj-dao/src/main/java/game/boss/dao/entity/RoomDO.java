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

public class RoomDO extends EntityObject<RoomDO, RoomDO.Key>{

	/**牌局id*/
	private int id;
	/**创建用户id*/
	private int createUserId;
	/**用户数*/
	private int userMax;
	/**牌局uuid*/
	private String uuid;
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
	/**房间的check-id,进入id,可以重复,但是不允许同时活跃状态的id 相同*/
	private String roomCheckId;
	private java.util.Date startDate;
	private java.util.Date endDate;
	private boolean start;
	/**正常结束*/
	private boolean end;
	private int version;
	private int sceneId;
	private int chapterNums;
	/**配置*/
	private mj.data.Config config;

	public static class Key implements KeyObject<RoomDO, RoomDO.Key>{
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
			return "Room[id:"+ id+ "]";
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
				return "Room[id:"+ id+ "]";
			}
		};
	}




	public RoomDO() {
    }

	public RoomDO(int createUserId,int userMax,String uuid,int user0,int user1,int user2,int user3,String userName0,String userName1,String userName2,String userName3,int score0,int score1,int score2,int score3,String roomCheckId,java.util.Date startDate,java.util.Date endDate,boolean start,boolean end,int version,int sceneId,int chapterNums,mj.data.Config config) {
		this.createUserId = createUserId;
		this.userMax = userMax;
		this.uuid = uuid;
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
		this.roomCheckId = roomCheckId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.start = start;
		this.end = end;
		this.version = version;
		this.sceneId = sceneId;
		this.chapterNums = chapterNums;
		this.config = config;
	}


	public RoomDO newInstance(){
		return new RoomDO();
	}

    public void setKey(Object key){
        this.id = ((Number)key).intValue();
    }

	/**
	 * 牌局id
	 **/
	public int getId() {
		return id;
	}

	/**
	 * 牌局id
	 **/
	public void setId(int id) {
		this.id = id;
		changeProperty("id",id);
	}

	/**
	 * 创建用户id
	 **/
	public int getCreateUserId() {
		return createUserId;
	}

	/**
	 * 创建用户id
	 **/
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
		changeProperty("createUserId",createUserId);
	}

	/**
	 * 用户数
	 **/
	public int getUserMax() {
		return userMax;
	}

	/**
	 * 用户数
	 **/
	public void setUserMax(int userMax) {
		this.userMax = userMax;
		changeProperty("userMax",userMax);
	}

	/**
	 * 牌局uuid
	 **/
	public String getUuid() {
		return uuid;
	}

	/**
	 * 牌局uuid
	 **/
	public void setUuid(String uuid) {
		this.uuid = uuid;
		changeProperty("uuid",uuid);
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

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
		changeProperty("startDate",startDate);
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
		changeProperty("endDate",endDate);
	}

	public boolean getStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
		changeProperty("start",start);
	}

	/**
	 * 正常结束
	 **/
	public boolean getEnd() {
		return end;
	}

	/**
	 * 正常结束
	 **/
	public void setEnd(boolean end) {
		this.end = end;
		changeProperty("end",end);
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
		changeProperty("version",version);
	}

	public int getSceneId() {
		return sceneId;
	}

	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
		changeProperty("sceneId",sceneId);
	}

	public int getChapterNums() {
		return chapterNums;
	}

	public void setChapterNums(int chapterNums) {
		this.chapterNums = chapterNums;
		changeProperty("chapterNums",chapterNums);
	}

	/**
	 * 配置
	 **/
	public mj.data.Config getConfig() {
		return config;
	}

	/**
	 * 配置
	 **/
	public void setConfig(mj.data.Config config) {
		this.config = config;
		changeProperty("config",config);
	}

    @Override
    public Object get(String dbName){
        switch(dbName){
        case "id":
            return id;
        case "create_user_id":
            return createUserId;
        case "user_max":
            return userMax;
        case "uuid":
            return uuid;
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
        case "room_check_id":
            return roomCheckId;
        case "start_date":
            return startDate;
        case "end_date":
            return endDate;
        case "start":
            return start;
        case "end":
            return end;
        case "version":
            return version;
        case "scene_id":
            return sceneId;
        case "chapter_nums":
            return chapterNums;
        case "config":
            return config;
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
		case "create_user_id":
			createUserId = (int)obj;
			return true;
		case "user_max":
			userMax = (int)obj;
			return true;
		case "uuid":
			uuid = (String)obj;
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
		case "room_check_id":
			roomCheckId = (String)obj;
			return true;
		case "start_date":
			startDate = (java.util.Date)obj;
			return true;
		case "end_date":
			endDate = (java.util.Date)obj;
			return true;
		case "start":
			start = (boolean)obj;
			return true;
		case "end":
			end = (boolean)obj;
			return true;
		case "version":
			version = (int)obj;
			return true;
		case "scene_id":
			sceneId = (int)obj;
			return true;
		case "chapter_nums":
			chapterNums = (int)obj;
			return true;
		case "config":
			config = (mj.data.Config)obj;
			return true;
		default :
			return false;
		}
	}

	@Override
	public String toString() {
		return "Room[id:"+ id+",createUserId:"+ createUserId+",userMax:"+ userMax+",uuid:"+ (uuid == null ?"null":uuid.substring(0, Math.min(uuid.length(), 64)))+",user0:"+ user0+",user1:"+ user1+",user2:"+ user2+",user3:"+ user3+",userName0:"+ (userName0 == null ?"null":userName0.substring(0, Math.min(userName0.length(), 64)))+",userName1:"+ (userName1 == null ?"null":userName1.substring(0, Math.min(userName1.length(), 64)))+",userName2:"+ (userName2 == null ?"null":userName2.substring(0, Math.min(userName2.length(), 64)))+",userName3:"+ (userName3 == null ?"null":userName3.substring(0, Math.min(userName3.length(), 64)))+",score0:"+ score0+",score1:"+ score1+",score2:"+ score2+",score3:"+ score3+",roomCheckId:"+ (roomCheckId == null ?"null":roomCheckId.substring(0, Math.min(roomCheckId.length(), 64)))+",startDate:"+ startDate+",endDate:"+ endDate+",start:"+ start+",end:"+ end+",version:"+ version+",sceneId:"+ sceneId+",chapterNums:"+ chapterNums+",config:"+ config+ "]";
	}

	@Override
	@JsonIgnore
    @Transient
	public Table getTableInfo() {
		return TABLE_INFO;
	}


	public static final Table TABLE_INFO= new Table();

	public static final class Table extends TableInfo<RoomDO ,Key>{
		public static final String DB_TABLE_NAME = "room";
		private Map<String, UniqueInfo> uniqueMap;

		public static final String ID = "id";
		public static final String CREATE_USER_ID = "create_user_id";
		public static final String USER_MAX = "user_max";
		public static final String UUID = "uuid";
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
		public static final String ROOM_CHECK_ID = "room_check_id";
		public static final String START_DATE = "start_date";
		public static final String END_DATE = "end_date";
		public static final String START = "start";
		public static final String END = "end";
		public static final String VERSION = "version";
		public static final String SCENE_ID = "scene_id";
		public static final String CHAPTER_NUMS = "chapter_nums";
		public static final String CONFIG = "config";

        public static final String UNIQUE_PRIMARY = "PRIMARY";

		private Table(){
		    uniqueMap = new HashMap<>();
			super.initProperty("id", "id", int.class, new TypeReference<Integer>() {});
			super.initProperty("create_user_id", "createUserId", int.class, new TypeReference<Integer>() {});
			super.initProperty("user_max", "userMax", int.class, new TypeReference<Integer>() {});
			super.initProperty("uuid", "uuid", String.class, new TypeReference<String>() {});
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
			super.initProperty("room_check_id", "roomCheckId", String.class, new TypeReference<String>() {});
			super.initProperty("start_date", "startDate", java.util.Date.class, new TypeReference<java.util.Date>() {});
			super.initProperty("end_date", "endDate", java.util.Date.class, new TypeReference<java.util.Date>() {});
			super.initProperty("start", "start", boolean.class, new TypeReference<Boolean>() {});
			super.initProperty("end", "end", boolean.class, new TypeReference<Boolean>() {});
			super.initProperty("version", "version", int.class, new TypeReference<Integer>() {});
			super.initProperty("scene_id", "sceneId", int.class, new TypeReference<Integer>() {});
			super.initProperty("chapter_nums", "chapterNums", int.class, new TypeReference<Integer>() {});
			super.initProperty("config", "config", mj.data.Config.class, new TypeReference<mj.data.Config>() {});
		}

		@Override public String getKeyUpdatePartialPrefixSql(){
			return "UPDATE `room` SET ";
		}

		@Override public String getKeyWhereByKeySql(){
			return " WHERE `id`=?";
		}

		@Override public String getKeyDeleteSql(){
			return "DELETE FROM `room` WHERE `id`=?";
		}

		@Override public Map<String, UniqueInfo> getUniques(){
            return uniqueMap;
		}

		@Override
        public UniqueInfo getUniques(String uniqueName){
            return uniqueMap.get(uniqueName);
        }

		@Override
		public int setPreparedStatement(RoomDO t, PreparedStatement ps, int i, boolean isSetUnique) throws SQLException {
			Object idPtr;
			idPtr = t.getId();

			Object createUserIdPtr;
			createUserIdPtr = t.getCreateUserId();

			ps.setObject(i++, createUserIdPtr);
			Object userMaxPtr;
			userMaxPtr = t.getUserMax();

			ps.setObject(i++, userMaxPtr);
			Object uuidPtr;
			uuidPtr = t.getUuid();

			ps.setObject(i++, uuidPtr);
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
			Object roomCheckIdPtr;
			roomCheckIdPtr = t.getRoomCheckId();

			ps.setObject(i++, roomCheckIdPtr);
			Object startDatePtr;
			startDatePtr = t.getStartDate();

			ps.setObject(i++, startDatePtr);
			Object endDatePtr;
			endDatePtr = t.getEndDate();

			ps.setObject(i++, endDatePtr);
			Object startPtr;
			startPtr = t.getStart();

			ps.setObject(i++, startPtr);
			Object endPtr;
			endPtr = t.getEnd();

			ps.setObject(i++, endPtr);
			Object versionPtr;
			versionPtr = t.getVersion();

			ps.setObject(i++, versionPtr);
			Object sceneIdPtr;
			sceneIdPtr = t.getSceneId();

			ps.setObject(i++, sceneIdPtr);
			Object chapterNumsPtr;
			chapterNumsPtr = t.getChapterNums();

			ps.setObject(i++, chapterNumsPtr);
			Object configPtr;
			if(t.getConfig() != null){
				configPtr = com.isnowfox.util.JsonUtils.serialize(t.getConfig());
			}else{
				configPtr = null;
			}

			ps.setObject(i++, configPtr);
			return i;
		}

		@Override
        public int setAllPreparedStatement(RoomDO t, PreparedStatement ps, int i) throws SQLException {
			Object idPtr;
				idPtr = t.getId();

			ps.setObject(i++,  idPtr);
			Object createUserIdPtr;
				createUserIdPtr = t.getCreateUserId();

			ps.setObject(i++,  createUserIdPtr);
			Object userMaxPtr;
				userMaxPtr = t.getUserMax();

			ps.setObject(i++,  userMaxPtr);
			Object uuidPtr;
				uuidPtr = t.getUuid();

			ps.setObject(i++,  uuidPtr);
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
			Object roomCheckIdPtr;
				roomCheckIdPtr = t.getRoomCheckId();

			ps.setObject(i++,  roomCheckIdPtr);
			Object startDatePtr;
				startDatePtr = t.getStartDate();

			ps.setObject(i++,  startDatePtr);
			Object endDatePtr;
				endDatePtr = t.getEndDate();

			ps.setObject(i++,  endDatePtr);
			Object startPtr;
				startPtr = t.getStart();

			ps.setObject(i++,  startPtr);
			Object endPtr;
				endPtr = t.getEnd();

			ps.setObject(i++,  endPtr);
			Object versionPtr;
				versionPtr = t.getVersion();

			ps.setObject(i++,  versionPtr);
			Object sceneIdPtr;
				sceneIdPtr = t.getSceneId();

			ps.setObject(i++,  sceneIdPtr);
			Object chapterNumsPtr;
				chapterNumsPtr = t.getChapterNums();

			ps.setObject(i++,  chapterNumsPtr);
			Object configPtr;
			if(t.getConfig() != null){
				configPtr = com.isnowfox.util.JsonUtils.serialize(t.getConfig());
			}else{
				configPtr = null;
			}

			ps.setObject(i++,  configPtr);
        	return i;
        }

		@Override
		public int setPreparedStatementKeys(RoomDO t, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, t.getId());
			return i;
		}

		@Override
		public int setKeyPreparedStatement(Key k, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, k.getId());
			return i;
		}

		@Override public String getInsertSql(){
			return "INSERT INTO `room` (`create_user_id`,`user_max`,`uuid`,`user0`,`user1`,`user2`,`user3`,`userName0`,`userName1`,`userName2`,`userName3`,`score0`,`score1`,`score2`,`score3`,`room_check_id`,`start_date`,`end_date`,`start`,`end`,`version`,`scene_id`,`chapter_nums`,`config`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		}

		@Override public String getReplaceSql(){
        	return "REPLACE INTO `room` (`id`,`create_user_id`,`user_max`,`uuid`,`user0`,`user1`,`user2`,`user3`,`userName0`,`userName1`,`userName2`,`userName3`,`score0`,`score1`,`score2`,`score3`,`room_check_id`,`start_date`,`end_date`,`start`,`end`,`version`,`scene_id`,`chapter_nums`,`config`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

		@Override public String getFastInsertPrefixSql(){
			return "INSERT INTO `room` (`create_user_id`,`user_max`,`uuid`,`user0`,`user1`,`user2`,`user3`,`userName0`,`userName1`,`userName2`,`userName3`,`score0`,`score1`,`score2`,`score3`,`room_check_id`,`start_date`,`end_date`,`start`,`end`,`version`,`scene_id`,`chapter_nums`,`config`) VALUES ";
		}
		@Override public String getFastInsertValueItemsSql(){
			return " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		}
		@Override public String getUpdateSql(){
			return "UPDATE `room` SET `create_user_id`=?,`user_max`=?,`uuid`=?,`user0`=?,`user1`=?,`user2`=?,`user3`=?,`userName0`=?,`userName1`=?,`userName2`=?,`userName3`=?,`score0`=?,`score1`=?,`score2`=?,`score3`=?,`room_check_id`=?,`start_date`=?,`end_date`=?,`start`=?,`end`=?,`version`=?,`scene_id`=?,`chapter_nums`=?,`config`=? WHERE `id`=?";
		}

		@Override public String getSelectByKeySql(){
			return "SELECT * FROM `room` WHERE `id`=? ORDER BY `id` DESC";
		}
		@Override public String getSelectCountSql(){
			return "SELECT count(*) FROM `room`";
		}
		@Override public String getFormatSelectSql(){
			return "SELECT %s FROM `room` ORDER BY `id` DESC";
		}
		@Override public String getFormatSelectPrefixSql(){
			return "SELECT %s FROM `room` ";
		}
		@Override public String getSelectPrefixSql(){
			return "SELECT * FROM `room` ";
		}
		@Override public String getOrderByIdDescSql(){
			return " ORDER BY `id` DESC";
		}
		@Override public String getDbTableName(){
			return DB_TABLE_NAME;
		}

		@Override public RowMapper<RoomDO> getRowMapper(){
			return new RowMapper<RoomDO>() {
				@Override
				public RoomDO mapRow(ResultSet rs, int rowNum) throws SQLException {
					RoomDO o=new RoomDO();
					o.id = rs.getInt("id");
					o.createUserId = rs.getInt("create_user_id");
					o.userMax = rs.getInt("user_max");
					o.uuid = rs.getString("uuid");
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
					o.roomCheckId = rs.getString("room_check_id");
					o.startDate = rs.getTimestamp("start_date");
					o.endDate = rs.getTimestamp("end_date");
					o.start = rs.getBoolean("start");
					o.end = rs.getBoolean("end");
					o.version = rs.getInt("version");
					o.sceneId = rs.getInt("scene_id");
					o.chapterNums = rs.getInt("chapter_nums");
					String configStr = rs.getString("config");
					if (com.isnowfox.util.StringExpandUtils.isNotEmpty(configStr)) {
						o.config =  com.isnowfox.util.JsonUtils.deserialize(configStr,new com.fasterxml.jackson.core.type.TypeReference<mj.data.Config>(){});
					}else{
						o.config = null;
					}
					return o;
				}
			};
		}

		@Override public <C extends RoomDO> RowMapper<C>  getRowMapper(final Class<C> cls){
			return new RowMapper<C>() {
				@Override
				public C mapRow(ResultSet rs, int rowNum) throws SQLException {
					C o;
					try{
						o = cls.newInstance();
						o.setId(rs.getInt("id"));
						o.setCreateUserId(rs.getInt("create_user_id"));
						o.setUserMax(rs.getInt("user_max"));
						o.setUuid(rs.getString("uuid"));
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
						o.setRoomCheckId(rs.getString("room_check_id"));
						o.setStartDate(rs.getTimestamp("start_date"));
						o.setEndDate(rs.getTimestamp("end_date"));
						o.setStart(rs.getBoolean("start"));
						o.setEnd(rs.getBoolean("end"));
						o.setVersion(rs.getInt("version"));
						o.setSceneId(rs.getInt("scene_id"));
						o.setChapterNums(rs.getInt("chapter_nums"));
						String configStr = rs.getString("config");
						if (com.isnowfox.util.StringExpandUtils.isNotEmpty(configStr)) {
							o.setConfig(com.isnowfox.util.JsonUtils.deserialize(configStr,new com.fasterxml.jackson.core.type.TypeReference<mj.data.Config>(){}));
						}else{
							o.setConfig(null);
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
