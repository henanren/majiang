package game.boss.net;

import com.isnowfox.game.proxy.message.PxMsg;
import com.isnowfox.util.collect.ConcurrentArrayList;
import game.boss.ServerException;
import game.boss.ServerRuntimeException;
import io.netty.channel.Channel;
import org.apache.commons.lang.math.RandomUtils;
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
final class SceneManager implements ApplicationContextAware {
    private final static Logger log = LoggerFactory.getLogger(SceneManager.class);

    /**
     * 网关锁,对网关注册,注销都需要这个锁
     */
    private final ReentrantLock lock = new ReentrantLock();
    private ConcurrentHashMap<Integer, Scene> sceneMap = new ConcurrentHashMap<>();
    private ConcurrentArrayList<Scene> sceneList = new ConcurrentArrayList<>();
    private ApplicationContext applicationContext;

    Scene reg(Channel channel, int sceneId, String sceneAddress, int scenePort) throws ServerException {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (sceneMap.containsKey(sceneId)) {
                Scene scene = sceneMap.get(sceneId);
                clear(scene);
                scene.close();
                log.info("重复的Scene,立即关闭", channel);
            }
            log.info("Scene注册成功:{}", channel);
            Scene scene = new Scene();
            applicationContext.getAutowireCapableBeanFactory().autowireBean(scene);

            scene.setChannel(channel);
            scene.setId(sceneId);
            scene.setAddress(sceneAddress);
            scene.setPort(scenePort);

            sceneMap.put(sceneId, scene);
            sceneList.add(scene);
            return scene;
        } finally {
            lock.unlock();
        }
    }

    void unreg(Scene scene) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            scene.close();
            clear(scene);
            log.info("Scene注销成功!{}", scene);
        } finally {
            lock.unlock();
        }
    }

    private void clear(Scene scene) {
        if (scene != null) {
            sceneMap.remove(scene.getId());
            sceneList.remove(scene);
        }
    }

    void send(int sceneId, PxMsg pxMsg) {
        Scene scene = sceneMap.get(sceneId);
        if (scene != null) {
            scene.send(pxMsg);
        } else {
            throw new ServerRuntimeException("场景不存在!sceneId:" + sceneId);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    int getRandomSceneId() {
        Object[] objects = sceneList.toArray();
        Scene scene = (Scene) objects[RandomUtils.nextInt(objects.length)];
        return scene.getId();
    }

    Scene getScene(int sceneId) {
        return sceneMap.get(sceneId);
    }
}
