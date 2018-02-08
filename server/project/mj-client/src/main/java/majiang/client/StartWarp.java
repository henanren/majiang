package majiang.client;

import org.springframework.boot.SpringApplication;

/**
 * @author zuoge85@gmail.com on 2017/1/7.
 */
public class StartWarp {
//    private static final Logger logger = LogManager.getLogger(StartWarp.class);

    public static void start(String[] args) throws Exception {
//        if (System.getProperty("log4j.configurationFile") == null) {
//            URL log4j2ConfigUrl = StartWarp.class.getResource("/ClientLog4j2.yml");
//            System.setProperty("log4j.configurationFile", log4j2ConfigUrl.getFile());
//        }

//
//        try (InputStream stream = log4j2ConfigUrl.openStream()) {
//            ConfigurationSource source = new ConfigurationSource(stream, log4j2ConfigUrl);
//            Configuration config = YamlConfigurationFactory.getInstance().getConfiguration(source);
//            LoggerContext ctx = (LoggerContext) LogManager.getContext(true);
//
////            ctx.stop();
//            ctx.start(config);
//
        SpringApplication.run(MainConfig.class, args);
//        }
    }
}
