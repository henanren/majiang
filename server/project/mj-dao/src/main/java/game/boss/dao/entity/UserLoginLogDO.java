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

public class UserLoginLogDO extends EntityObject<UserLoginLogDO, UserLoginLogDO.Key>{

	private int id;
	/**用户id*/
	private int userId;
	private java.util.Date loginDate;
	private java.util.Date logoutDate;
	/**经度*/
	private Double longitude;
	/**纬度*/
	private Double latitude;
	/**ip*/
	private String ip;
	private int version;
	private String loginToken;

	public static class Key implements KeyObject<UserLoginLogDO, UserLoginLogDO.Key>{
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
			return "UserLoginLog[id:"+ id+ "]";
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
				return "UserLoginLog[id:"+ id+ "]";
			}
		};
	}




	public UserLoginLogDO() {
    }

	public UserLoginLogDO(int userId,java.util.Date loginDate,java.util.Date logoutDate,Double longitude,Double latitude,String ip,int version,String loginToken) {
		this.userId = userId;
		this.loginDate = loginDate;
		this.logoutDate = logoutDate;
		this.longitude = longitude;
		this.latitude = latitude;
		this.ip = ip;
		this.version = version;
		this.loginToken = loginToken;
	}


	public UserLoginLogDO newInstance(){
		return new UserLoginLogDO();
	}

    public void setKey(Object key){
        this.id = ((Number)key).intValue();
    }

	public int getId() {
		return id;
	}

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

	public java.util.Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(java.util.Date loginDate) {
		this.loginDate = loginDate;
		changeProperty("loginDate",loginDate);
	}

	public java.util.Date getLogoutDate() {
		return logoutDate;
	}

	public void setLogoutDate(java.util.Date logoutDate) {
		this.logoutDate = logoutDate;
		changeProperty("logoutDate",logoutDate);
	}

	/**
	 * 经度
	 **/
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * 经度
	 **/
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
		changeProperty("longitude",longitude);
	}

	/**
	 * 纬度
	 **/
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * 纬度
	 **/
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
		changeProperty("latitude",latitude);
	}

	/**
	 * ip
	 **/
	public String getIp() {
		return ip;
	}

	/**
	 * ip
	 **/
	public void setIp(String ip) {
		this.ip = ip;
		changeProperty("ip",ip);
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
		changeProperty("version",version);
	}

	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
		changeProperty("loginToken",loginToken);
	}

    @Override
    public Object get(String dbName){
        switch(dbName){
        case "id":
            return id;
        case "user_id":
            return userId;
        case "login_date":
            return loginDate;
        case "logout_date":
            return logoutDate;
        case "longitude":
            return longitude;
        case "latitude":
            return latitude;
        case "ip":
            return ip;
        case "version":
            return version;
        case "login_token":
            return loginToken;
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
		case "login_date":
			loginDate = (java.util.Date)obj;
			return true;
		case "logout_date":
			logoutDate = (java.util.Date)obj;
			return true;
		case "longitude":
			longitude = (Double)obj;
			return true;
		case "latitude":
			latitude = (Double)obj;
			return true;
		case "ip":
			ip = (String)obj;
			return true;
		case "version":
			version = (int)obj;
			return true;
		case "login_token":
			loginToken = (String)obj;
			return true;
		default :
			return false;
		}
	}

	@Override
	public String toString() {
		return "UserLoginLog[id:"+ id+",userId:"+ userId+",loginDate:"+ loginDate+",logoutDate:"+ logoutDate+",longitude:"+ longitude+",latitude:"+ latitude+",ip:"+ (ip == null ?"null":ip.substring(0, Math.min(ip.length(), 64)))+",version:"+ version+",loginToken:"+ (loginToken == null ?"null":loginToken.substring(0, Math.min(loginToken.length(), 64)))+ "]";
	}

	@Override
	@JsonIgnore
    @Transient
	public Table getTableInfo() {
		return TABLE_INFO;
	}


	public static final Table TABLE_INFO= new Table();

	public static final class Table extends TableInfo<UserLoginLogDO ,Key>{
		public static final String DB_TABLE_NAME = "user_login_log";
		private Map<String, UniqueInfo> uniqueMap;

		public static final String ID = "id";
		public static final String USER_ID = "user_id";
		public static final String LOGIN_DATE = "login_date";
		public static final String LOGOUT_DATE = "logout_date";
		public static final String LONGITUDE = "longitude";
		public static final String LATITUDE = "latitude";
		public static final String IP = "ip";
		public static final String VERSION = "version";
		public static final String LOGIN_TOKEN = "login_token";

        public static final String UNIQUE_PRIMARY = "PRIMARY";

		private Table(){
		    uniqueMap = new HashMap<>();
			super.initProperty("id", "id", int.class, new TypeReference<Integer>() {});
			super.initProperty("user_id", "userId", int.class, new TypeReference<Integer>() {});
			super.initProperty("login_date", "loginDate", java.util.Date.class, new TypeReference<java.util.Date>() {});
			super.initProperty("logout_date", "logoutDate", java.util.Date.class, new TypeReference<java.util.Date>() {});
			super.initProperty("longitude", "longitude", Double.class, new TypeReference<Double>() {});
			super.initProperty("latitude", "latitude", Double.class, new TypeReference<Double>() {});
			super.initProperty("ip", "ip", String.class, new TypeReference<String>() {});
			super.initProperty("version", "version", int.class, new TypeReference<Integer>() {});
			super.initProperty("login_token", "loginToken", String.class, new TypeReference<String>() {});
		}

		@Override public String getKeyUpdatePartialPrefixSql(){
			return "UPDATE `user_login_log` SET ";
		}

		@Override public String getKeyWhereByKeySql(){
			return " WHERE `id`=?";
		}

		@Override public String getKeyDeleteSql(){
			return "DELETE FROM `user_login_log` WHERE `id`=?";
		}

		@Override public Map<String, UniqueInfo> getUniques(){
            return uniqueMap;
		}

		@Override
        public UniqueInfo getUniques(String uniqueName){
            return uniqueMap.get(uniqueName);
        }

		@Override
		public int setPreparedStatement(UserLoginLogDO t, PreparedStatement ps, int i, boolean isSetUnique) throws SQLException {
			Object idPtr;
			idPtr = t.getId();

			Object userIdPtr;
			userIdPtr = t.getUserId();

			ps.setObject(i++, userIdPtr);
			Object loginDatePtr;
			loginDatePtr = t.getLoginDate();

			ps.setObject(i++, loginDatePtr);
			Object logoutDatePtr;
			logoutDatePtr = t.getLogoutDate();

			ps.setObject(i++, logoutDatePtr);
			Object longitudePtr;
			longitudePtr = t.getLongitude();

			ps.setObject(i++, longitudePtr);
			Object latitudePtr;
			latitudePtr = t.getLatitude();

			ps.setObject(i++, latitudePtr);
			Object ipPtr;
			ipPtr = t.getIp();

			ps.setObject(i++, ipPtr);
			Object versionPtr;
			versionPtr = t.getVersion();

			ps.setObject(i++, versionPtr);
			Object loginTokenPtr;
			loginTokenPtr = t.getLoginToken();

			ps.setObject(i++, loginTokenPtr);
			return i;
		}

		@Override
        public int setAllPreparedStatement(UserLoginLogDO t, PreparedStatement ps, int i) throws SQLException {
			Object idPtr;
				idPtr = t.getId();

			ps.setObject(i++,  idPtr);
			Object userIdPtr;
				userIdPtr = t.getUserId();

			ps.setObject(i++,  userIdPtr);
			Object loginDatePtr;
				loginDatePtr = t.getLoginDate();

			ps.setObject(i++,  loginDatePtr);
			Object logoutDatePtr;
				logoutDatePtr = t.getLogoutDate();

			ps.setObject(i++,  logoutDatePtr);
			Object longitudePtr;
				longitudePtr = t.getLongitude();

			ps.setObject(i++,  longitudePtr);
			Object latitudePtr;
				latitudePtr = t.getLatitude();

			ps.setObject(i++,  latitudePtr);
			Object ipPtr;
				ipPtr = t.getIp();

			ps.setObject(i++,  ipPtr);
			Object versionPtr;
				versionPtr = t.getVersion();

			ps.setObject(i++,  versionPtr);
			Object loginTokenPtr;
				loginTokenPtr = t.getLoginToken();

			ps.setObject(i++,  loginTokenPtr);
        	return i;
        }

		@Override
		public int setPreparedStatementKeys(UserLoginLogDO t, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, t.getId());
			return i;
		}

		@Override
		public int setKeyPreparedStatement(Key k, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, k.getId());
			return i;
		}

		@Override public String getInsertSql(){
			return "INSERT INTO `user_login_log` (`user_id`,`login_date`,`logout_date`,`longitude`,`latitude`,`ip`,`version`,`login_token`) VALUES (?,?,?,?,?,?,?,?)";
		}

		@Override public String getReplaceSql(){
        	return "REPLACE INTO `user_login_log` (`id`,`user_id`,`login_date`,`logout_date`,`longitude`,`latitude`,`ip`,`version`,`login_token`) VALUES (?,?,?,?,?,?,?,?,?)";
        }

		@Override public String getFastInsertPrefixSql(){
			return "INSERT INTO `user_login_log` (`user_id`,`login_date`,`logout_date`,`longitude`,`latitude`,`ip`,`version`,`login_token`) VALUES ";
		}
		@Override public String getFastInsertValueItemsSql(){
			return " (?,?,?,?,?,?,?,?) ";
		}
		@Override public String getUpdateSql(){
			return "UPDATE `user_login_log` SET `user_id`=?,`login_date`=?,`logout_date`=?,`longitude`=?,`latitude`=?,`ip`=?,`version`=?,`login_token`=? WHERE `id`=?";
		}

		@Override public String getSelectByKeySql(){
			return "SELECT * FROM `user_login_log` WHERE `id`=? ORDER BY `id` DESC";
		}
		@Override public String getSelectCountSql(){
			return "SELECT count(*) FROM `user_login_log`";
		}
		@Override public String getFormatSelectSql(){
			return "SELECT %s FROM `user_login_log` ORDER BY `id` DESC";
		}
		@Override public String getFormatSelectPrefixSql(){
			return "SELECT %s FROM `user_login_log` ";
		}
		@Override public String getSelectPrefixSql(){
			return "SELECT * FROM `user_login_log` ";
		}
		@Override public String getOrderByIdDescSql(){
			return " ORDER BY `id` DESC";
		}
		@Override public String getDbTableName(){
			return DB_TABLE_NAME;
		}

		@Override public RowMapper<UserLoginLogDO> getRowMapper(){
			return new RowMapper<UserLoginLogDO>() {
				@Override
				public UserLoginLogDO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserLoginLogDO o=new UserLoginLogDO();
					o.id = rs.getInt("id");
					o.userId = rs.getInt("user_id");
					o.loginDate = rs.getTimestamp("login_date");
					o.logoutDate = rs.getTimestamp("logout_date");
					o.longitude = rs.getDouble("longitude");
					o.latitude = rs.getDouble("latitude");
					o.ip = rs.getString("ip");
					o.version = rs.getInt("version");
					o.loginToken = rs.getString("login_token");
					return o;
				}
			};
		}

		@Override public <C extends UserLoginLogDO> RowMapper<C>  getRowMapper(final Class<C> cls){
			return new RowMapper<C>() {
				@Override
				public C mapRow(ResultSet rs, int rowNum) throws SQLException {
					C o;
					try{
						o = cls.newInstance();
						o.setId(rs.getInt("id"));
						o.setUserId(rs.getInt("user_id"));
						o.setLoginDate(rs.getTimestamp("login_date"));
						o.setLogoutDate(rs.getTimestamp("logout_date"));
						o.setLongitude(rs.getDouble("longitude"));
						o.setLatitude(rs.getDouble("latitude"));
						o.setIp(rs.getString("ip"));
						o.setVersion(rs.getInt("version"));
						o.setLoginToken(rs.getString("login_token"));
                        return o;
					} catch (InstantiationException | IllegalAccessException e) {
						throw new SQLException("必须实现默认构造函数",e);
					}
				}
			};
		}
	}
}
