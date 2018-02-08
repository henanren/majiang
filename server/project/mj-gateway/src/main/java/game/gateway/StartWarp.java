package game.gateway;

import com.isnowfox.core.SpringIocFactory;
import game.flash843.Flash843;
import game.gateway.server.BossClient;
import game.gateway.server.GameServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartWarp {
    private final static Logger log = LoggerFactory.getLogger(GatewayMain.class);

    public static void start() throws Exception {
        SpringIocFactory ioc = null;
      // Flash843 f843 = null;
        try {
           // f843 = Flash843.createServer(1843);
          // f843.start();

            ioc = new SpringIocFactory("GatewayContext.xml");
            GameServer server = ioc.getBean(GameServer.class);
            BossClient bossClient = ioc.getBean(BossClient.class);
            bossClient.connect();
            server.init();
        } catch (Throwable e) {
            log.error("服务器启动错误,准备关闭", e);
          // if (f843 != null) {
            //  f843.close();
           // }
            if (ioc != null) {
                ioc.destroy();
            }
        }

//		ConsoleCommand.blockStart(new Command() {
//			@Override
//			public boolean onError(Throwable th) {
//				log.error("控制命令执行异常",th);
//				return false;
//			}
//
//			@Override
//			public boolean execute(String name, String... args) {
//				switch (name) {
//				case "exit":
//					ioc.destroy();
//                    try {
//                        f843.close();
//                    } catch (Exception e) {
//                        log.error(e.getMessage(), e);
//                    }
//                    return true;
//				default:
//					break;
//				}
//				return false;
//			}
//		});
    }
}
