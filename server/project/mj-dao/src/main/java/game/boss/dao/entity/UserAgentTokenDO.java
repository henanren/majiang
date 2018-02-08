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

public class UserAgentTokenDO extends EntityObject<UserAgentTokenDO, UserAgentTokenDO.Key>{

	private int id;
	/**用户id*/
	private long userId;
	/**登录token*/
	private String token;
	/**创建时间*/
	private Long createTime;

	public static class Key implements KeyObject<UserAgentTokenDO, UserAgentTokenDO.Key>{
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
			return "UserAgentToken[id:"+ id+ "]";
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
				return "UserAgentToken[id:"+ id+ "]";
			}
		};
	}




	public UserAgentTokenDO() {
    }

	public UserAgentTokenDO(long userId,String token,Long createTime) {
		this.userId = userId;
		this.token = token;
		this.createTime = createTime;
	}


	public UserAgentTokenDO newInstance(){
		return new UserAgentTokenDO();
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
	public long getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 **/
	public void setUserId(long userId) {
		this.userId = userId;
		changeProperty("userId",userId);
	}

	/**
	 * 登录token
	 **/
	public String getToken() {
		return token;
	}

	/**
	 * 登录token
	 **/
	public void setToken(String token) {
		this.token = token;
		changeProperty("token",token);
	}

	/**
	 * 创建时间
	 **/
	public Long getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 **/
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
		changeProperty("createTime",createTime);
	}

    @Override
    public Object get(String dbName){
        switch(dbName){
        case "id":
            return id;
        case "user_id":
            return userId;
        case "token":
            return token;
        case "create_time":
            return createTime;
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
			userId = (long)obj;
			return true;
		case "token":
			token = (String)obj;
			return true;
		case "create_time":
			createTime = (Long)obj;
			return true;
		default :
			return false;
		}
	}

	@Override
	public String toString() {
		return "UserAgentToken[id:"+ id+",userId:"+ userId+",token:"+ (token == null ?"null":token.substring(0, Math.min(token.length(), 64)))+",createTime:"+ createTime+ "]";
	}

	@Override
	@JsonIgnore
    @Transient
	public Table getTableInfo() {
		return TABLE_INFO;
	}


	public static final Table TABLE_INFO= new Table();

	public static final class Table extends TableInfo<UserAgentTokenDO ,Key>{
		public static final String DB_TABLE_NAME = "user_agent_token";
		private Map<String, UniqueInfo> uniqueMap;

		public static final String ID = "id";
		public static final String USER_ID = "user_id";
		public static final String TOKEN = "token";
		public static final String CREATE_TIME = "create_time";

        public static final String UNIQUE_PRIMARY = "PRIMARY";

		private Table(){
		    uniqueMap = new HashMap<>();
			super.initProperty("id", "id", int.class, new TypeReference<Integer>() {});
			super.initProperty("user_id", "userId", long.class, new TypeReference<Long>() {});
			super.initProperty("token", "token", String.class, new TypeReference<String>() {});
			super.initProperty("create_time", "createTime", Long.class, new TypeReference<Long>() {});
		}

		@Override public String getKeyUpdatePartialPrefixSql(){
			return "UPDATE `user_agent_token` SET ";
		}

		@Override public String getKeyWhereByKeySql(){
			return " WHERE `id`=?";
		}

		@Override public String getKeyDeleteSql(){
			return "DELETE FROM `user_agent_token` WHERE `id`=?";
		}

		@Override public Map<String, UniqueInfo> getUniques(){
            return uniqueMap;
		}

		@Override
        public UniqueInfo getUniques(String uniqueName){
            return uniqueMap.get(uniqueName);
        }

		@Override
		public int setPreparedStatement(UserAgentTokenDO t, PreparedStatement ps, int i, boolean isSetUnique) throws SQLException {
			Object idPtr;
			idPtr = t.getId();

			Object userIdPtr;
			userIdPtr = t.getUserId();

			ps.setObject(i++, userIdPtr);
			Object tokenPtr;
			tokenPtr = t.getToken();

			ps.setObject(i++, tokenPtr);
			Object createTimePtr;
			createTimePtr = t.getCreateTime();

			ps.setObject(i++, createTimePtr);
			return i;
		}

		@Override
        public int setAllPreparedStatement(UserAgentTokenDO t, PreparedStatement ps, int i) throws SQLException {
			Object idPtr;
				idPtr = t.getId();

			ps.setObject(i++,  idPtr);
			Object userIdPtr;
				userIdPtr = t.getUserId();

			ps.setObject(i++,  userIdPtr);
			Object tokenPtr;
				tokenPtr = t.getToken();

			ps.setObject(i++,  tokenPtr);
			Object createTimePtr;
				createTimePtr = t.getCreateTime();

			ps.setObject(i++,  createTimePtr);
        	return i;
        }

		@Override
		public int setPreparedStatementKeys(UserAgentTokenDO t, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, t.getId());
			return i;
		}

		@Override
		public int setKeyPreparedStatement(Key k, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, k.getId());
			return i;
		}

		@Override public String getInsertSql(){
			return "INSERT INTO `user_agent_token` (`user_id`,`token`,`create_time`) VALUES (?,?,?)";
		}

		@Override public String getReplaceSql(){
        	return "REPLACE INTO `user_agent_token` (`id`,`user_id`,`token`,`create_time`) VALUES (?,?,?,?)";
        }

		@Override public String getFastInsertPrefixSql(){
			return "INSERT INTO `user_agent_token` (`user_id`,`token`,`create_time`) VALUES ";
		}
		@Override public String getFastInsertValueItemsSql(){
			return " (?,?,?) ";
		}
		@Override public String getUpdateSql(){
			return "UPDATE `user_agent_token` SET `user_id`=?,`token`=?,`create_time`=? WHERE `id`=?";
		}

		@Override public String getSelectByKeySql(){
			return "SELECT * FROM `user_agent_token` WHERE `id`=? ORDER BY `id` DESC";
		}
		@Override public String getSelectCountSql(){
			return "SELECT count(*) FROM `user_agent_token`";
		}
		@Override public String getFormatSelectSql(){
			return "SELECT %s FROM `user_agent_token` ORDER BY `id` DESC";
		}
		@Override public String getFormatSelectPrefixSql(){
			return "SELECT %s FROM `user_agent_token` ";
		}
		@Override public String getSelectPrefixSql(){
			return "SELECT * FROM `user_agent_token` ";
		}
		@Override public String getOrderByIdDescSql(){
			return " ORDER BY `id` DESC";
		}
		@Override public String getDbTableName(){
			return DB_TABLE_NAME;
		}

		@Override public RowMapper<UserAgentTokenDO> getRowMapper(){
			return new RowMapper<UserAgentTokenDO>() {
				@Override
				public UserAgentTokenDO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserAgentTokenDO o=new UserAgentTokenDO();
					o.id = rs.getInt("id");
					o.userId = rs.getLong("user_id");
					o.token = rs.getString("token");
					o.createTime = rs.getLong("create_time");
					return o;
				}
			};
		}

		@Override public <C extends UserAgentTokenDO> RowMapper<C>  getRowMapper(final Class<C> cls){
			return new RowMapper<C>() {
				@Override
				public C mapRow(ResultSet rs, int rowNum) throws SQLException {
					C o;
					try{
						o = cls.newInstance();
						o.setId(rs.getInt("id"));
						o.setUserId(rs.getLong("user_id"));
						o.setToken(rs.getString("token"));
						o.setCreateTime(rs.getLong("create_time"));
                        return o;
					} catch (InstantiationException | IllegalAccessException e) {
						throw new SQLException("必须实现默认构造函数",e);
					}
				}
			};
		}
	}
}
