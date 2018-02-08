package game.boss.admin;

import com.isnowfox.core.net.message.Message;
import com.isnowfox.core.net.message.MessageFactory;
import com.isnowfox.game.proxy.message.PxMsg;
import game.boss.ServerRuntimeException;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.locks.ReentrantLock;


final class Admin {
    private static final Logger log = LoggerFactory.getLogger(Admin.class);

    @Autowired
    private MessageFactory messageFactory;

    private Channel channel;

    private final ReentrantLock lock = new ReentrantLock();


    Admin() {
    }

    void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Gateway [channel=" + channel + "]";
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

    public Channel getChannel() {
        return channel;
    }
}
