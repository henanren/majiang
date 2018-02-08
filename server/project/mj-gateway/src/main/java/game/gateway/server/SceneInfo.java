package game.gateway.server;

/**
 * @author zuoge85@gmail.com on 16/10/7.
 */
public class SceneInfo {
    private short sessionId;
    private short sceneId;

    private String sceneAddress;
    private int scenePort;

    public short getSessionId() {
        return sessionId;
    }

    public void setSessionId(short sessionId) {
        this.sessionId = sessionId;
    }

    public short getSceneId() {
        return sceneId;
    }

    public void setSceneId(short sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneAddress() {
        return sceneAddress;
    }

    public void setSceneAddress(String sceneAddress) {
        this.sceneAddress = sceneAddress;
    }

    public int getScenePort() {
        return scenePort;
    }

    public void setScenePort(int scenePort) {
        this.scenePort = scenePort;
    }

    @Override
    public String toString() {
        return "SceneUserInfo{" +
                "sessionId=" + sessionId +
                ", sceneId=" + sceneId +
                ", sceneAddress='" + sceneAddress + '\'' +
                ", scenePort=" + scenePort +
                '}';
    }
}
