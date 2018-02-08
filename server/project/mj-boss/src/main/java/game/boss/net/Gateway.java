package game.boss.net;

import com.isnowfox.core.net.Session;
import com.isnowfox.core.net.message.Message;
import com.isnowfox.core.net.message.MessageFactory;
import com.isnowfox.game.proxy.message.AllPxMsg;
import com.isnowfox.game.proxy.message.LogoutPxMsg;
import com.isnowfox.game.proxy.message.PxMsg;
import com.isnowfox.game.proxy.message.SinglePxMsg;
import game.boss.ServerRuntimeException;
import game.boss.services.UserService;
import io.netty.channel.Channel;
import mj.net.message.login.Pong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.locks.ReentrantLock;


final class Gateway {
    private static final Logger log = LoggerFactory.getLogger(Gateway.class);


    @Autowired
    private MessageFactory messageFactory;
    @Autowired
    private UserService userService;

    private Channel channel;
    //	private final User[] userArray;
    private final Session<UserImpi>[] userArray;
    private int id;
    private final ReentrantLock lock = new ReentrantLock();

    @SuppressWarnings("unchecked")
    Gateway(int sceneMaxConnect) {
        userArray = new Session[sceneMaxConnect];
    }

    Session<UserImpi> getSession(short sessionId) {
        return userArray[sessionId];
    }

    Session<UserImpi> reg(short sessionId, String ip) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            UserImpi user = new UserImpi();
            user.setSessionId(sessionId);
            user.setGatewayId(id);
            user.setGateway(this);
            user.setIp(ip);

            Session<UserImpi> s = new Session<>(channel);
            s.set(user);
            userArray[sessionId] = s;
            log.info("注册Session:{},ip:{} [gateway:{}] ", id, ip, this.id);
            return s;
        } finally {
            lock.unlock();
        }
    }

    void unReg(short sessionId) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Session<UserImpi> session = getSession(sessionId);
            if (session != null) {
                UserImpi userImpi = session.get();
                String name = null;
                String ip = null;
                if (userImpi != null) {
                    userService.logout(userImpi);
                    ip = userImpi.getIp();
                    if (userImpi.getUserDO() != null) {
                        name = userImpi.getUserDO().getName();
                    }
                }
                session.set(null);
                session.setInfo(null);
                userArray[sessionId] = null;
                channel.writeAndFlush(new LogoutPxMsg(sessionId));
                log.info("注销Session:{}, name:{}, ip:{} [gateway:{}] ", id, name, ip, this.id);
            }
        } finally {
            lock.unlock();
        }
    }

    void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Gateway [channel=" + channel + "]";
    }

    void setId(int id) {
        this.id = id;
    }

    int getId() {
        return id;
    }

    void close() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            for (int sessionId = 0, userArrayLength = userArray.length; sessionId < userArrayLength; sessionId++) {
                Session<UserImpi> session = userArray[sessionId];
                if (session != null) {
                    unReg((short) sessionId);
                }
            }
            channel.close();
        } finally {
            lock.unlock();
        }
    }

    void send(short userSessionId, Message msg) {
        checkMsg(msg);
        SinglePxMsg.EncodeWrapper w = new SinglePxMsg.EncodeWrapper(userSessionId, msg);
        if(!(msg instanceof Pong)){
            log.info("发送{}", w);
        }
        channel.writeAndFlush(w);
    }

    void sendAll(Message msg) {
        checkMsg(msg);
        AllPxMsg.EncodeWrapper w = new AllPxMsg.EncodeWrapper(msg);
        log.info("发送{}", w);
        channel.writeAndFlush(w);
    }

    void sendToGateway(PxMsg msg) {
        log.info("发送{}", msg);
        channel.writeAndFlush(msg);
    }

    private void checkMsg(Message msg) {
        Message testMsg;
        try {
            testMsg = messageFactory.getMessage(msg.getMessageType(), msg.getMessageId());
        } catch (Exception e) {
            throw new ServerRuntimeException("错误的消息", e);
        }
        if (!testMsg.getClass().isAssignableFrom(msg.getClass())) {
            throw new ServerRuntimeException("错误的消息");
        }
    }
}
