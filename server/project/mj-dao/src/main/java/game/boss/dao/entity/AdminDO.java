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

public class AdminDO extends EntityObject<AdminDO, AdminDO.Key>{

	private int id;
	/**用户名*/
	private String name;
	/**密码*/
	private String password;
	/**登录token*/
	private String token;

	public static class Key implements KeyObject<AdminDO, AdminDO.Key>{
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
			return "Admin[id:"+ id+ "]";
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
				return "Admin[id:"+ id+ "]";
			}
		};
	}




	public AdminDO() {
    }

	public AdminDO(String name,String password,String token) {
		this.name = name;
		this.password = password;
		this.token = token;
	}


	public AdminDO newInstance(){
		return new AdminDO();
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
	 * 用户名
	 **/
	public String getName() {
		return name;
	}

	/**
	 * 用户名
	 **/
	public void setName(String name) {
		this.name = name;
		changeProperty("name",name);
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

    @Override
    public Object get(String dbName){
        switch(dbName){
        case "id":
            return id;
        case "name":
            return name;
        case "password":
            return password;
        case "token":
            return token;
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
		case "password":
			password = (String)obj;
			return true;
		case "token":
			token = (String)obj;
			return true;
		default :
			return false;
		}
	}

	@Override
	public String toString() {
		return "Admin[id:"+ id+",name:"+ (name == null ?"null":name.substring(0, Math.min(name.length(), 64)))+",password:"+ (password == null ?"null":password.substring(0, Math.min(password.length(), 64)))+",token:"+ (token == null ?"null":token.substring(0, Math.min(token.length(), 64)))+ "]";
	}

	@Override
	@JsonIgnore
    @Transient
	public Table getTableInfo() {
		return TABLE_INFO;
	}


	public static final Table TABLE_INFO= new Table();

	public static final class Table extends TableInfo<AdminDO ,Key>{
		public static final String DB_TABLE_NAME = "admin";
		private Map<String, UniqueInfo> uniqueMap;

		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String PASSWORD = "password";
		public static final String TOKEN = "token";

        public static final String UNIQUE_NAME = "name";
        public static final String UNIQUE_PRIMARY = "PRIMARY";

		private Table(){
		    uniqueMap = new HashMap<>();
		    uniqueMap.put("name", new UniqueInfo("name", "name"));
			super.initProperty("id", "id", int.class, new TypeReference<Integer>() {});
			super.initProperty("name", "name", String.class, new TypeReference<String>() {});
			super.initProperty("password", "password", String.class, new TypeReference<String>() {});
			super.initProperty("token", "token", String.class, new TypeReference<String>() {});
		}

		@Override public String getKeyUpdatePartialPrefixSql(){
			return "UPDATE `admin` SET ";
		}

		@Override public String getKeyWhereByKeySql(){
			return " WHERE `id`=?";
		}

		@Override public String getKeyDeleteSql(){
			return "DELETE FROM `admin` WHERE `id`=?";
		}

		@Override public Map<String, UniqueInfo> getUniques(){
            return uniqueMap;
		}

		@Override
        public UniqueInfo getUniques(String uniqueName){
            return uniqueMap.get(uniqueName);
        }

		@Override
		public int setPreparedStatement(AdminDO t, PreparedStatement ps, int i, boolean isSetUnique) throws SQLException {
			Object idPtr;
			idPtr = t.getId();

			Object namePtr;
			namePtr = t.getName();

			if(isSetUnique){
				ps.setObject(i++, namePtr);
			}
			Object passwordPtr;
			passwordPtr = t.getPassword();

			ps.setObject(i++, passwordPtr);
			Object tokenPtr;
			tokenPtr = t.getToken();

			ps.setObject(i++, tokenPtr);
			return i;
		}

		@Override
        public int setAllPreparedStatement(AdminDO t, PreparedStatement ps, int i) throws SQLException {
			Object idPtr;
				idPtr = t.getId();

			ps.setObject(i++,  idPtr);
			Object namePtr;
				namePtr = t.getName();

			ps.setObject(i++,  namePtr);
			Object passwordPtr;
				passwordPtr = t.getPassword();

			ps.setObject(i++,  passwordPtr);
			Object tokenPtr;
				tokenPtr = t.getToken();

			ps.setObject(i++,  tokenPtr);
        	return i;
        }

		@Override
		public int setPreparedStatementKeys(AdminDO t, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, t.getId());
			return i;
		}

		@Override
		public int setKeyPreparedStatement(Key k, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, k.getId());
			return i;
		}

		@Override public String getInsertSql(){
			return "INSERT INTO `admin` (`name`,`password`,`token`) VALUES (?,?,?)";
		}

		@Override public String getReplaceSql(){
        	return "REPLACE INTO `admin` (`id`,`name`,`password`,`token`) VALUES (?,?,?,?)";
        }

		@Override public String getFastInsertPrefixSql(){
			return "INSERT INTO `admin` (`name`,`password`,`token`) VALUES ";
		}
		@Override public String getFastInsertValueItemsSql(){
			return " (?,?,?) ";
		}
		@Override public String getUpdateSql(){
			return "UPDATE `admin` SET `name`=?,`password`=?,`token`=? WHERE `id`=?";
		}

		@Override public String getSelectByKeySql(){
			return "SELECT * FROM `admin` WHERE `id`=? ORDER BY `id` DESC";
		}
		@Override public String getSelectCountSql(){
			return "SELECT count(*) FROM `admin`";
		}
		@Override public String getFormatSelectSql(){
			return "SELECT %s FROM `admin` ORDER BY `id` DESC";
		}
		@Override public String getFormatSelectPrefixSql(){
			return "SELECT %s FROM `admin` ";
		}
		@Override public String getSelectPrefixSql(){
			return "SELECT * FROM `admin` ";
		}
		@Override public String getOrderByIdDescSql(){
			return " ORDER BY `id` DESC";
		}
		@Override public String getDbTableName(){
			return DB_TABLE_NAME;
		}

		@Override public RowMapper<AdminDO> getRowMapper(){
			return new RowMapper<AdminDO>() {
				@Override
				public AdminDO mapRow(ResultSet rs, int rowNum) throws SQLException {
					AdminDO o=new AdminDO();
					o.id = rs.getInt("id");
					o.name = rs.getString("name");
					o.password = rs.getString("password");
					o.token = rs.getString("token");
					return o;
				}
			};
		}

		@Override public <C extends AdminDO> RowMapper<C>  getRowMapper(final Class<C> cls){
			return new RowMapper<C>() {
				@Override
				public C mapRow(ResultSet rs, int rowNum) throws SQLException {
					C o;
					try{
						o = cls.newInstance();
						o.setId(rs.getInt("id"));
						o.setName(rs.getString("name"));
						o.setPassword(rs.getString("password"));
						o.setToken(rs.getString("token"));
                        return o;
					} catch (InstantiationException | IllegalAccessException e) {
						throw new SQLException("必须实现默认构造函数",e);
					}
				}
			};
		}
	}
}
