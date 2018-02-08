package majiang.client.portal.client.admin.model;

import java.util.Date;

import org.forkjoin.apikit.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

/**
 * @author  zuoge85@gmail.com on 2017/1/8.
 */
public class RoomModel implements ApiMessage {

	/**
	 * 牌局id
	 */
	private int id;

	/**
	 * 创建用户id
	 */
	private int createUserId;

	/**
	 * 用户数
	 */
	private int userMax;

	/**
	 * 牌局uuid
	 */
	private String uuid;

	/**
	 * 用户0
	 */
	private int user0;

	/**
	 * 用户1
	 */
	private int user1;

	/**
	 * 用户2
	 */
	private int user2;

	/**
	 * 用户3
	 */
	private int user3;

	/**
	 * 用户0
	 */
	private String userName0;

	/**
	 * 用户1
	 */
	private String userName1;

	/**
	 * 用户2
	 */
	private String userName2;

	/**
	 * 用户3
	 */
	private String userName3;

	/**
	 * 积分0
	 */
	private int score0;

	/**
	 * 积分1
	 */
	private int score1;

	/**
	 * 积分2
	 */
	private int score2;

	/**
	 * 积分3
	 */
	private int score3;

	/**
	 * 房间的check-id,进入id,可以重复,但是不允许同时活跃状态的id 相同
	 */
	private String roomCheckId;

	private Date startDate;

	private Date endDate;

	private boolean start;

	/**
	 * 正常结束
	 */
	private boolean end;

	private int version;

	private int sceneId;

	private int chapterNums;

	/**
	 * 配置
	 */
	private String config;

	public RoomModel() {
	}

	public RoomModel(int id, int createUserId, int userMax, String uuid, int user0, int user1, int user2, int user3,
			String userName0, String userName1, String userName2, String userName3, int score0, int score1, int score2,
			int score3, String roomCheckId, Date startDate, Date endDate, boolean start, boolean end, int version,
			int sceneId, int chapterNums, String config) {
		this.id = id;
		this.createUserId = createUserId;
		this.userMax = userMax;
		this.uuid = uuid;
		this.user0 = user0;
		this.user1 = user1;
		this.user2 = user2;
		this.user3 = user3;
		this.userName0 = userName0;
		this.userName1 = userName1;
		this.userName2 = userName2;
		this.userName3 = userName3;
		this.score0 = score0;
		this.score1 = score1;
		this.score2 = score2;
		this.score3 = score3;
		this.roomCheckId = roomCheckId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.start = start;
		this.end = end;
		this.version = version;
		this.sceneId = sceneId;
		this.chapterNums = chapterNums;
		this.config = config;
	}

	/**
	 * 牌局id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 牌局id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 创建用户id
	 */
	public int getCreateUserId() {
		return createUserId;
	}

	/**
	 * 创建用户id
	 */
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 用户数
	 */
	public int getUserMax() {
		return userMax;
	}

	/**
	 * 用户数
	 */
	public void setUserMax(int userMax) {
		this.userMax = userMax;
	}

	/**
	 * 牌局uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * 牌局uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 用户0
	 */
	public int getUser0() {
		return user0;
	}

	/**
	 * 用户0
	 */
	public void setUser0(int user0) {
		this.user0 = user0;
	}

	/**
	 * 用户1
	 */
	public int getUser1() {
		return user1;
	}

	/**
	 * 用户1
	 */
	public void setUser1(int user1) {
		this.user1 = user1;
	}

	/**
	 * 用户2
	 */
	public int getUser2() {
		return user2;
	}

	/**
	 * 用户2
	 */
	public void setUser2(int user2) {
		this.user2 = user2;
	}

	/**
	 * 用户3
	 */
	public int getUser3() {
		return user3;
	}

	/**
	 * 用户3
	 */
	public void setUser3(int user3) {
		this.user3 = user3;
	}

	/**
	 * 用户0
	 */
	public String getUserName0() {
		return userName0;
	}

