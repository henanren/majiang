package game.scene.msg;

import com.isnowfox.game.proxy.message.AbstractSessionPxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author zuoge85@gmail.com on 2016/12/24.
 */
public class UserInfo extends AbstractSessionPxMsg {
    private int userId;
    private int locationIndex;
    private int sessionId;
    private int gatewayId;

    private String userName;
    private String avatar;
    /**
     * 0:女生,1:男生,2:未知
     */
    private int sex;
    private int gold;
    private String ip;
    /**
     * 经度
     */
    private double longitude;
    /**
     * 纬度
     */
    private double latitude;


    public UserInfo() {
        super(-1);
    }

    public void encode(ByteBuf out) throws Exception {
        out.writeInt(userId);
        out.writeInt(locationIndex);
        out.writeInt(sessionId);
        out.writeInt(gatewayId);


        writeString(out, userName);
        writeString(out, avatar);
        out.writeInt(sex);
        out.writeInt(gold);

        writeString(out, ip);

        out.writeDouble(longitude);
        out.writeDouble(latitude);
    }

    @Override
    public void decode(ByteBuf in, ChannelHandlerContext channelHandlerContext) throws Exception {
        userId = in.readInt();
        locationIndex = in.readInt();
        sessionId = in.readInt();
        gatewayId = in.readInt();

        userName = readString(in);
        avatar = readString(in);
        sex = in.readInt();
        gold = in.readInt();

        ip = readString(in);

        longitude = in.readDouble();
        latitude = in.readDouble();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLocationIndex() {
        return locationIndex;
    }

    public void setLocationIndex(int locationIndex) {
        this.locationIndex = locationIndex;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(int gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", locationIndex=" + locationIndex +
                ", sessionId=" + sessionId +
                ", gatewayId=" + gatewayId +
                ", userName='" + userName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex=" + sex +
                ", gold=" + gold +
                ", ip='" + ip + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
