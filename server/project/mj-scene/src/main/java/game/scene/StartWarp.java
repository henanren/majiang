package game.scene;

import com.isnowfox.core.IocFactory;
import com.isnowfox.util.ArrayExpandUtils;
import game.scene.net.BossClient;
import game.scene.net.SceneServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartWarp {
    private static final Logger log = LoggerFactory.getLogger(SceneMain.class);

    public static void start(String[] args) {
        IocFactory ioc = null;
        try {
            String arg1 = ArrayExpandUtils.get(args, 0);
            log.debug("数据库参数:{}", arg1);
            ioc = IocFactoryProxy.getInstance().init();
            SceneServer sceneServer = ioc.getBean("sceneServer", SceneServer.class);
            sceneServer.start();
            BossClient bossClient = ioc.getBean("bossClient", BossClient.class);
            bossClient.connect();
            System.gc();
            log.info("服务器启动完毕！");
        } catch (Throwable e) {
            log.error("服务器启动错误,准备关闭", e);
            if (ioc != null) {
                ioc.destroy();
            }
        }
    }
}
