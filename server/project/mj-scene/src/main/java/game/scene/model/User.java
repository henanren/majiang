package game.scene.model;

import com.isnowfox.core.net.message.Message;

/**
 * @author zuoge85@gmail.com on 16/9/26.
 */
public abstract class User {
    private short sessionId;
    private int gatewayId;
    private String ip;
    private int userId;

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


    @Override
    public String toString() {
        return "User{" +
                "sessionId=" + sessionId +
                ", gatewayId=" + gatewayId +
                ", ip='" + ip + '\'' +
                ", userId=" + userId +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public abstract void send(Message msg);

    public abstract void close();

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }
}
