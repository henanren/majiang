package game.game.db;

import com.isnowfox.core.IocFactory;
import com.isnowfox.core.SpringIocFactory;
import org.forkjoin.jdbckit.mysql.Builder;
import org.forkjoin.jdbckit.mysql.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;


public class DaoBuilder {
    private static final Logger log = LoggerFactory.getLogger(DaoBuilder.class);

    /**
     *
     */
    public static void main(String[] args) {
        try {

            File projectFile = new File(new File(".").getCanonicalFile(), "project");
            if(!projectFile.exists()){
                projectFile = new File(new File(".").getCanonicalFile(), "server/project");
            }

            String path = new File(
                    projectFile,
                    "mj-dao/src/main/java"
            ).getAbsolutePath();

            String resourcesPath = new File(
                    projectFile,
                    "mj-dao/src/main/resources"
            ).getAbsolutePath();

            File dir = new File(path);
            File resourcesDir = new File(resourcesPath);
            log.info("代码路径:{}", dir.getAbsolutePath());
            //ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,Main.class.getResource(".").getFile());
            //ve.init();
            Config config = new Config(dir, resourcesDir);
            config.setPack("game.boss.dao");
            IocFactory ioc = new SpringIocFactory("/boss/BossBuilderContext.xml");
            DataSource ds = ioc.getBean("dataSource", DataSource.class);
            Connection conn = null;
            try {
                Thread.sleep(1000);
                conn = ds.getConnection();
                Builder builder = new Builder(config, conn);
                builder.objectCreate();
                //builder.daoCreate();
                builder.daoImplCreate();
                builder.springXmlCreate();
                log.info("生成结束");
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
