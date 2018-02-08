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

public class SettingDO extends EntityObject<SettingDO, SettingDO.Key>{

	/**id 永远是1*/
	private int id;
	/**广告（首页）*/
	private String notice;
	/**广播（跑马灯）*/
	private String radio;
	/**充值信息*/
	private String payInfo;
	/**用户协议*/
	private String agreement;
	/**用户默认房卡*/
	private int initGold;

	public static class Key implements KeyObject<SettingDO, SettingDO.Key>{
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
			return "Setting[id:"+ id+ "]";
		}
	}

	@Override
	public Key getKey() {
		return new Key() {

			public int getId() {
				return id;
			}

			public void setId(int id) {
				SettingDO.this.id  = id;
				SettingDO.this.changeProperty("id",id);
			}

			@Override
			public String toString() {
				return "Setting[id:"+ id+ "]";
			}
		};
	}




	public SettingDO() {
    }

	public SettingDO(int id,String notice,String radio,String payInfo,String agreement,int initGold) {
		this.id = id;
		this.notice = notice;
		this.radio = radio;
		this.payInfo = payInfo;
		this.agreement = agreement;
		this.initGold = initGold;
	}


	public SettingDO newInstance(){
		return new SettingDO();
	}

    public void setKey(Object key){
        this.id = ((Number)key).intValue();
    }

	/**
	 * id 永远是1
	 **/
	public int getId() {
		return id;
	}

	/**
	 * id 永远是1
	 **/
	public void setId(int id) {
		this.id = id;
		changeProperty("id",id);
	}

	/**
	 * 广告（首页）
	 **/
	public String getNotice() {
		return notice;
	}

	/**
	 * 广告（首页）
	 **/
	public void setNotice(String notice) {
		this.notice = notice;
		changeProperty("notice",notice);
	}

	/**
	 * 广播（跑马灯）
	 **/
	public String getRadio() {
		return radio;
	}

	/**
	 * 广播（跑马灯）
	 **/
	public void setRadio(String radio) {
		this.radio = radio;
		changeProperty("radio",radio);
	}

	/**
	 * 充值信息
	 **/
	public String getPayInfo() {
		return payInfo;
	}

	/**
	 * 充值信息
	 **/
	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
		changeProperty("payInfo",payInfo);
	}

	/**
	 * 用户协议
	 **/
	public String getAgreement() {
		return agreement;
	}

	/**
	 * 用户协议
	 **/
	public void setAgreement(String agreement) {
		this.agreement = agreement;
		changeProperty("agreement",agreement);
	}

	/**
	 * 用户默认房卡
	 **/
	public int getInitGold() {
		return initGold;
	}

	/**
	 * 用户默认房卡
	 **/
	public void setInitGold(int initGold) {
		this.initGold = initGold;
		changeProperty("initGold",initGold);
	}

    @Override
    public Object get(String dbName){
        switch(dbName){
        case "id":
            return id;
        case "notice":
            return notice;
        case "radio":
            return radio;
        case "payInfo":
            return payInfo;
        case "agreement":
            return agreement;
        case "initGold":
            return initGold;
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
		case "notice":
			notice = (String)obj;
			return true;
		case "radio":
			radio = (String)obj;
			return true;
		case "payInfo":
			payInfo = (String)obj;
			return true;
		case "agreement":
			agreement = (String)obj;
			return true;
		case "initGold":
			initGold = (int)obj;
			return true;
		default :
			return false;
		}
	}

	@Override
	public String toString() {
		return "Setting[id:"+ id+",notice:"+ (notice == null ?"null":notice.substring(0, Math.min(notice.length(), 64)))+",radio:"+ (radio == null ?"null":radio.substring(0, Math.min(radio.length(), 64)))+",payInfo:"+ (payInfo == null ?"null":payInfo.substring(0, Math.min(payInfo.length(), 64)))+",agreement:"+ (agreement == null ?"null":agreement.substring(0, Math.min(agreement.length(), 64)))+",initGold:"+ initGold+ "]";
	}

	@Override
	@JsonIgnore
    @Transient
	public Table getTableInfo() {
		return TABLE_INFO;
	}


	public static final Table TABLE_INFO= new Table();

	public static final class Table extends TableInfo<SettingDO ,Key>{
		public static final String DB_TABLE_NAME = "setting";
		private Map<String, UniqueInfo> uniqueMap;

		public static final String ID = "id";
		public static final String NOTICE = "notice";
		public static final String RADIO = "radio";
		public static final String PAYINFO = "payInfo";
		public static final String AGREEMENT = "agreement";
		public static final String INITGOLD = "initGold";

        public static final String UNIQUE_PRIMARY = "PRIMARY";

		private Table(){
		    uniqueMap = new HashMap<>();
			super.initProperty("id", "id", int.class, new TypeReference<Integer>() {});
			super.initProperty("notice", "notice", String.class, new TypeReference<String>() {});
			super.initProperty("radio", "radio", String.class, new TypeReference<String>() {});
			super.initProperty("payInfo", "payInfo", String.class, new TypeReference<String>() {});
			super.initProperty("agreement", "agreement", String.class, new TypeReference<String>() {});
			super.initProperty("initGold", "initGold", int.class, new TypeReference<Integer>() {});
		}

		@Override public String getKeyUpdatePartialPrefixSql(){
			return "UPDATE `setting` SET ";
		}

		@Override public String getKeyWhereByKeySql(){
			return " WHERE `id`=?";
		}

		@Override public String getKeyDeleteSql(){
			return "DELETE FROM `setting` WHERE `id`=?";
		}

		@Override public Map<String, UniqueInfo> getUniques(){
            return uniqueMap;
		}

		@Override
        public UniqueInfo getUniques(String uniqueName){
            return uniqueMap.get(uniqueName);
        }

		@Override
		public int setPreparedStatement(SettingDO t, PreparedStatement ps, int i, boolean isSetUnique) throws SQLException {
			Object idPtr;
			idPtr = t.getId();

			if(isSetUnique){
				ps.setObject(i++, idPtr);
			}
			Object noticePtr;
			noticePtr = t.getNotice();

			ps.setObject(i++, noticePtr);
			Object radioPtr;
			radioPtr = t.getRadio();

			ps.setObject(i++, radioPtr);
			Object payInfoPtr;
			payInfoPtr = t.getPayInfo();

			ps.setObject(i++, payInfoPtr);
			Object agreementPtr;
			agreementPtr = t.getAgreement();

			ps.setObject(i++, agreementPtr);
			Object initGoldPtr;
			initGoldPtr = t.getInitGold();

			ps.setObject(i++, initGoldPtr);
			return i;
		}

		@Override
        public int setAllPreparedStatement(SettingDO t, PreparedStatement ps, int i) throws SQLException {
			Object idPtr;
				idPtr = t.getId();

			ps.setObject(i++,  idPtr);
			Object noticePtr;
				noticePtr = t.getNotice();

			ps.setObject(i++,  noticePtr);
			Object radioPtr;
				radioPtr = t.getRadio();

			ps.setObject(i++,  radioPtr);
			Object payInfoPtr;
				payInfoPtr = t.getPayInfo();

			ps.setObject(i++,  payInfoPtr);
			Object agreementPtr;
				agreementPtr = t.getAgreement();

			ps.setObject(i++,  agreementPtr);
			Object initGoldPtr;
				initGoldPtr = t.getInitGold();

			ps.setObject(i++,  initGoldPtr);
        	return i;
        }

		@Override
		public int setPreparedStatementKeys(SettingDO t, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, t.getId());
			return i;
		}

		@Override
		public int setKeyPreparedStatement(Key k, PreparedStatement ps, int i) throws SQLException {
			ps.setObject(i++, k.getId());
			return i;
		}

		@Override public String getInsertSql(){
			return "INSERT INTO `setting` (`id`,`notice`,`radio`,`payInfo`,`agreement`,`initGold`) VALUES (?,?,?,?,?,?)";
		}

		@Override public String getReplaceSql(){
        	return "REPLACE INTO `setting` (`id`,`notice`,`radio`,`payInfo`,`agreement`,`initGold`) VALUES (?,?,?,?,?,?)";
        }

		@Override public String getFastInsertPrefixSql(){
			return "INSERT INTO `setting` (`id`,`notice`,`radio`,`payInfo`,`agreement`,`initGold`) VALUES ";
		}
		@Override public String getFastInsertValueItemsSql(){
			return " (?,?,?,?,?,?) ";
		}
		@Override public String getUpdateSql(){
			return "UPDATE `setting` SET `id`=?,`notice`=?,`radio`=?,`payInfo`=?,`agreement`=?,`initGold`=? WHERE `id`=?";
		}

		@Override public String getSelectByKeySql(){
			return "SELECT * FROM `setting` WHERE `id`=? ORDER BY `id` DESC";
		}
		@Override public String getSelectCountSql(){
			return "SELECT count(*) FROM `setting`";
		}
		@Override public String getFormatSelectSql(){
			return "SELECT %s FROM `setting` ORDER BY `id` DESC";
		}
		@Override public String getFormatSelectPrefixSql(){
			return "SELECT %s FROM `setting` ";
		}
		@Override public String getSelectPrefixSql(){
			return "SELECT * FROM `setting` ";
		}
		@Override public String getOrderByIdDescSql(){
			return " ORDER BY `id` DESC";
		}
		@Override public String getDbTableName(){
			return DB_TABLE_NAME;
		}

		@Override public RowMapper<SettingDO> getRowMapper(){
			return new RowMapper<SettingDO>() {
				@Override
				public SettingDO mapRow(ResultSet rs, int rowNum) throws SQLException {
					SettingDO o=new SettingDO();
					o.id = rs.getInt("id");
					o.notice = rs.getString("notice");
					o.radio = rs.getString("radio");
					o.payInfo = rs.getString("payInfo");
					o.agreement = rs.getString("agreement");
					o.initGold = rs.getInt("initGold");
					return o;
				}
			};
		}

		@Override public <C extends SettingDO> RowMapper<C>  getRowMapper(final Class<C> cls){
			return new RowMapper<C>() {
				@Override
				public C mapRow(ResultSet rs, int rowNum) throws SQLException {
					C o;
					try{
						o = cls.newInstance();
						o.setId(rs.getInt("id"));
						o.setNotice(rs.getString("notice"));
						o.setRadio(rs.getString("radio"));
						o.setPayInfo(rs.getString("payInfo"));
						o.setAgreement(rs.getString("agreement"));
						o.setInitGold(rs.getInt("initGold"));
                        return o;
					} catch (InstantiationException | IllegalAccessException e) {
						throw new SQLException("必须实现默认构造函数",e);
					}
				}
			};
		}
	}
}
