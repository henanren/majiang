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

public class RoomCheckIdPoolDO extends EntityObject<RoomCheckIdPoolDO, RoomCheckIdPoolDO.Key>{

	/**随机id*/
	private String id;
	/**0,未使用,1,在使用,2,缓存*/
	private int state;

	public static class Key implements KeyObject<RoomCheckIdPoolDO, RoomCheckIdPoolDO.Key>{
    	private String id;

		public Key() {
   		}

		public Key(String id) {
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

		public String getId() {
			return id;
		}
		public void setId(String id) {
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
			return "RoomCheckIdPool[id:"+ (id == null ?"null":id.substring(0, Math.min(id.length(), 64)))+ "]";
		}
	}

	@Override
	public Key getKey() {
		return new Key() {

			public String getId() {
				return id;
			}

			public void setId(String id) {
				RoomCheckIdPoolDO.this.id  = id;
				RoomCheckIdPoolDO.this.changeProperty("id",id);
			}

			@Override
			public String toString() {
				return "RoomCheckIdPool[id:"+ (id == null ?"null":id.substring(0, Math.min(id.length(), 64)))+ "]";
			}
		};
	}




	public RoomCheckIdPoolDO() {
    }

	public RoomCheckIdPoolDO(String id,int state) {
		this.id = id;
		this.state = state;
	}


	public RoomCheckIdPoolDO newInstance(){
		return new RoomCheckIdPoolDO();
	}

    public void setKey(Object key){
        this.id = (String)key;
    }

	/**
	 * 随机id
	 **/
	public String getId() {
		return id;
	}

	/**
	 * 随机id
	 **/
	public void setId(String id) {
		this.id = id;
		changeProperty("id",id);
	}

	/**
	 * 0,未使用,1,在使用,2,缓存
	 **/
	public int getState() {
		return state;
	}

	/**
	 * 0,未使用,1,在使用,2,缓存
	 **/
	public void setState(int state) {
		this.state = state;
		changeProperty("state",state);
	}

    @Override
    public Object get(String dbName){
        switch(dbName){
        case "id":
            return id;
        case "state":
            return state;
        default :
            return null;
        }
    }


	@Override
	public boolean set(String dbName, Object obj) {
		switch(dbName){
		case "id":
			id = (String)obj;
			return true;
		case "state":
			state = (int)obj;
			return true;
		default :
			return false;
		}
	}

	@Override
	public String toString() {
		return "RoomCheckIdPool[id:"+ (id == null ?"null":id.substring(0, Math.min(id.length(), 64)))+",state:"+ state+ "]";
	}

	@Override
	@JsonIgnore
    @Transient
	public Table getTableInfo() {
		return TABLE_INFO;
	}


	public static final Table TABLE_INFO= new Table();

	public static final class Table extends TableInfo<RoomCheckIdPoolDO ,Key>{
		public static final String DB_TABLE_NAME = "room_check_id_pool";
		private Map<String, UniqueInfo> uniqueMap;

		public static final String ID = "id";
		public static final String STATE = "state";

        public static final String UNIQUE_PRIMARY = "PRIMARY";

		private Table(){
		    uniqueMap = new HashMap<>();
			super.initProperty("id", "id", String.class, new TypeReference<String>() {});
			super.initProperty("state", "state", int.class, new TypeReference<Integer>() {});
		}

		@Override public String getKeyUpdatePartialPrefixSql(){
			return "UPDATE `room_check_id_pool` SET ";
		}

		@Override public String getKeyWhereByKeySql(){
			return " WHERE `id`=?";
		}

		@Override public String getKeyDeleteSql(){
			return "DELETE FROM `room_check_id_pool` WHERE `id`=?";
		}

		@Override public Map<String, UniqueInfo> getUniques(){
            return uniqueMap;
		}

		@Override
        public UniqueInfo getUniques(String uniqueName){
            return uniqueMap.get(uniqueName);
        }

		@Override
		public int setPreparedStatement(RoomCheckIdPoolDO t, PreparedStatement ps, int i, boolean isSetUnique) throws SQLException {
			Object idPtr;
			idPtr = t.getId();

			if(isSetUnique){
				ps.setObject(i++, idPtr);
			}
			Object statePtr;
			statePtr = t.getState();

			ps.setObject(i++, statePtr);
			return i;
		}

		@Override
        public int setAllPreparedStatement(RoomCheckIdPoolDO t, PreparedStatement ps, int i) throws SQLException {
			Object idPtr;
				idPtr = t.getId();

			ps.setObject(i++,  idPtr);
			Object statePtr;
				statePtr = t.getState();

			ps.setObject(i++,  statePtr);
        	return i;
        }

		@Override
		public int setPreparedStatementKeys(RoomCheckIdPoolDO t, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, t.getId());
			return i;
		}

		@Override
		public int setKeyPreparedStatement(Key k, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, k.getId());
			return i;
		}

		@Override public String getInsertSql(){
			return "INSERT INTO `room_check_id_pool` (`id`,`state`) VALUES (?,?)";
		}

		@Override public String getReplaceSql(){
        	return "REPLACE INTO `room_check_id_pool` (`id`,`state`) VALUES (?,?)";
        }

		@Override public String getFastInsertPrefixSql(){
			return "INSERT INTO `room_check_id_pool` (`id`,`state`) VALUES ";
		}
		@Override public String getFastInsertValueItemsSql(){
			return " (?,?) ";
		}
		@Override public String getUpdateSql(){
			return "UPDATE `room_check_id_pool` SET `id`=?,`state`=? WHERE `id`=?";
		}

		@Override public String getSelectByKeySql(){
			return "SELECT * FROM `room_check_id_pool` WHERE `id`=? ORDER BY `id` DESC";
		}
		@Override public String getSelectCountSql(){
			return "SELECT count(*) FROM `room_check_id_pool`";
		}
		@Override public String getFormatSelectSql(){
			return "SELECT %s FROM `room_check_id_pool` ORDER BY `id` DESC";
		}
		@Override public String getFormatSelectPrefixSql(){
			return "SELECT %s FROM `room_check_id_pool` ";
		}
		@Override public String getSelectPrefixSql(){
			return "SELECT * FROM `room_check_id_pool` ";
		}
		@Override public String getOrderByIdDescSql(){
			return " ORDER BY `id` DESC";
		}
		@Override public String getDbTableName(){
			return DB_TABLE_NAME;
		}

		@Override public RowMapper<RoomCheckIdPoolDO> getRowMapper(){
			return new RowMapper<RoomCheckIdPoolDO>() {
				@Override
				public RoomCheckIdPoolDO mapRow(ResultSet rs, int rowNum) throws SQLException {
					RoomCheckIdPoolDO o=new RoomCheckIdPoolDO();
					o.id = rs.getString("id");
					o.state = rs.getInt("state");
					return o;
				}
			};
		}

		@Override public <C extends RoomCheckIdPoolDO> RowMapper<C>  getRowMapper(final Class<C> cls){
			return new RowMapper<C>() {
				@Override
				public C mapRow(ResultSet rs, int rowNum) throws SQLException {
					C o;
					try{
						o = cls.newInstance();
						o.setId(rs.getString("id"));
						o.setState(rs.getInt("state"));
                        return o;
					} catch (InstantiationException | IllegalAccessException e) {
						throw new SQLException("必须实现默认构造函数",e);
					}
				}
			};
		}
	}
}