	/**
	 * 用户0
	 */
	public void setUserName0(String userName0) {
		this.userName0 = userName0;
	}

	/**
	 * 用户1
	 */
	public String getUserName1() {
		return userName1;
	}

	/**
	 * 用户1
	 */
	public void setUserName1(String userName1) {
		this.userName1 = userName1;
	}

	/**
	 * 用户2
	 */
	public String getUserName2() {
		return userName2;
	}

	/**
	 * 用户2
	 */
	public void setUserName2(String userName2) {
		this.userName2 = userName2;
	}

	/**
	 * 用户3
	 */
	public String getUserName3() {
		return userName3;
	}

	/**
	 * 用户3
	 */
	public void setUserName3(String userName3) {
		this.userName3 = userName3;
	}

	/**
	 * 积分0
	 */
	public int getScore0() {
		return score0;
	}

	/**
	 * 积分0
	 */
	public void setScore0(int score0) {
		this.score0 = score0;
	}

	/**
	 * 积分1
	 */
	public int getScore1() {
		return score1;
	}

	/**
	 * 积分1
	 */
	public void setScore1(int score1) {
		this.score1 = score1;
	}

	/**
	 * 积分2
	 */
	public int getScore2() {
		return score2;
	}

	/**
	 * 积分2
	 */
	public void setScore2(int score2) {
		this.score2 = score2;
	}

	/**
	 * 积分3
	 */
	public int getScore3() {
		return score3;
	}

	/**
	 * 积分3
	 */
	public void setScore3(int score3) {
		this.score3 = score3;
	}

	/**
	 * 房间的check-id,进入id,可以重复,但是不允许同时活跃状态的id 相同
	 */
	public String getRoomCheckId() {
		return roomCheckId;
	}

	/**
	 * 房间的check-id,进入id,可以重复,但是不允许同时活跃状态的id 相同
	 */
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

	public boolean getStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	/**
	 * 正常结束
	 */
	public boolean getEnd() {
		return end;
	}

	/**
	 * 正常结束
	 */
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

	/**
	 * 配置
	 */
	public String getConfig() {
		return config;
	}

	/**
	 * 配置
	 */
	public void setConfig(String config) {
		this.config = config;
	}

	@Override
	public List<Entry<String, Object>> encode(String parent, List<Entry<String, Object>> $list) {

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "id", id));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "createUserId", createUserId));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "userMax", userMax));

		if (uuid != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "uuid", uuid));
		}

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "user0", user0));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "user1", user1));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "user2", user2));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "user3", user3));

		if (userName0 != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "userName0", userName0));
		}

		if (userName1 != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "userName1", userName1));
		}

		if (userName2 != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "userName2", userName2));
		}

		if (userName3 != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "userName3", userName3));
		}

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "score0", score0));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "score1", score1));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "score2", score2));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "score3", score3));

		if (roomCheckId != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "roomCheckId", roomCheckId));
		}

		if (startDate != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "startDate", startDate));
		}

		if (endDate != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "endDate", endDate));
		}

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "start", start));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "end", end));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "version", version));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "sceneId", sceneId));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "chapterNums", chapterNums));

		if (config != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "config", config));
		}

		return $list;
	}

	@Override
	public String toString() {
		return "RoomModel [id=" + id + ",createUserId=" + createUserId + ",userMax=" + userMax + ",uuid=" + uuid
				+ ",user0=" + user0 + ",user1=" + user1 + ",user2=" + user2 + ",user3=" + user3 + ",userName0="
				+ userName0 + ",userName1=" + userName1 + ",userName2=" + userName2 + ",userName3=" + userName3
				+ ",score0=" + score0 + ",score1=" + score1 + ",score2=" + score2 + ",score3=" + score3
				+ ",roomCheckId=" + roomCheckId + ",startDate=" + startDate + ",endDate=" + endDate + ",start=" + start
				+ ",end=" + end + ",version=" + version + ",sceneId=" + sceneId + ",chapterNums=" + chapterNums
				+ ",config=" + config + ", ]";
	}
}