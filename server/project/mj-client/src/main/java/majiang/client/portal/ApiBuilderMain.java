package majiang.client.portal;

import org.forkjoin.apikit.Analyse;
import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.Manager;
import org.forkjoin.apikit.ObjectFactory;
import org.forkjoin.apikit.generator.JavaClientGenerator;
import org.forkjoin.apikit.generator.JavaScriptGenerator;
import org.forkjoin.apikit.impl.JdtAnalyse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


public class ApiBuilderMain {
    private static final Logger log = LoggerFactory.getLogger(ApiBuilderMain.class);

    /**
     *
     */
    public static void main(String[] args) throws Exception {
        new ApiBuilderMain().buildAll();
    }

    private String adminPackage = "majiang.client.portal.admin";
    private String agentPackage = "majiang.client.portal.agent";
    private String adminClientPackage = "majiang.client.portal.client.admin";
    private String agentClientPackage = "majiang.client.portal.client.agent";


    private String version = "v1";
    private File dir;
    private File testDir;
    private File adminJsDir;
    private File agentJsDir;

    public ApiBuilderMain() {
        File root = new File("project/mj-client");
        if (!root.exists()) {
            root = new File("mj-client");
        }
        if (!root.exists()) {
            root = new File(".");
        }

        dir = new File(root, "src/main/java/");
        log.info("代码路径:{}", dir.getAbsolutePath());

        testDir = new File(root, "src/test/java/");
        adminJsDir = new File(root, "../../manager/admin-js-sdk");
        agentJsDir = new File(root, "../../manager/agent-js-sdk");
    }

    private void buildAll() throws Exception {
        buildAdmin(adminPackage, adminClientPackage, adminJsDir);
        buildAdmin(agentPackage, agentClientPackage, agentJsDir);
    }

    private void buildAdmin(String rootPackage, String clientPackage, File jsDir) throws Exception {
        Manager manager = new Manager();
        manager.setPath(dir.getAbsolutePath());
        manager.setRootPackage(rootPackage);
        manager.setObjectFactory(objectFactory);

        //开始处理
        manager.analyse();

        {
            JavaClientGenerator generator = new JavaClientGenerator();
            generator.setOutPath(testDir.getAbsolutePath());
            generator.setRootPackage(clientPackage);
            manager.generate(generator);
        }

        if (jsDir.getParentFile().exists()) {
            JavaScriptGenerator generator = new JavaScriptGenerator();
            generator.setOutPath(jsDir.getAbsolutePath());
            generator.setVersion(version);
            manager.generate(generator);
        }
    }

    private static ObjectFactory objectFactory = new ObjectFactory() {
        @Override
        public Analyse createAnalyse() {
            return new JdtAnalyse();
        }

        @Override
        public Context createContext() {
            return new Context();
        }
    };
}
