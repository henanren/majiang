package majiang.client.portal.client.admin.model;

import java.util.Date;

import org.forkjoin.apikit.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

/**
 * @author  zuoge85 on 15/6/17.
 */
public class UserModel implements ApiMessage {

	private int id;

	/**
	 * 昵称
	 */
	private String name;

	private String openId;

	/**
	 * 用户唯一uuid
	 */
	private String uuid;

	/**
	 * 用户头像地址
	 */
	private String avatar;

	/**
	 * 0:女生,1:男生,2:未知
	 */
	private int sex;

	private Date createDate;

	private Date updateDate;

	private int version;

	private int gold;

	private int historyGold;

	private String mobile;

	private String loginToken;

	private int level;

	public UserModel() {
	}

	public UserModel(int id, String name, String openId, String uuid, String avatar, int sex, Date createDate,
			Date updateDate, int version, int gold, int historyGold, String mobile, String loginToken, int level) {
		this.id = id;
		this.name = name;
		this.openId = openId;
		this.uuid = uuid;
		this.avatar = avatar;
		this.sex = sex;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.version = version;
		this.gold = gold;
		this.historyGold = historyGold;
		this.mobile = mobile;
		this.loginToken = loginToken;
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 昵称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 昵称
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * 用户唯一uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * 用户唯一uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 用户头像地址
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * 用户头像地址
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * 0:女生,1:男生,2:未知
	 */
	public int getSex() {
		return sex;
	}

	/**
	 * 0:女生,1:男生,2:未知
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getHistoryGold() {
		return historyGold;
	}

	public void setHistoryGold(int historyGold) {
		this.historyGold = historyGold;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public List<Entry<String, Object>> encode(String parent, List<Entry<String, Object>> $list) {

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "id", id));

		if (name != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "name", name));
		}

		if (openId != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "openId", openId));
		}

		if (uuid != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "uuid", uuid));
		}

		if (avatar != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "avatar", avatar));
		}

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "sex", sex));

		if (createDate != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "createDate", createDate));
		}

		if (updateDate != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "updateDate", updateDate));
		}

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "version", version));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "gold", gold));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "historyGold", historyGold));

		if (mobile != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "mobile", mobile));
		}

		if (loginToken != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "loginToken", loginToken));
		}

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "level", level));

		return $list;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ",name=" + name + ",openId=" + openId + ",uuid=" + uuid + ",avatar=" + avatar
				+ ",sex=" + sex + ",createDate=" + createDate + ",updateDate=" + updateDate + ",version=" + version
				+ ",gold=" + gold + ",historyGold=" + historyGold + ",mobile=" + mobile + ",loginToken=" + loginToken
				+ ",level=" + level + ", ]";
	}
}