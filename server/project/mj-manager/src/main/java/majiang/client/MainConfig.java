package majiang.client;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author zuoge85@gmail.com on 2016/12/7.
 */

@EnableAutoConfiguration
@ComponentScan(basePackages={"majiang.client.controller"})
@Configuration
public class MainConfig {

}
