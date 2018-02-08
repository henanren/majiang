package game.msg;

import com.isnowfox.io.serialize.tool.Config;
import com.isnowfox.io.serialize.tool.SerializeMain;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class StartWarp {
	public static final void start() throws IOException {
		File projectFile = new File(new File(".").getCanonicalFile(), "project");
		if(!projectFile.exists()){
			projectFile = new File(new File(".").getCanonicalFile(), "server/project");
		}

		File clientFile = new File(new File(".").getCanonicalFile(), "laya-client");
		if(!clientFile.exists()){
			clientFile = new File(new File(".").getCanonicalFile(), "client/laya-client");
		}

		File msgDir = new File(
                projectFile,
                "mj-data/src/main/resources/msg"
        );

        File javaDir = new File(
                projectFile,
                "mj-data/src/main/java/"
        );


		Config c = Config.load();
		c.setPath(msgDir.getAbsolutePath());
		c.setJavaSrcPath(javaDir.getAbsolutePath());
		c.setJavaRootPackage("mj.net.message");
		c.setJavaHandlerRootPackage("mj.net.handler");

		c.setJavaCharacterPackage("game.boss.model");
		c.setJavaCharacterClassName("User");

		File asDir = new File(
				clientFile,
				"src"
		);
        c.setAsSrcPath(asDir.getAbsolutePath());
		c.setAsRootPackage("mj.net.message");
		c.setAsHandlerRootPackage("mj.net.handler");
        c.setAsDirName("laya/");
		c.setJavaDirName("newjava/");
		c.setOverrideHandler(true);
		SerializeMain.start(c);
	}
}
