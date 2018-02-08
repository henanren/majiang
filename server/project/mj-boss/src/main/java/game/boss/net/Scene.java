package game.boss.net;

import com.isnowfox.core.net.message.Message;
import com.isnowfox.core.net.message.MessageFactory;
import com.isnowfox.game.proxy.message.PxMsg;
import game.boss.ServerRuntimeException;
import game.boss.services.UserService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;


final class Scene {
    private static final Logger log = LoggerFactory.getLogger(Scene.class);

    @Autowired
    private MessageFactory messageFactory;

    private Channel channel;

    private int id;
    private final ReentrantLock lock = new ReentrantLock();

    private String address;

    private int port;

    Scene() {
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
            channel.close();
        } finally {
            lock.unlock();
        }
    }

    void send(PxMsg msg) {
        channel.writeAndFlush(msg).addListener(future -> {
            future.get();
        });;
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

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
