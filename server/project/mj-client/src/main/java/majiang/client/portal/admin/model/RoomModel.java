package majiang.client.portal.admin.model;

import org.forkjoin.apikit.core.Message;

import java.util.Date;

/**
 * @author zuoge85@gmail.com on 2017/1/8.
 */
@Message
public class RoomModel {

    /**牌局id*/
    private int id;
    /**创建用户id*/
    private int createUserId;
    /**用户数*/
    private int userMax;
    /**牌局uuid*/
    private String uuid;
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
    /**房间的check-id,进入id,可以重复,但是不允许同时活跃状态的id 相同*/
    private String roomCheckId;
    private java.util.Date startDate;
    private java.util.Date endDate;
    private boolean start;
    /**正常结束*/
    private boolean end;
    private int version;
    private int sceneId;
    private int chapterNums;
    /**配置*/
    private String config;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public int getUserMax() {
        return userMax;
    }

    public void setUserMax(int userMax) {
        this.userMax = userMax;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getUser0() {
        return user0;
    }

    public void setUser0(int user0) {
        this.user0 = user0;
    }

    public int getUser1() {
        return user1;
    }

    public void setUser1(int user1) {
        this.user1 = user1;
    }

    public int getUser2() {
        return user2;
    }

    public void setUser2(int user2) {
        this.user2 = user2;
    }

    public int getUser3() {
        return user3;
    }

    public void setUser3(int user3) {
        this.user3 = user3;
    }

    public String getUserName0() {
        return userName0;
    }

    public void setUserName0(String userName0) {
        this.userName0 = userName0;
    }

    public String getUserName1() {
        return userName1;
    }

    public void setUserName1(String userName1) {
        this.userName1 = userName1;
    }

    public String getUserName2() {
        return userName2;
    }

    public void setUserName2(String userName2) {
        this.userName2 = userName2;
    }

    public String getUserName3() {
        return userName3;
    }

    public void setUserName3(String userName3) {
        this.userName3 = userName3;
    }

    public int getScore0() {
        return score0;
    }

    public void setScore0(int score0) {
        this.score0 = score0;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public int getScore3() {
        return score3;
    }

    public void setScore3(int score3) {
        this.score3 = score3;
    }

    public String getRoomCheckId() {
        return roomCheckId;
    }

    public void setRoomCheckId(String roomCheckId) {
        this.roomCheckId = roomCheckId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public int getChapterNums() {
        return chapterNums;
    }

    public void setChapterNums(int chapterNums) {
        this.chapterNums = chapterNums;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "RoomModel{" +
                "id=" + id +
                ", createUserId=" + createUserId +
                ", userMax=" + userMax +
                ", uuid='" + uuid + '\'' +
                ", user0=" + user0 +
                ", user1=" + user1 +
                ", user2=" + user2 +
                ", user3=" + user3 +
                ", userName0='" + userName0 + '\'' +
                ", userName1='" + userName1 + '\'' +
                ", userName2='" + userName2 + '\'' +
                ", userName3='" + userName3 + '\'' +
                ", score0=" + score0 +
                ", score1=" + score1 +
                ", score2=" + score2 +
                ", score3=" + score3 +
                ", roomCheckId='" + roomCheckId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", start=" + start +
                ", end=" + end +
                ", version=" + version +
                ", sceneId=" + sceneId +
                ", chapterNums=" + chapterNums +
                ", config='" + config + '\'' +
                '}';
    }
}
