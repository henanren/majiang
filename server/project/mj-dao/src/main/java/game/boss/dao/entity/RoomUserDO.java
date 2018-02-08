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

public class RoomUserDO extends EntityObject<RoomUserDO, RoomUserDO.Key>{

	/**用户id*/
	private int userId;
	private int locationIndex;
	private Integer roomId;
	private java.util.Date startDate;
	private java.util.Date endDate;
	/**房间的check-id,进入id,可以重复,但是不允许同时活跃状态的id 相同*/
	private String roomCheckId;
	private int version;

	public static class Key implements KeyObject<RoomUserDO, RoomUserDO.Key>{
    	private int userId;

		public Key() {
   		}

		public Key(int userId) {
			this.userId = userId;
		}

		@JsonIgnore
		@Transient
		@Override
		public Object[] getQueryParams() {
			return new Object[]{
				getUserId()
			};
		}

		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}

        @Override
        public String toStringKey(){
            return String.valueOf(getUserId());

        }

		@Override
		public Table getTableInfo() {
			return TABLE_INFO;
		}

		@Override
		public String toString() {
			return "RoomUser[userId:"+ userId+ "]";
		}
	}

	@Override
	public Key getKey() {
		return new Key() {

			public int getUserId() {
				return userId;
			}

			public void setUserId(int userId) {
				RoomUserDO.this.userId  = userId;
				RoomUserDO.this.changeProperty("userId",userId);
			}

			@Override
			public String toString() {
				return "RoomUser[userId:"+ userId+ "]";
			}
		};
	}




	public RoomUserDO() {
    }

	public RoomUserDO(int userId,int locationIndex,Integer roomId,java.util.Date startDate,java.util.Date endDate,String roomCheckId,int version) {
		this.userId = userId;
		this.locationIndex = locationIndex;
		this.roomId = roomId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.roomCheckId = roomCheckId;
		this.version = version;
	}


	public RoomUserDO newInstance(){
		return new RoomUserDO();
	}

    public void setKey(Object key){
        this.userId = ((Number)key).intValue();
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

	public int getLocationIndex() {
		return locationIndex;
	}

	public void setLocationIndex(int locationIndex) {
		this.locationIndex = locationIndex;
		changeProperty("locationIndex",locationIndex);
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
		changeProperty("roomId",roomId);
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
		changeProperty("version",version);
	}

    @Override
    public Object get(String dbName){
        switch(dbName){
        case "user_id":
            return userId;
        case "location_index":
            return locationIndex;
        case "room_id":
            return roomId;
        case "start_date":
            return startDate;
        case "end_date":
            return endDate;
        case "room_check_id":
            return roomCheckId;
        case "version":
            return version;
        default :
            return null;
        }
    }


	@Override
	public boolean set(String dbName, Object obj) {
		switch(dbName){
		case "user_id":
			userId = (int)obj;
			return true;
		case "location_index":
			locationIndex = (int)obj;
			return true;
		case "room_id":
			roomId = (Integer)obj;
			return true;
		case "start_date":
			startDate = (java.util.Date)obj;
			return true;
		case "end_date":
			endDate = (java.util.Date)obj;
			return true;
		case "room_check_id":
			roomCheckId = (String)obj;
			return true;
		case "version":
			version = (int)obj;
			return true;
		default :
			return false;
		}
	}

	@Override
	public String toString() {
		return "RoomUser[userId:"+ userId+",locationIndex:"+ locationIndex+",roomId:"+ roomId+",startDate:"+ startDate+",endDate:"+ endDate+",roomCheckId:"+ (roomCheckId == null ?"null":roomCheckId.substring(0, Math.min(roomCheckId.length(), 64)))+",version:"+ version+ "]";
	}

	@Override
	@JsonIgnore
    @Transient
	public Table getTableInfo() {
		return TABLE_INFO;
	}


	public static final Table TABLE_INFO= new Table();

	public static final class Table extends TableInfo<RoomUserDO ,Key>{
		public static final String DB_TABLE_NAME = "room_user";
		private Map<String, UniqueInfo> uniqueMap;

		public static final String USER_ID = "user_id";
		public static final String LOCATION_INDEX = "location_index";
		public static final String ROOM_ID = "room_id";
		public static final String START_DATE = "start_date";
		public static final String END_DATE = "end_date";
		public static final String ROOM_CHECK_ID = "room_check_id";
		public static final String VERSION = "version";

        public static final String UNIQUE_PRIMARY = "PRIMARY";

		private Table(){
		    uniqueMap = new HashMap<>();
			super.initProperty("user_id", "userId", int.class, new TypeReference<Integer>() {});
			super.initProperty("location_index", "locationIndex", int.class, new TypeReference<Integer>() {});
			super.initProperty("room_id", "roomId", Integer.class, new TypeReference<Integer>() {});
			super.initProperty("start_date", "startDate", java.util.Date.class, new TypeReference<java.util.Date>() {});
			super.initProperty("end_date", "endDate", java.util.Date.class, new TypeReference<java.util.Date>() {});
			super.initProperty("room_check_id", "roomCheckId", String.class, new TypeReference<String>() {});
			super.initProperty("version", "version", int.class, new TypeReference<Integer>() {});
		}

		@Override public String getKeyUpdatePartialPrefixSql(){
			return "UPDATE `room_user` SET ";
		}

		@Override public String getKeyWhereByKeySql(){
			return " WHERE `user_id`=?";
		}

		@Override public String getKeyDeleteSql(){
			return "DELETE FROM `room_user` WHERE `user_id`=?";
		}

		@Override public Map<String, UniqueInfo> getUniques(){
            return uniqueMap;
		}

		@Override
        public UniqueInfo getUniques(String uniqueName){
            return uniqueMap.get(uniqueName);
        }

		@Override
		public int setPreparedStatement(RoomUserDO t, PreparedStatement ps, int i, boolean isSetUnique) throws SQLException {
			Object userIdPtr;
			userIdPtr = t.getUserId();

			if(isSetUnique){
				ps.setObject(i++, userIdPtr);
			}
			Object locationIndexPtr;
			locationIndexPtr = t.getLocationIndex();

			ps.setObject(i++, locationIndexPtr);
			Object roomIdPtr;
			roomIdPtr = t.getRoomId();

			ps.setObject(i++, roomIdPtr);
			Object startDatePtr;
			startDatePtr = t.getStartDate();

			ps.setObject(i++, startDatePtr);
			Object endDatePtr;
			endDatePtr = t.getEndDate();

			ps.setObject(i++, endDatePtr);
			Object roomCheckIdPtr;
			roomCheckIdPtr = t.getRoomCheckId();

			ps.setObject(i++, roomCheckIdPtr);
			Object versionPtr;
			versionPtr = t.getVersion();

			ps.setObject(i++, versionPtr);
			return i;
		}

		@Override
        public int setAllPreparedStatement(RoomUserDO t, PreparedStatement ps, int i) throws SQLException {
			Object userIdPtr;
				userIdPtr = t.getUserId();

			ps.setObject(i++,  userIdPtr);
			Object locationIndexPtr;
				locationIndexPtr = t.getLocationIndex();

			ps.setObject(i++,  locationIndexPtr);
			Object roomIdPtr;
				roomIdPtr = t.getRoomId();

			ps.setObject(i++,  roomIdPtr);
			Object startDatePtr;
				startDatePtr = t.getStartDate();

			ps.setObject(i++,  startDatePtr);
			Object endDatePtr;
				endDatePtr = t.getEndDate();

			ps.setObject(i++,  endDatePtr);
			Object roomCheckIdPtr;
				roomCheckIdPtr = t.getRoomCheckId();

			ps.setObject(i++,  roomCheckIdPtr);
			Object versionPtr;
				versionPtr = t.getVersion();

			ps.setObject(i++,  versionPtr);
        	return i;
        }

		@Override
		public int setPreparedStatementKeys(RoomUserDO t, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, t.getUserId());
			return i;
		}

		@Override
		public int setKeyPreparedStatement(Key k, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, k.getUserId());
			return i;
		}

		@Override public String getInsertSql(){
			return "INSERT INTO `room_user` (`user_id`,`location_index`,`room_id`,`start_date`,`end_date`,`room_check_id`,`version`) VALUES (?,?,?,?,?,?,?)";
		}

		@Override public String getReplaceSql(){
        	return "REPLACE INTO `room_user` (`user_id`,`location_index`,`room_id`,`start_date`,`end_date`,`room_check_id`,`version`) VALUES (?,?,?,?,?,?,?)";
        }

		@Override public String getFastInsertPrefixSql(){
			return "INSERT INTO `room_user` (`user_id`,`location_index`,`room_id`,`start_date`,`end_date`,`room_check_id`,`version`) VALUES ";
		}
		@Override public String getFastInsertValueItemsSql(){
			return " (?,?,?,?,?,?,?) ";
		}
		@Override public String getUpdateSql(){
			return "UPDATE `room_user` SET `user_id`=?,`location_index`=?,`room_id`=?,`start_date`=?,`end_date`=?,`room_check_id`=?,`version`=? WHERE `user_id`=?";
		}

		@Override public String getSelectByKeySql(){
			return "SELECT * FROM `room_user` WHERE `user_id`=? ORDER BY `user_id` DESC";
		}
		@Override public String getSelectCountSql(){
			return "SELECT count(*) FROM `room_user`";
		}
		@Override public String getFormatSelectSql(){
			return "SELECT %s FROM `room_user` ORDER BY `user_id` DESC";
		}
		@Override public String getFormatSelectPrefixSql(){
			return "SELECT %s FROM `room_user` ";
		}
		@Override public String getSelectPrefixSql(){
			return "SELECT * FROM `room_user` ";
		}
		@Override public String getOrderByIdDescSql(){
			return " ORDER BY `user_id` DESC";
		}
		@Override public String getDbTableName(){
			return DB_TABLE_NAME;
		}

		@Override public RowMapper<RoomUserDO> getRowMapper(){
			return new RowMapper<RoomUserDO>() {
				@Override
				public RoomUserDO mapRow(ResultSet rs, int rowNum) throws SQLException {
					RoomUserDO o=new RoomUserDO();
					o.userId = rs.getInt("user_id");
					o.locationIndex = rs.getInt("location_index");
					o.roomId = rs.getInt("room_id");
					o.startDate = rs.getTimestamp("start_date");
					o.endDate = rs.getTimestamp("end_date");
					o.roomCheckId = rs.getString("room_check_id");
					o.version = rs.getInt("version");
					return o;
				}
			};
		}

		@Override public <C extends RoomUserDO> RowMapper<C>  getRowMapper(final Class<C> cls){
			return new RowMapper<C>() {
				@Override
				public C mapRow(ResultSet rs, int rowNum) throws SQLException {
					C o;
					try{
						o = cls.newInstance();
						o.setUserId(rs.getInt("user_id"));
						o.setLocationIndex(rs.getInt("location_index"));
						o.setRoomId(rs.getInt("room_id"));
						o.setStartDate(rs.getTimestamp("start_date"));
						o.setEndDate(rs.getTimestamp("end_date"));
						o.setRoomCheckId(rs.getString("room_check_id"));
						o.setVersion(rs.getInt("version"));
                        return o;
					} catch (InstantiationException | IllegalAccessException e) {
						throw new SQLException("必须实现默认构造函数",e);
					}
				}
			};
		}
	}
}
