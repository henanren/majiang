package game.boss.model;

import com.isnowfox.core.net.message.Message;
import com.isnowfox.game.proxy.message.PxMsg;
import game.boss.dao.entity.UserDO;
import game.boss.dao.entity.UserLoginLogDO;
import game.type.NoticeType;
import mj.net.message.login.Notice;

import java.util.function.Consumer;

/**
 * @author zuoge85@gmail.com on 16/9/26.
 */
public abstract class User {
    private short sessionId;
    private int gatewayId;
    private UserDO userDO;
    private UserLoginLogDO loginLog;
    private String ip;
    private boolean joinHomeGatewaySuccess;
    private boolean joinHomeSceneSuccess;
    private Consumer<Boolean> joinRoomCallback;


    public User() {
    }

    public short getSessionId() {
        return sessionId;
    }

    public void setSessionId(short sessionId) {
        this.sessionId = sessionId;
    }

    public void setGatewayId(int gatewayId) {
        this.gatewayId = gatewayId;
    }

    public int getGatewayId() {
        return gatewayId;
    }

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    public UserLoginLogDO getLoginLog() {
        return loginLog;
    }

    public void setLoginLog(UserLoginLogDO loginLog) {
        this.loginLog = loginLog;
    }


    public int getUserId() {
        return userDO == null ? -1 : userDO.getId();
    }

    public abstract void send(Message msg);

    public abstract void sendToGateway(PxMsg msg);

    public abstract void close();


    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }


    public void noticeError(String key) {
        noticeError(key, new String[0]);
    }

    public void noticeError(String key, String[] args) {
        notice(key, args, NoticeType.ERROR, false);
    }

    public void noticeMain(String key) {
        noticeMain(key, new String[0]);
    }

    public void noticeMain(String key, String[] args) {
        notice(key, args, NoticeType.MAIN, false);
    }

    public void notice(String key, String[] args, NoticeType type) {
        notice(key, args, type, false);
    }

    public void notice(String key, String[] args, NoticeType type, boolean reboot) {
        send(new Notice(key, args, type.ordinal(), reboot));
    }

    public boolean isJoinHomeGatewaySuccess() {
        return joinHomeGatewaySuccess;
    }

    public void setJoinHomeGatewaySuccess(boolean joinHomeGatewaySuccess) {
        this.joinHomeGatewaySuccess = joinHomeGatewaySuccess;
    }

    public boolean isJoinHomeSceneSuccess() {
        return joinHomeSceneSuccess;
    }

    public void setJoinHomeSceneSuccess(boolean joinHomeSceneSuccess) {
        this.joinHomeSceneSuccess = joinHomeSceneSuccess;
    }

    public void setJoinRoomCallback(Consumer<Boolean> joinRoomCallback) {
        this.joinRoomCallback = joinRoomCallback;
    }

    public Consumer<Boolean> getJoinRoomCallback() {
        return joinRoomCallback;
    }


    @Override
    public String toString() {
        return "User{" +
                "sessionId=" + sessionId +
                ", gatewayId=" + gatewayId +
                ", userDO=" + userDO +
                ", loginLog=" + loginLog +
                ", ip='" + ip + '\'' +
                ", joinHomeGatewaySuccess=" + joinHomeGatewaySuccess +
                ", joinHomeSceneSuccess=" + joinHomeSceneSuccess +
                ", joinRoomCallback=" + joinRoomCallback +
                '}';
    }
}
