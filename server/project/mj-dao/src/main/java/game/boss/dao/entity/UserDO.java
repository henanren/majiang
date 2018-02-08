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

public class UserDO extends EntityObject<UserDO, UserDO.Key>{

	private int id;
	/**昵称*/
	private String name;
	private String openId;
	/**用户唯一uuid*/
	private String uuid;
	/**用户头像地址*/
	private String avatar;
	/**0:女生,1:男生,2:未知*/
	private int sex;
	private java.util.Date createDate;
	private java.util.Date updateDate;
	private int version;
	private int gold;
	private String mobile;
	private String loginToken;
	private int historyGold;
	/**用户代理级别*/
	private int level;
	/**ip*/
	private String ip;
	/**ip*/
	private double longitude;
	/**ip*/
	private double latitude;
	/**密码*/
	private String password;

	public static class Key implements KeyObject<UserDO, UserDO.Key>{
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
			return "User[id:"+ id+ "]";
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
				return "User[id:"+ id+ "]";
			}
		};
	}




	public UserDO() {
    }

	public UserDO(String name,String openId,String uuid,String avatar,int sex,java.util.Date createDate,java.util.Date updateDate,int version,int gold,String mobile,String loginToken,int historyGold,int level,String ip,double longitude,double latitude,String password) {
		this.name = name;
		this.openId = openId;
		this.uuid = uuid;
		this.avatar = avatar;
		this.sex = sex;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.version = version;
		this.gold = gold;
		this.mobile = mobile;
		this.loginToken = loginToken;
		this.historyGold = historyGold;
		this.level = level;
		this.ip = ip;
		this.longitude = longitude;
		this.latitude = latitude;
		this.password = password;
	}


	public UserDO newInstance(){
		return new UserDO();
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
	 * 昵称
	 **/
	public String getName() {
		return name;
	}

	/**
	 * 昵称
	 **/
	public void setName(String name) {
		this.name = name;
		changeProperty("name",name);
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
		changeProperty("openId",openId);
	}

	/**
	 * 用户唯一uuid
	 **/
	public String getUuid() {
		return uuid;
	}

	/**
	 * 用户唯一uuid
	 **/
	public void setUuid(String uuid) {
		this.uuid = uuid;
		changeProperty("uuid",uuid);
	}

	/**
	 * 用户头像地址
	 **/
	public String getAvatar() {
		return avatar;
	}

	/**
	 * 用户头像地址
	 **/
	public void setAvatar(String avatar) {
		this.avatar = avatar;
		changeProperty("avatar",avatar);
	}

	/**
	 * 0:女生,1:男生,2:未知
	 **/
	public int getSex() {
		return sex;
	}

	/**
	 * 0:女生,1:男生,2:未知
	 **/
	public void setSex(int sex) {
		this.sex = sex;
		changeProperty("sex",sex);
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
		changeProperty("createDate",createDate);
	}

	public java.util.Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
		changeProperty("updateDate",updateDate);
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
		changeProperty("version",version);
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
		changeProperty("gold",gold);
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
		changeProperty("mobile",mobile);
	}

	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
		changeProperty("loginToken",loginToken);
	}

	public int getHistoryGold() {
		return historyGold;
	}

	public void setHistoryGold(int historyGold) {
		this.historyGold = historyGold;
		changeProperty("historyGold",historyGold);
	}

	/**
	 * 用户代理级别
	 **/
	public int getLevel() {
		return level;
	}

	/**
	 * 用户代理级别
	 **/
	public void setLevel(int level) {
		this.level = level;
		changeProperty("level",level);
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

	/**
	 * ip
	 **/
	public double getLongitude() {
		return longitude;
	}

	/**
	 * ip
	 **/
	public void setLongitude(double longitude) {
		this.longitude = longitude;
		changeProperty("longitude",longitude);
	}

	/**
	 * ip
	 **/
	public double getLatitude() {
		return latitude;
	}

	/**
	 * ip
	 **/
	public void setLatitude(double latitude) {
		this.latitude = latitude;
		changeProperty("latitude",latitude);
	}

	/**
	 * 密码
	 **/
	public String getPassword() {
		return password;
	}

	/**
	 * 密码
	 **/
	public void setPassword(String password) {
		this.password = password;
		changeProperty("password",password);
	}

    @Override
    public Object get(String dbName){
        switch(dbName){
        case "id":
            return id;
        case "name":
            return name;
        case "open_id":
            return openId;
        case "uuid":
            return uuid;
        case "avatar":
            return avatar;
        case "sex":
            return sex;
        case "create_date":
            return createDate;
        case "update_date":
            return updateDate;
        case "version":
            return version;
        case "gold":
            return gold;
        case "mobile":
            return mobile;
        case "login_token":
            return loginToken;
        case "history_gold":
            return historyGold;
        case "level":
            return level;
        case "ip":
            return ip;
        case "longitude":
            return longitude;
        case "latitude":
            return latitude;
        case "password":
            return password;
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
		case "name":
			name = (String)obj;
			return true;
		case "open_id":
			openId = (String)obj;
			return true;
		case "uuid":
			uuid = (String)obj;
			return true;
		case "avatar":
			avatar = (String)obj;
			return true;
		case "sex":
			sex = (int)obj;
			return true;
		case "create_date":
			createDate = (java.util.Date)obj;
			return true;
		case "update_date":
			updateDate = (java.util.Date)obj;
			return true;
		case "version":
			version = (int)obj;
			return true;
		case "gold":
			gold = (int)obj;
			return true;
		case "mobile":
			mobile = (String)obj;
			return true;
		case "login_token":
			loginToken = (String)obj;
			return true;
		case "history_gold":
			historyGold = (int)obj;
			return true;
		case "level":
			level = (int)obj;
			return true;
		case "ip":
			ip = (String)obj;
			return true;
		case "longitude":
			longitude = (double)obj;
			return true;
		case "latitude":
			latitude = (double)obj;
			return true;
		case "password":
			password = (String)obj;
			return true;
		default :
			return false;
		}
	}

	@Override
	public String toString() {
		return "User[id:"+ id+",name:"+ (name == null ?"null":name.substring(0, Math.min(name.length(), 64)))+",openId:"+ (openId == null ?"null":openId.substring(0, Math.min(openId.length(), 64)))+",uuid:"+ (uuid == null ?"null":uuid.substring(0, Math.min(uuid.length(), 64)))+",avatar:"+ (avatar == null ?"null":avatar.substring(0, Math.min(avatar.length(), 64)))+",sex:"+ sex+",createDate:"+ createDate+",updateDate:"+ updateDate+",version:"+ version+",gold:"+ gold+",mobile:"+ (mobile == null ?"null":mobile.substring(0, Math.min(mobile.length(), 64)))+",loginToken:"+ (loginToken == null ?"null":loginToken.substring(0, Math.min(loginToken.length(), 64)))+",historyGold:"+ historyGold+",level:"+ level+",ip:"+ (ip == null ?"null":ip.substring(0, Math.min(ip.length(), 64)))+",longitude:"+ longitude+",latitude:"+ latitude+",password:"+ (password == null ?"null":password.substring(0, Math.min(password.length(), 64)))+ "]";
	}

	@Override
	@JsonIgnore
    @Transient
	public Table getTableInfo() {
		return TABLE_INFO;
	}


	public static final Table TABLE_INFO= new Table();

	public static final class Table extends TableInfo<UserDO ,Key>{
		public static final String DB_TABLE_NAME = "user";
		private Map<String, UniqueInfo> uniqueMap;

		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String OPEN_ID = "open_id";
		public static final String UUID = "uuid";
		public static final String AVATAR = "avatar";
		public static final String SEX = "sex";
		public static final String CREATE_DATE = "create_date";
		public static final String UPDATE_DATE = "update_date";
		public static final String VERSION = "version";
		public static final String GOLD = "gold";
		public static final String MOBILE = "mobile";
		public static final String LOGIN_TOKEN = "login_token";
		public static final String HISTORY_GOLD = "history_gold";
		public static final String LEVEL = "level";
		public static final String IP = "ip";
		public static final String LONGITUDE = "longitude";
		public static final String LATITUDE = "latitude";
		public static final String PASSWORD = "password";

        public static final String UNIQUE_OPEN_ID = "open_id";
        public static final String UNIQUE_MOBILE = "mobile";
        public static final String UNIQUE_PRIMARY = "PRIMARY";
        public static final String UNIQUE_UUID = "uuid";

		private Table(){
		    uniqueMap = new HashMap<>();
		    uniqueMap.put("open_id", new UniqueInfo("open_id", "open_id"));
		    uniqueMap.put("mobile", new UniqueInfo("mobile", "mobile"));
		    uniqueMap.put("uuid", new UniqueInfo("uuid", "uuid"));
			super.initProperty("id", "id", int.class, new TypeReference<Integer>() {});
			super.initProperty("name", "name", String.class, new TypeReference<String>() {});
			super.initProperty("open_id", "openId", String.class, new TypeReference<String>() {});
			super.initProperty("uuid", "uuid", String.class, new TypeReference<String>() {});
			super.initProperty("avatar", "avatar", String.class, new TypeReference<String>() {});
			super.initProperty("sex", "sex", int.class, new TypeReference<Integer>() {});
			super.initProperty("create_date", "createDate", java.util.Date.class, new TypeReference<java.util.Date>() {});
			super.initProperty("update_date", "updateDate", java.util.Date.class, new TypeReference<java.util.Date>() {});
			super.initProperty("version", "version", int.class, new TypeReference<Integer>() {});
			super.initProperty("gold", "gold", int.class, new TypeReference<Integer>() {});
			super.initProperty("mobile", "mobile", String.class, new TypeReference<String>() {});
			super.initProperty("login_token", "loginToken", String.class, new TypeReference<String>() {});
			super.initProperty("history_gold", "historyGold", int.class, new TypeReference<Integer>() {});
			super.initProperty("level", "level", int.class, new TypeReference<Integer>() {});
			super.initProperty("ip", "ip", String.class, new TypeReference<String>() {});
			super.initProperty("longitude", "longitude", double.class, new TypeReference<Double>() {});
			super.initProperty("latitude", "latitude", double.class, new TypeReference<Double>() {});
			super.initProperty("password", "password", String.class, new TypeReference<String>() {});
		}

		@Override public String getKeyUpdatePartialPrefixSql(){
			return "UPDATE `user` SET ";
		}

		@Override public String getKeyWhereByKeySql(){
			return " WHERE `id`=?";
		}

		@Override public String getKeyDeleteSql(){
			return "DELETE FROM `user` WHERE `id`=?";
		}

		@Override public Map<String, UniqueInfo> getUniques(){
            return uniqueMap;
		}

		@Override
        public UniqueInfo getUniques(String uniqueName){
            return uniqueMap.get(uniqueName);
        }

		@Override
		public int setPreparedStatement(UserDO t, PreparedStatement ps, int i, boolean isSetUnique) throws SQLException {
			Object idPtr;
			idPtr = t.getId();

			Object namePtr;
			namePtr = t.getName();

			ps.setObject(i++, namePtr);
			Object openIdPtr;
			openIdPtr = t.getOpenId();

			if(isSetUnique){
				ps.setObject(i++, openIdPtr);
			}
			Object uuidPtr;
			uuidPtr = t.getUuid();

			if(isSetUnique){
				ps.setObject(i++, uuidPtr);
			}
			Object avatarPtr;
			avatarPtr = t.getAvatar();

			ps.setObject(i++, avatarPtr);
			Object sexPtr;
			sexPtr = t.getSex();

			ps.setObject(i++, sexPtr);
			Object createDatePtr;
			createDatePtr = t.getCreateDate();

			ps.setObject(i++, createDatePtr);
			Object updateDatePtr;
			updateDatePtr = t.getUpdateDate();

			ps.setObject(i++, updateDatePtr);
			Object versionPtr;
			versionPtr = t.getVersion();

			ps.setObject(i++, versionPtr);
			Object goldPtr;
			goldPtr = t.getGold();

			ps.setObject(i++, goldPtr);
			Object mobilePtr;
			mobilePtr = t.getMobile();

			if(isSetUnique){
				ps.setObject(i++, mobilePtr);
			}
			Object loginTokenPtr;
			loginTokenPtr = t.getLoginToken();

			ps.setObject(i++, loginTokenPtr);
			Object historyGoldPtr;
			historyGoldPtr = t.getHistoryGold();

			ps.setObject(i++, historyGoldPtr);
			Object levelPtr;
			levelPtr = t.getLevel();

			ps.setObject(i++, levelPtr);
			Object ipPtr;
			ipPtr = t.getIp();

			ps.setObject(i++, ipPtr);
			Object longitudePtr;
			longitudePtr = t.getLongitude();

			ps.setObject(i++, longitudePtr);
			Object latitudePtr;
			latitudePtr = t.getLatitude();

			ps.setObject(i++, latitudePtr);
			Object passwordPtr;
			passwordPtr = t.getPassword();

			ps.setObject(i++, passwordPtr);
			return i;
		}

		@Override
        public int setAllPreparedStatement(UserDO t, PreparedStatement ps, int i) throws SQLException {
			Object idPtr;
				idPtr = t.getId();

			ps.setObject(i++,  idPtr);
			Object namePtr;
				namePtr = t.getName();

			ps.setObject(i++,  namePtr);
			Object openIdPtr;
				openIdPtr = t.getOpenId();

			ps.setObject(i++,  openIdPtr);
			Object uuidPtr;
				uuidPtr = t.getUuid();

			ps.setObject(i++,  uuidPtr);
			Object avatarPtr;
				avatarPtr = t.getAvatar();

			ps.setObject(i++,  avatarPtr);
			Object sexPtr;
				sexPtr = t.getSex();

			ps.setObject(i++,  sexPtr);
			Object createDatePtr;
				createDatePtr = t.getCreateDate();

			ps.setObject(i++,  createDatePtr);
			Object updateDatePtr;
				updateDatePtr = t.getUpdateDate();

			ps.setObject(i++,  updateDatePtr);
			Object versionPtr;
				versionPtr = t.getVersion();

			ps.setObject(i++,  versionPtr);
			Object goldPtr;
				goldPtr = t.getGold();

			ps.setObject(i++,  goldPtr);
			Object mobilePtr;
				mobilePtr = t.getMobile();

			ps.setObject(i++,  mobilePtr);
			Object loginTokenPtr;
				loginTokenPtr = t.getLoginToken();

			ps.setObject(i++,  loginTokenPtr);
			Object historyGoldPtr;
				historyGoldPtr = t.getHistoryGold();

			ps.setObject(i++,  historyGoldPtr);
			Object levelPtr;
				levelPtr = t.getLevel();

			ps.setObject(i++,  levelPtr);
			Object ipPtr;
				ipPtr = t.getIp();

			ps.setObject(i++,  ipPtr);
			Object longitudePtr;
				longitudePtr = t.getLongitude();

			ps.setObject(i++,  longitudePtr);
			Object latitudePtr;
				latitudePtr = t.getLatitude();

			ps.setObject(i++,  latitudePtr);
			Object passwordPtr;
				passwordPtr = t.getPassword();

			ps.setObject(i++,  passwordPtr);
        	return i;
        }

		@Override
		public int setPreparedStatementKeys(UserDO t, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, t.getId());
			return i;
		}

		@Override
		public int setKeyPreparedStatement(Key k, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, k.getId());
			return i;
		}

		@Override public String getInsertSql(){
			return "INSERT INTO `user` (`name`,`open_id`,`uuid`,`avatar`,`sex`,`create_date`,`update_date`,`version`,`gold`,`mobile`,`login_token`,`history_gold`,`level`,`ip`,`longitude`,`latitude`,`password`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		}

		@Override public String getReplaceSql(){
        	return "REPLACE INTO `user` (`id`,`name`,`open_id`,`uuid`,`avatar`,`sex`,`create_date`,`update_date`,`version`,`gold`,`mobile`,`login_token`,`history_gold`,`level`,`ip`,`longitude`,`latitude`,`password`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

		@Override public String getFastInsertPrefixSql(){
			return "INSERT INTO `user` (`name`,`open_id`,`uuid`,`avatar`,`sex`,`create_date`,`update_date`,`version`,`gold`,`mobile`,`login_token`,`history_gold`,`level`,`ip`,`longitude`,`latitude`,`password`) VALUES ";
		}
		@Override public String getFastInsertValueItemsSql(){
			return " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		}
		@Override public String getUpdateSql(){
			return "UPDATE `user` SET `name`=?,`open_id`=?,`uuid`=?,`avatar`=?,`sex`=?,`create_date`=?,`update_date`=?,`version`=?,`gold`=?,`mobile`=?,`login_token`=?,`history_gold`=?,`level`=?,`ip`=?,`longitude`=?,`latitude`=?,`password`=? WHERE `id`=?";
		}

		@Override public String getSelectByKeySql(){
			return "SELECT * FROM `user` WHERE `id`=? ORDER BY `id` DESC";
		}
		@Override public String getSelectCountSql(){
			return "SELECT count(*) FROM `user`";
		}
		@Override public String getFormatSelectSql(){
			return "SELECT %s FROM `user` ORDER BY `id` DESC";
		}
		@Override public String getFormatSelectPrefixSql(){
			return "SELECT %s FROM `user` ";
		}
		@Override public String getSelectPrefixSql(){
			return "SELECT * FROM `user` ";
		}
		@Override public String getOrderByIdDescSql(){
			return " ORDER BY `id` DESC";
		}
		@Override public String getDbTableName(){
			return DB_TABLE_NAME;
		}

		@Override public RowMapper<UserDO> getRowMapper(){
			return new RowMapper<UserDO>() {
				@Override
				public UserDO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserDO o=new UserDO();
					o.id = rs.getInt("id");
					o.name = rs.getString("name");
					o.openId = rs.getString("open_id");
					o.uuid = rs.getString("uuid");
					o.avatar = rs.getString("avatar");
					o.sex = rs.getInt("sex");
					o.createDate = rs.getTimestamp("create_date");
					o.updateDate = rs.getTimestamp("update_date");
					o.version = rs.getInt("version");
					o.gold = rs.getInt("gold");
					o.mobile = rs.getString("mobile");
					o.loginToken = rs.getString("login_token");
					o.historyGold = rs.getInt("history_gold");
					o.level = rs.getInt("level");
					o.ip = rs.getString("ip");
					o.longitude = rs.getDouble("longitude");
					o.latitude = rs.getDouble("latitude");
					o.password = rs.getString("password");
					return o;
				}
			};
		}

		@Override public <C extends UserDO> RowMapper<C>  getRowMapper(final Class<C> cls){
			return new RowMapper<C>() {
				@Override
				public C mapRow(ResultSet rs, int rowNum) throws SQLException {
					C o;
					try{
						o = cls.newInstance();
						o.setId(rs.getInt("id"));
						o.setName(rs.getString("name"));
						o.setOpenId(rs.getString("open_id"));
						o.setUuid(rs.getString("uuid"));
						o.setAvatar(rs.getString("avatar"));
						o.setSex(rs.getInt("sex"));
						o.setCreateDate(rs.getTimestamp("create_date"));
						o.setUpdateDate(rs.getTimestamp("update_date"));
						o.setVersion(rs.getInt("version"));
						o.setGold(rs.getInt("gold"));
						o.setMobile(rs.getString("mobile"));
						o.setLoginToken(rs.getString("login_token"));
						o.setHistoryGold(rs.getInt("history_gold"));
						o.setLevel(rs.getInt("level"));
						o.setIp(rs.getString("ip"));
						o.setLongitude(rs.getDouble("longitude"));
						o.setLatitude(rs.getDouble("latitude"));
						o.setPassword(rs.getString("password"));
                        return o;
					} catch (InstantiationException | IllegalAccessException e) {
						throw new SQLException("必须实现默认构造函数",e);
					}
				}
			};
		}
	}
}
