package game.scene.room;

import com.github.davidmoten.grumpy.core.Position;
import game.scene.room.majiang.MajiangChapter;
import mj.data.UserPaiInfo;
import mj.net.message.game.GameRoomInfo;
import mj.net.message.game.GameUserInfo;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Method;

/**
 * @author zuoge85@gmail.com on 16/10/6.
 */
public class RoomInfo {
    private short sceneId;
    /**
     * 牌局id
     */
    private int roomId;
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
     * 房间的check-id,进入id,可以重复,但是不允许同时活跃状态的id 相同
     */
    private String roomCheckId;

    private boolean start;
    private boolean chapterStart;


    private final SceneUser[] users = new SceneUser[4];

    protected final MajiangChapter chapter;

    public RoomInfo(Room room,String rulesName) {
        chapter = new MajiangChapter(room, rulesName);
    }

    public GameRoomInfo toMessage(int myLocationIndex) {
        GameRoomInfo g = new GameRoomInfo();
        g.setStart(start);

        g.setRoomCheckId(roomCheckId);
        g.setCreateUserId(createUserId);
        g.setLeftChapterNums(chapter.getLeftChapterNums());
        for (SceneUser u : users) {
            if (u != null) {
                GameUserInfo sceneUser = u.toMessage();
                excuteDistanceKm(sceneUser, u);
                g.addSceneUser(sceneUser);
            }
        }
        if (isStart()) {
//            if (chapter.isStart()) {
                g.setChapter(chapter.toMessage(myLocationIndex));
//            }
        }
        return g;
    }


    public void excuteDistanceKm(GameUserInfo msg, SceneUser curUser) {
        if (curUser.getLongitude() == 0 || curUser.getLatitude() == 0) {
            return;
        }
        for (int i = 0; i < users.length; i++) {
            SceneUser u = users[i];
            if (u != null && u.getLocationIndex() != msg.getLocationIndex()) {
                String distance = "";
                if (u.getLongitude() != 0 && u.getLatitude() != 0) {
                    distance = String.format(
                            "%.3f公里",
                            distanceKm(curUser.getLatitude(), curUser.getLongitude(), u.getLatitude(), u.getLongitude())
                    );
                } else {
                    distance = "位置未知";
                }
                Method writeMethod = BeanUtils.getPropertyDescriptor(GameUserInfo.class, "user" + i + "Distance").getWriteMethod();

                try {
                    writeMethod.invoke(msg, distance);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * @return 返回距离，单位千米！
     */
    public static double distanceKm(double latitude0, double longitude0, double latitude1, double longitude1) {
        return new Position(latitude0, longitude0).getDistanceToKm(new Position(latitude1, longitude1));
    }

    public void updateUserInfo(SceneUser sceneUser) {
        SceneUser oldUser = users[sceneUser.getLocationIndex()];
        if (oldUser == null) {
            users[sceneUser.getLocationIndex()] = sceneUser;
        } else {
            oldUser.setAvatar(sceneUser.getAvatar());
            oldUser.setLocationIndex(sceneUser.getLocationIndex());
            oldUser.setSex(sceneUser.getSex());
            oldUser.setUserId(sceneUser.getUserId());
        }
        chapter.updateUser(sceneUser);
    }

    public SceneUser getUserInfo(int locationIndex) {
        return users[locationIndex];
    }


    public boolean removeUserInfo(int userId) {
        for (int i = 0; i < users.length; i++) {
            SceneUser u = users[i];
            if (u != null && u.getUserId() == userId) {
                users[i] = null;
                return true;
            }
        }
        return false;
    }

    public SceneUser[] getUsers() {
        return users;
    }


    public void changeScore(UserPaiInfo[] userPaiInfos) {
        for (int i = 0; i < users.length; i++) {
            SceneUser user = users[i];
            UserPaiInfo userPaiInfo = userPaiInfos[i];
            user.addScore(userPaiInfo.getScore());
        }
//        if (fangPaoIndex == -1) {
//            for (int i = 0; i < users.length; i++) {
//                if (i != huPaiLocationIndex) {
//                    users[i].removeScore(scoreNums);
//                }
//            }
//            users[huPaiLocationIndex].addScore(scoreNums * 3);
//        } else {
//            users[fangPaoIndex].removeScore(scoreNums);
//            users[huPaiLocationIndex].addScore(scoreNums);
//        }
    }

    public short getSceneId() {
        return sceneId;
    }

    public void setSceneId(short sceneId) {
        this.sceneId = sceneId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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


    public String getRoomCheckId() {
        return roomCheckId;
    }

    public void setRoomCheckId(String roomCheckId) {
        this.roomCheckId = roomCheckId;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public MajiangChapter getChapter() {
        return chapter;
    }

    @Override
    public String toString() {
        return "RoomInfo{" +
                "start=" + start +
                ", sceneId=" + sceneId +
                ", roomId=" + roomId +
                ", createUserId=" + createUserId +
                ", userMax=" + userMax +
                ", uuid='" + uuid + '\'' +
                ", roomCheckId='" + roomCheckId + '\'' +
                '}';
    }

    public boolean isFull() {
        for (int i = 0; i < users.length; i++) {
            SceneUser u = users[i];
            if (u == null || !u.isJoinGame()) {
                return false;
            }
        }
        return true;
    }

    public SceneUser getUserInfoByUserId(int userId) {
        for (int i = 0; i < users.length; i++) {
            SceneUser u = users[i];
            if (u != null && u.getUserId() == userId) {
                return u;
            }
        }
        return null;
    }

    public boolean isChapterStart() {
        return chapterStart;
    }

    public void setChapterStart(boolean chapterStart) {
        this.chapterStart = chapterStart;
    }
}
