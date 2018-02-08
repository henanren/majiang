package majiang.client.model;

/**
 * @author zuoge85@gmail.com on 2016/12/8.
 */
public class LoginTokenInfo {
    private long time;
    private String token;
    private String userId;

    public LoginTokenInfo(long time, String token, String userId) {
        this.time = time;
        this.token = token;
        this.userId = userId;
    }

    public LoginTokenInfo() {
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LoginTokenInfo{" +
                "time=" + time +
                ", token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
