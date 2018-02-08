package game.gateway;

import com.isnowfox.core.io.Input;
import com.isnowfox.core.io.MarkCompressInput;
import com.isnowfox.core.io.ProtocolException;
import com.isnowfox.core.net.Session;
import com.isnowfox.core.net.message.Packet;
import com.isnowfox.game.platform.User;
import com.isnowfox.game.proxy.message.*;
import com.isnowfox.util.collect.primitive.ShortList;
import game.boss.SceneUserInfo;
import game.boss.msg.*;
import game.gateway.server.BossClient;
import game.gateway.server.SceneClient;
import game.gateway.server.SceneClientManager;
import game.gateway.server.SceneInfo;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import mj.net.message.login.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zuoge85@gmail.com on 16/9/26.
 */
public class GatewayService {
    private final static Logger log = LoggerFactory.getLogger(GatewayService.class);

    @Autowired
    private SessionService sessionService;

    @Autowired
    private BossClient bossClient;

    @Autowired
    private SceneClientManager sceneClientManager;

    private short gatewayId;

    private boolean isWebSocket = true;

    private ConcurrentHashMap<Short, SceneInfo> map = new ConcurrentHashMap<>();

    private void broadcastToAll(Packet msg) {
        int m = sessionService.getMax();
        for (int i = 0; i < m; i++) {
            User u = sessionService.getUser(i);
            if (u != null) {
                msg.getBuf().retain();
                u.writeAndFlush(new BinaryWebSocketFrame(msg.getBuf()));
            }
        }
    }

    private void sendToUsers(ShortList list, Packet msg) {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            User u = sessionService.getUser(list.get(i));
            if (u != null) {
                msg.getBuf().retain();
                u.writeAndFlush(new BinaryWebSocketFrame(msg.getBuf()));
            }
        }
    }

    private void sendToUser(int sessionId, Packet msg) {
        User u = sessionService.getUser(sessionId);
        if (u != null) {
            msg.retain();
            u.writeAndFlush(new BinaryWebSocketFrame(msg.getBuf()));
        }
    }

    /**
     * 转发消息到 boss 服务器或者场景服务器
     */
    private void forwardFormUser(Packet msg) throws IOException, ProtocolException {
        User u = msg.<User>getSession().get();
        msg.getBuf().retain();
        SinglePxMsg sm = new SinglePxMsg(u.getId(), msg.getBuf());

        msg.getBuf().readerIndex(msg.getBufOffset());
        ByteBufInputStream bin = new ByteBufInputStream(msg.getBuf(), msg.getLength());
        Input in = MarkCompressInput.create(bin);
        int type = in.readInt();
//        int id = in.readInt();

        if (type == Login.TYPE) {
            bossClient.writeAndFlush(sm);
        } else {
            SceneInfo sceneInfo = map.get(u.getId());
            if (sceneInfo != null) {
                sceneClientManager.forwardMessage(sm, sceneInfo);
            } else {
                throw new RuntimeException("未分配场景");
            }
        }
    }


    private void close(int sessionId) {
        User u = sessionService.getUser(sessionId);
        if (u != null) {
            if (u.getChannel().isActive()) {
                u.getChannel().close();
            } else {
                sessionService.unReg(u);
            }
        } else {
            LogoutPxMsg msg = new LogoutPxMsg((short) sessionId);
            bossClient.writeAndFlush(msg);
        }
    }

    void toBoss(PxMsg msg) {
        bossClient.writeAndFlush(msg);
    }

    /**
     * 处理客户端过来的消息
     */
    public void handlerClient(Packet msg) throws IOException, ProtocolException {
        forwardFormUser(msg);
    }

    /**
     * 处理boss 过来的消息
     */
    public void handlerBoss(PxMsg msg) throws Exception {
        switch (msg.getType()) {
            case SinglePxMsg.ID: {
                SinglePxMsg sm = (SinglePxMsg) msg;

                Packet p = new Packet(sm.getBufLen(), (byte) 0, sm.getBuf(), 0);
                log.debug("准备转发[单个]消息{} --> {}", sm, p);
                sendToUser(sm.getSessionId(), p);
                break;
            }
            case RangePxMsg.ID: {
                RangePxMsg sm = (RangePxMsg) msg;

                Packet p = new Packet(sm.getBufLen(), (byte) 0, sm.getBuf(), 0);
                ShortList userList = sm.getUserList();
                log.debug("准备转发[多个用户:{}]消息{} --> {}", userList, sm, p);
                sendToUsers(userList, p);
                break;
            }
            case AllPxMsg.ID: {
                AllPxMsg sm = (AllPxMsg) msg;

                Packet p = new Packet(sm.getBufLen(), (byte) 0, sm.getBuf(), 0);
                log.debug("准备转发[全部玩家]消息{} --> {}", sm, p);
                broadcastToAll(p);
                break;
            }
            case LogoutPxMsg.ID: {
                LogoutPxMsg sm = (LogoutPxMsg) msg;

                close(sm.getSessionId());
                break;
            }
            case JoinRoomMsg.ID: {
                //开始处理玩家进入游戏的情况
                JoinRoomMsg sm = (JoinRoomMsg) msg;
                SceneInfo sceneInfo = new SceneInfo();
                sceneInfo.setScenePort(sm.getScenePort());
                sceneInfo.setSceneAddress(sm.getSceneAddress());
                sceneInfo.setSessionId(sm.getSessionId());
                sceneInfo.setSceneId(sm.getSceneId());

                map.put(sm.getSessionId(), sceneInfo);
                sceneClientManager.checkConnect(sm.getSceneId(), sm.getSceneAddress(), sm.getScenePort(), () -> {
                    toBoss(sm);
                });
                break;
            }
            case ExitRoomMsg.ID: {
                //开始处理玩家进入游戏的情况
                ExitRoomMsg sm = (ExitRoomMsg) msg;
                map.remove(sm.getSessionId());
                toBoss(sm);
                break;
            }
            case DelRoomMsg.ID: {
                //开始处理玩家进入游戏的情况
                DelRoomMsg sm = (DelRoomMsg) msg;
                map.remove(sm.getSessionId());
                toBoss(sm);
                break;
            }
            default:
                break;
        }
    }

    public void onBossConnect() {
        bossClient.writeAndFlush(new RegGatewayMsg(gatewayId));
    }

    public void onBossDisconnect() {
    }

    public void onSceneConnect(SceneClient client) {
        client.writeAndFlush(new RegGatewayMsg(gatewayId));
    }

    public void onSceneDisconnect(SceneClient client) {

    }

    public void handlerScene(PxMsg msg) throws Exception {
        handlerBoss(msg);
    }


    public void onCreateSession(ChannelHandlerContext ctx, Session<User> session) {
        RegSessionMsg msg = new RegSessionMsg();
        msg.setSessionId(session.get().getId());

        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String hostAddress = socketAddress.getAddress().getHostAddress();
        msg.setIp(hostAddress);
        bossClient.writeAndFlush(msg);
    }

    public boolean isWebSocket() {
        return isWebSocket;
    }

    public void setWebSocket(boolean webSocket) {
        isWebSocket = webSocket;
    }

    public void setGatewayId(short gatewayId) {
        this.gatewayId = gatewayId;
    }
}
