package game.gateway;

import com.isnowfox.core.net.Session;
import com.isnowfox.game.platform.User;
import com.isnowfox.game.proxy.message.LogoutPxMsg;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock;

public class SessionService {
    private final static Logger log = LoggerFactory.getLogger(SessionService.class);

    //private final Config config;
    private final User[] userArray;
    private final TreeSet<Short> idPool = new TreeSet<>();
    private int max;

    private final int maxConnect;
    private final int freeWaitNums;
    /**
     * 用户注册和离开都需要这个锁
     */
    private final ReentrantLock lock = new ReentrantLock();

    @Autowired
    private GatewayService gatewayService;

    public SessionService(Config config) {
        //this.config = config;
        this.userArray = new User[config.getMaxConnect()];
        maxConnect = (int) (config.getMaxConnect() * 1.3 + 1);
        freeWaitNums = config.getMaxConnect() * 1 / 3;
    }

    public Session<User> reg(ChannelHandlerContext ctx) throws FileNotFoundException, IOException, GatewayException {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            int id;
            if (idPool.size() > freeWaitNums) {
                id = idPool.pollFirst();
            } else {
                if (max >= maxConnect) {
                    throw new GatewayException("pool id too big:" + max + ", max:" + maxConnect);
                }
                id = max;
                max++;
            }
            User user = new User((short) id, ctx.channel());
            Session<User> session = new Session<>(ctx.channel());
            session.set(user);

            userArray[id] = user;
            log.info("注册Session:{}", id);
            return session;
        } finally {
            lock.unlock();
        }
    }

    public void unReg(User user) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (user != null) {
                short id = user.getId();
                LogoutPxMsg msg = new LogoutPxMsg(id);
                gatewayService.toBoss(msg);
                idPool.add(id);
                userArray[id] = null;
                log.info("注销Session:{}", id);
            }
        } finally {
            lock.unlock();
        }
    }

    public User getUser(int sessionId) {
        if (sessionId > -1 && sessionId < userArray.length) {
            return userArray[sessionId];
        }
        return null;
    }

    public int getMax() {
        return max;
    }
}
