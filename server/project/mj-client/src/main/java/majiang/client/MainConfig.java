package majiang.client;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author zuoge85@gmail.com on 2016/12/7.
 */

//@EnableAutoConfiguration
@ComponentScan
@ImportResource("classpath*:game/boss/dao/DaoContext.xml")
@ConfigurationProperties(prefix = "jdbc")
@Configuration
@Import({DateSourceConfig.class, WebConfig.class})
@SpringBootApplication(exclude = {FlywayAutoConfiguration.class})
public class MainConfig{

}
