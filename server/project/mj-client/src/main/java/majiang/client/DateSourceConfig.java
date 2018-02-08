package majiang.client;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;

/**
 * @author zuoge85@gmail.com on 2016/12/7.
 */
@Configuration
@ConfigurationProperties(prefix = "jdbc")
public class DateSourceConfig {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int maxNums;
    private int minNums;

    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource source = new DruidDataSource();
        source.setUrl(url);
        source.setUsername(username);
        source.setPassword(password);
        source.setFilters("stat");

        source.setMaxActive(maxNums);
        source.setInitialSize(minNums);
        source.setMaxWait(60000);
        source.setMinIdle(minNums);

        source.setTimeBetweenEvictionRunsMillis(60000);
        source.setMinEvictableIdleTimeMillis(300000);

        source.setTestWhileIdle(true);
        source.setTestOnBorrow(false);
        source.setTestOnReturn(false);

        source.setPoolPreparedStatements(true);
        source.setMaxOpenPreparedStatements(200);
        source.setConnectionInitSqls(
                Collections.singletonList("SET NAMES utf8mb4;")
        );
        return source;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public int getMaxNums() {
        return maxNums;
    }

    public void setMaxNums(int maxNums) {
        this.maxNums = maxNums;
    }

    public int getMinNums() {
        return minNums;
    }

    public void setMinNums(int minNums) {
        this.minNums = minNums;
    }

    @Override
    public String toString() {
        return "DateSourceConfig{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", driverClassName='" + driverClassName + '\'' +
                ", maxNums='" + maxNums + '\'' +
                ", minNums='" + minNums + '\'' +
                '}';
    }
}
