package game.gateway.server;

import com.google.common.collect.ArrayListMultimap;
import com.isnowfox.core.net.message.Packet;
import com.isnowfox.core.thread.FrameQueueContainer;
import com.isnowfox.game.proxy.message.SinglePxMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zuoge85@gmail.com on 16/9/26.
 */
public final class SceneClientManager extends FrameQueueContainer implements ApplicationContextAware {
    private static final int FRAME_TIME_SPAN = 33;
    private static final int RUN_QUEUE_MAX = 1024;

    private static final Logger log = LoggerFactory.getLogger(SceneClientManager.class);

    private ApplicationContext applicationContext;
    private final ConcurrentHashMap<Integer, SceneClient> map = new ConcurrentHashMap<>();
    private final ArrayListMultimap<Integer, Runnable> mapCallbacks = ArrayListMultimap.create();

    public SceneClientManager() {
        super(FRAME_TIME_SPAN, RUN_QUEUE_MAX);
        start();
    }

    public void checkConnect(int sceneId, String address, int port, Runnable callback) throws Exception {
        run(() -> {
            SceneClient client = map.get(sceneId);
            if (client == null) {
                mapCallbacks.put(sceneId, callback);
                add(sceneId, address, port);
            } else {
                if (client.isConnect()) {
                    callback.run();
                } else {
                    mapCallbacks.put(sceneId, callback);
                }
            }
        });
    }

    private void add(int sceneId, String address, int port) {
        try {
            SceneClientHandler sceneClientHandler = new SceneClientHandler(() -> runConnectCallback(sceneId));
            applicationContext.getAutowireCapableBeanFactory().autowireBean(sceneClientHandler);
            SceneClient client = new SceneClient(address, port, sceneClientHandler);
            sceneClientHandler.setClient(client);
            client.connect();
            map.put(sceneId, client);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void runConnectCallback(int sceneId) {
        run(() -> {
            SceneClient client = map.get(sceneId);
            List<Runnable> callbacks = mapCallbacks.get(sceneId);
            for (int i = 0; i < callbacks.size(); i++) {
                callbacks.get(i).run();
            }
            client.setConnect(true);
        });
    }

    public void forwardMessage(SinglePxMsg msg, SceneInfo sceneInfo) {
        int sceneId = sceneInfo.getSceneId();
        SceneClient client = map.get(sceneId);
        client.writeAndFlush(msg);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    protected void threadMethod(int i, long l, long l1) {

    }

    @Override
    protected void errorHandler(Throwable t) {
        log.error("严重异常", t);
    }
}
