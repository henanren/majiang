package majiang.client.portal.admin.model;


import org.forkjoin.apikit.core.Message;

import java.util.Date;

/**
 * @author zuoge85 on 15/6/17.
 */
@Message
public class UserModel {
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
    private Date createDate;
    private Date updateDate;
    private int version;
    private int gold;
    private int historyGold;
    private String mobile;
    private String loginToken;
    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSex() {
        return sex;
    }

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

    public int getHistoryGold() {
        return historyGold;
    }

    public void setHistoryGold(int historyGold) {
        this.historyGold = historyGold;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", openId='" + openId + '\'' +
                ", uuid='" + uuid + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex=" + sex +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", version=" + version +
                ", gold=" + gold +
                ", historyGold=" + historyGold +
                ", mobile='" + mobile + '\'' +
                ", loginToken='" + loginToken + '\'' +
                ", level=" + level +
                '}';
    }
}
