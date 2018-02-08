package game.boss.model;

import game.boss.dao.entity.RoomDO;
import game.boss.dao.entity.UserDO;
import game.scene.msg.CheckJoinRoomMsg;
import game.scene.msg.UserInfo;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author zuoge85@gmail.com on 2016/12/25.
 */
public class Room {
    private RoomDO roomDO;
    private Map<Integer, UserDO> map = new HashMap<>();
    private boolean start;

    public Room(RoomDO roomDO) {
        this.roomDO = roomDO;
    }


    public void removeUser(int userId) {
        map.entrySet().removeIf(entry -> entry.getValue().getId() == userId);
    }

    public RoomDO getRoomDO() {
        return roomDO;
    }

    public void setRoomDO(RoomDO roomDO) {
        this.roomDO = roomDO;
    }

    public void addUser(int location, UserDO user) {
        this.map.put(location, user);
    }

    public Map<Integer, UserDO> getMap() {
        return map;
    }

    public CheckJoinRoomMsg toMsg(User user){
        final CheckJoinRoomMsg m = new CheckJoinRoomMsg();


        m.setUuid(roomDO.getUuid());
        m.setUserMax(roomDO.getUserMax());
        m.setCreateUserId(roomDO.getCreateUserId());
        m.setRoomCheckId(roomDO.getRoomCheckId());
        m.setRoomId(roomDO.getId());
        m.setUser0(roomDO.getUser0());
        m.setUser1(roomDO.getUser1());
        m.setUser2(roomDO.getUser2());
        m.setUser3(roomDO.getUser3());
        m.setScore0(roomDO.getScore0());
        m.setScore1(roomDO.getScore1());
        m.setScore2(roomDO.getScore2());
        m.setScore3(roomDO.getScore3());
        m.setJoinGatewayId(user.getGatewayId());
        m.setJoinSessionId(user.getSessionId());
        m.setJoinUserId(user.getUserId());

        m.setOptions(roomDO.getConfig().getOptions());
        map.entrySet().stream().map(e -> {
            UserDO userDO = e.getValue();
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userDO.getId());
            userInfo.setLocationIndex(e.getKey());
//            userInfo.setSessionId(sessionId);
//            userInfo.setGatewayId((short) user.getGatewayId());

            userInfo.setIp(userDO.getIp());
            userInfo.setLongitude(userDO.getLongitude());
            userInfo.setLatitude(userDO.getLatitude());

            userInfo.setUserName(userDO.getName());
            userInfo.setAvatar(userDO.getAvatar());
            userInfo.setSex(userDO.getSex());
            userInfo.setGold(userDO.getGold());
            return userInfo;
        }).collect(Collectors.toCollection(m::getUserInfos));
        return m;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomDO=" + roomDO +
                '}';
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isStart() {
        return start;
    }
}
