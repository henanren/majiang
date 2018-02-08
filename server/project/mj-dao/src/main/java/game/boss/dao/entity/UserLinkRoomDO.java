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

public class UserLinkRoomDO extends EntityObject<UserLinkRoomDO, UserLinkRoomDO.Key>{

	/**牌局id*/
	private int id;
	/**用户id*/
	private int userId;
	/**房间id*/
	private int roomId;
	/**房间的check-id,进入id,可以重复,但是不允许同时活跃状态的id 相同*/
	private String roomCheckId;
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
	private java.util.Date startDate;
	private java.util.Date endDate;
	private int chapterNums;

	public static class Key implements KeyObject<UserLinkRoomDO, UserLinkRoomDO.Key>{
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
			return "UserLinkRoom[id:"+ id+ "]";
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
				return "UserLinkRoom[id:"+ id+ "]";
			}
		};
	}




	public UserLinkRoomDO() {
    }

	public UserLinkRoomDO(int userId,int roomId,String roomCheckId,int user0,int user1,int user2,int user3,String userName0,String userName1,String userName2,String userName3,int score0,int score1,int score2,int score3,java.util.Date startDate,java.util.Date endDate,int chapterNums) {
		this.userId = userId;
		this.roomId = roomId;
		this.roomCheckId = roomCheckId;
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
		this.startDate = startDate;
		this.endDate = endDate;
		this.chapterNums = chapterNums;
	}


	public UserLinkRoomDO newInstance(){
		return new UserLinkRoomDO();
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
	 * 用户id
	 **/
	public int getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 **/
	public void setUserId(int userId) {
		this.userId = userId;
		changeProperty("userId",userId);
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

	public int getChapterNums() {
		return chapterNums;
	}

	public void setChapterNums(int chapterNums) {
		this.chapterNums = chapterNums;
		changeProperty("chapterNums",chapterNums);
	}

    @Override
    public Object get(String dbName){
        switch(dbName){
        case "id":
            return id;
        case "user_id":
            return userId;
        case "room_id":
            return roomId;
        case "room_check_id":
            return roomCheckId;
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
        case "start_date":
            return startDate;
        case "end_date":
            return endDate;
        case "chapter_nums":
            return chapterNums;
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
		case "user_id":
			userId = (int)obj;
			return true;
		case "room_id":
			roomId = (int)obj;
			return true;
		case "room_check_id":
			roomCheckId = (String)obj;
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
		case "start_date":
			startDate = (java.util.Date)obj;
			return true;
		case "end_date":
			endDate = (java.util.Date)obj;
			return true;
		case "chapter_nums":
			chapterNums = (int)obj;
			return true;
		default :
			return false;
		}
	}

	@Override
	public String toString() {
		return "UserLinkRoom[id:"+ id+",userId:"+ userId+",roomId:"+ roomId+",roomCheckId:"+ (roomCheckId == null ?"null":roomCheckId.substring(0, Math.min(roomCheckId.length(), 64)))+",user0:"+ user0+",user1:"+ user1+",user2:"+ user2+",user3:"+ user3+",userName0:"+ (userName0 == null ?"null":userName0.substring(0, Math.min(userName0.length(), 64)))+",userName1:"+ (userName1 == null ?"null":userName1.substring(0, Math.min(userName1.length(), 64)))+",userName2:"+ (userName2 == null ?"null":userName2.substring(0, Math.min(userName2.length(), 64)))+",userName3:"+ (userName3 == null ?"null":userName3.substring(0, Math.min(userName3.length(), 64)))+",score0:"+ score0+",score1:"+ score1+",score2:"+ score2+",score3:"+ score3+",startDate:"+ startDate+",endDate:"+ endDate+",chapterNums:"+ chapterNums+ "]";
	}

	@Override
	@JsonIgnore
    @Transient
	public Table getTableInfo() {
		return TABLE_INFO;
	}


	public static final Table TABLE_INFO= new Table();

	public static final class Table extends TableInfo<UserLinkRoomDO ,Key>{
		public static final String DB_TABLE_NAME = "user_link_room";
		private Map<String, UniqueInfo> uniqueMap;

		public static final String ID = "id";
		public static final String USER_ID = "user_id";
		public static final String ROOM_ID = "room_id";
		public static final String ROOM_CHECK_ID = "room_check_id";
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
		public static final String START_DATE = "start_date";
		public static final String END_DATE = "end_date";
		public static final String CHAPTER_NUMS = "chapter_nums";

        public static final String UNIQUE_PRIMARY = "PRIMARY";

		private Table(){
		    uniqueMap = new HashMap<>();
			super.initProperty("id", "id", int.class, new TypeReference<Integer>() {});
			super.initProperty("user_id", "userId", int.class, new TypeReference<Integer>() {});
			super.initProperty("room_id", "roomId", int.class, new TypeReference<Integer>() {});
			super.initProperty("room_check_id", "roomCheckId", String.class, new TypeReference<String>() {});
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
			super.initProperty("start_date", "startDate", java.util.Date.class, new TypeReference<java.util.Date>() {});
			super.initProperty("end_date", "endDate", java.util.Date.class, new TypeReference<java.util.Date>() {});
			super.initProperty("chapter_nums", "chapterNums", int.class, new TypeReference<Integer>() {});
		}

		@Override public String getKeyUpdatePartialPrefixSql(){
			return "UPDATE `user_link_room` SET ";
		}

		@Override public String getKeyWhereByKeySql(){
			return " WHERE `id`=?";
		}

		@Override public String getKeyDeleteSql(){
			return "DELETE FROM `user_link_room` WHERE `id`=?";
		}

		@Override public Map<String, UniqueInfo> getUniques(){
            return uniqueMap;
		}

		@Override
        public UniqueInfo getUniques(String uniqueName){
            return uniqueMap.get(uniqueName);
        }

		@Override
		public int setPreparedStatement(UserLinkRoomDO t, PreparedStatement ps, int i, boolean isSetUnique) throws SQLException {
			Object idPtr;
			idPtr = t.getId();

			Object userIdPtr;
			userIdPtr = t.getUserId();

			ps.setObject(i++, userIdPtr);
			Object roomIdPtr;
			roomIdPtr = t.getRoomId();

			ps.setObject(i++, roomIdPtr);
			Object roomCheckIdPtr;
			roomCheckIdPtr = t.getRoomCheckId();

			ps.setObject(i++, roomCheckIdPtr);
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
			Object startDatePtr;
			startDatePtr = t.getStartDate();

			ps.setObject(i++, startDatePtr);
			Object endDatePtr;
			endDatePtr = t.getEndDate();

			ps.setObject(i++, endDatePtr);
			Object chapterNumsPtr;
			chapterNumsPtr = t.getChapterNums();

			ps.setObject(i++, chapterNumsPtr);
			return i;
		}

		@Override
        public int setAllPreparedStatement(UserLinkRoomDO t, PreparedStatement ps, int i) throws SQLException {
			Object idPtr;
				idPtr = t.getId();

			ps.setObject(i++,  idPtr);
			Object userIdPtr;
				userIdPtr = t.getUserId();

			ps.setObject(i++,  userIdPtr);
			Object roomIdPtr;
				roomIdPtr = t.getRoomId();

			ps.setObject(i++,  roomIdPtr);
			Object roomCheckIdPtr;
				roomCheckIdPtr = t.getRoomCheckId();

			ps.setObject(i++,  roomCheckIdPtr);
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
			Object startDatePtr;
				startDatePtr = t.getStartDate();

			ps.setObject(i++,  startDatePtr);
			Object endDatePtr;
				endDatePtr = t.getEndDate();

			ps.setObject(i++,  endDatePtr);
			Object chapterNumsPtr;
				chapterNumsPtr = t.getChapterNums();

			ps.setObject(i++,  chapterNumsPtr);
        	return i;
        }

		@Override
		public int setPreparedStatementKeys(UserLinkRoomDO t, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, t.getId());
			return i;
		}

		@Override
		public int setKeyPreparedStatement(Key k, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, k.getId());
			return i;
		}

		@Override public String getInsertSql(){
			return "INSERT INTO `user_link_room` (`user_id`,`room_id`,`room_check_id`,`user0`,`user1`,`user2`,`user3`,`userName0`,`userName1`,`userName2`,`userName3`,`score0`,`score1`,`score2`,`score3`,`start_date`,`end_date`,`chapter_nums`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		}

		@Override public String getReplaceSql(){
        	return "REPLACE INTO `user_link_room` (`id`,`user_id`,`room_id`,`room_check_id`,`user0`,`user1`,`user2`,`user3`,`userName0`,`userName1`,`userName2`,`userName3`,`score0`,`score1`,`score2`,`score3`,`start_date`,`end_date`,`chapter_nums`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

		@Override public String getFastInsertPrefixSql(){
			return "INSERT INTO `user_link_room` (`user_id`,`room_id`,`room_check_id`,`user0`,`user1`,`user2`,`user3`,`userName0`,`userName1`,`userName2`,`userName3`,`score0`,`score1`,`score2`,`score3`,`start_date`,`end_date`,`chapter_nums`) VALUES ";
		}
		@Override public String getFastInsertValueItemsSql(){
			return " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		}
		@Override public String getUpdateSql(){
			return "UPDATE `user_link_room` SET `user_id`=?,`room_id`=?,`room_check_id`=?,`user0`=?,`user1`=?,`user2`=?,`user3`=?,`userName0`=?,`userName1`=?,`userName2`=?,`userName3`=?,`score0`=?,`score1`=?,`score2`=?,`score3`=?,`start_date`=?,`end_date`=?,`chapter_nums`=? WHERE `id`=?";
		}

		@Override public String getSelectByKeySql(){
			return "SELECT * FROM `user_link_room` WHERE `id`=? ORDER BY `id` DESC";
		}
		@Override public String getSelectCountSql(){
			return "SELECT count(*) FROM `user_link_room`";
		}
		@Override public String getFormatSelectSql(){
			return "SELECT %s FROM `user_link_room` ORDER BY `id` DESC";
		}
		@Override public String getFormatSelectPrefixSql(){
			return "SELECT %s FROM `user_link_room` ";
		}
		@Override public String getSelectPrefixSql(){
			return "SELECT * FROM `user_link_room` ";
		}
		@Override public String getOrderByIdDescSql(){
			return " ORDER BY `id` DESC";
		}
		@Override public String getDbTableName(){
			return DB_TABLE_NAME;
		}

		@Override public RowMapper<UserLinkRoomDO> getRowMapper(){
			return new RowMapper<UserLinkRoomDO>() {
				@Override
				public UserLinkRoomDO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserLinkRoomDO o=new UserLinkRoomDO();
					o.id = rs.getInt("id");
					o.userId = rs.getInt("user_id");
					o.roomId = rs.getInt("room_id");
					o.roomCheckId = rs.getString("room_check_id");
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
					o.startDate = rs.getTimestamp("start_date");
					o.endDate = rs.getTimestamp("end_date");
					o.chapterNums = rs.getInt("chapter_nums");
					return o;
				}
			};
		}

		@Override public <C extends UserLinkRoomDO> RowMapper<C>  getRowMapper(final Class<C> cls){
			return new RowMapper<C>() {
				@Override
				public C mapRow(ResultSet rs, int rowNum) throws SQLException {
					C o;
					try{
						o = cls.newInstance();
						o.setId(rs.getInt("id"));
						o.setUserId(rs.getInt("user_id"));
						o.setRoomId(rs.getInt("room_id"));
						o.setRoomCheckId(rs.getString("room_check_id"));
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
						o.setStartDate(rs.getTimestamp("start_date"));
						o.setEndDate(rs.getTimestamp("end_date"));
						o.setChapterNums(rs.getInt("chapter_nums"));
                        return o;
					} catch (InstantiationException | IllegalAccessException e) {
						throw new SQLException("必须实现默认构造函数",e);
					}
				}
			};
		}
	}
}
