package game.boss.admin;

import com.isnowfox.util.collect.ConcurrentArrayList;
import game.boss.ServerException;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 现在支持注册多个网关
 */
final class AdminManager implements ApplicationContextAware {
    private final static Logger log = LoggerFactory.getLogger(AdminManager.class);

    /**
     * 网关锁,对网关注册,注销都需要这个锁
     */
    private final ReentrantLock lock = new ReentrantLock();
    private ConcurrentArrayList<Admin> adminList = new ConcurrentArrayList<>();
    private ConcurrentHashMap<ChannelId, Admin> adminMap = new ConcurrentHashMap<>();
    private ApplicationContext applicationContext;

    Admin reg(Channel channel) throws ServerException {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            log.info("Admin注册成功:{}", channel);
            Admin Admin = new Admin();
            applicationContext.getAutowireCapableBeanFactory().autowireBean(Admin);

            Admin.setChannel(channel);

            adminMap.put(channel.id(), Admin);
            adminList.add(Admin);
            return Admin;
        } finally {
            lock.unlock();
        }
    }

    void unreg(Admin admin) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (admin != null) {
                admin.close();
                clear(admin);
            }
            log.info("Admin注销成功!{}", admin);
        } finally {
            lock.unlock();
        }
    }

    public Admin get(Channel channel) {
        return adminMap.get(channel.id());
    }

    private void clear(Admin admin) {
        if (admin != null) {
            adminMap.remove(admin.getChannel().id());
            adminList.remove(admin);
        }
    }

//    void send(int AdminId, PxMsg pxMsg) {
//        Admin Admin = AdminMap.get(AdminId);
//        if (Admin != null) {
//            Admin.send(pxMsg);
//        } else {
//            throw new ServerRuntimeException("场景不存在!AdminId:" + AdminId);
//        }
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

//    int getRandomAdminId() {
//        Object[] objects = adminList.toArray();
//        Admin Admin = (Admin) objects[RandomUtils.nextInt(objects.length)];
//        return Admin.getId();
//    }
//
//    Admin getAdmin(int AdminId) {
//        return AdminMap.get(AdminId);
//    }
}
