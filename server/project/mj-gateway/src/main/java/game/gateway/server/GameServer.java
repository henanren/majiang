package game.gateway.server;

import com.isnowfox.core.net.NetPacketHandler;
import com.isnowfox.core.net.SocketServer;

import java.util.concurrent.ExecutionException;

/**
 * @author zuoge85@gmail.com on 16/9/27.
 */
public class GameServer {
    private static final String PATH = "/g";
    private int port;
    private NetPacketHandler<?> handler;
    private int bossThreadNums;
    private int workerThreadNums;
    private int zipSize;
    private int encryptValue;
    private boolean isWebSocket = true;

    private SocketServer socketServer;

    public GameServer() {
    }

    public void init() throws Exception {
        if (isWebSocket) {
            socketServer = SocketServer.createWebSocketServer(
                    port, handler, bossThreadNums, workerThreadNums, PATH
            );
            socketServer.start();
        } else {
            socketServer = SocketServer.createCrcEncryptServer(
                    port, handler, bossThreadNums, workerThreadNums, zipSize, encryptValue
            );
            socketServer.start();
        }
    }

    public void close() throws ExecutionException, InterruptedException {
        socketServer.close();
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setHandler(NetPacketHandler<?> handler) {
        this.handler = handler;
    }

    public void setBossThreadNums(int bossThreadNums) {
        this.bossThreadNums = bossThreadNums;
    }

    public void setWorkerThreadNums(int workerThreadNums) {
        this.workerThreadNums = workerThreadNums;
    }

    public void setZipSize(int zipSize) {
        this.zipSize = zipSize;
    }

    public void setEncryptValue(int encryptValue) {
        this.encryptValue = encryptValue;
    }

    public void setWebSocket(boolean webSocket) {
        isWebSocket = webSocket;
    }

    @Override
    public String toString() {
        return "GameServer{" +
                "port=" + port +
                ", handler=" + handler +
                ", bossThreadNums=" + bossThreadNums +
                ", workerThreadNums=" + workerThreadNums +
                ", zipSize=" + zipSize +
                ", encryptValue=" + encryptValue +
                '}';
    }
}
