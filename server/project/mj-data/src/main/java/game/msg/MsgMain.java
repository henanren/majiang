package game.msg;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.Properties;

//import com.isnowfox.io.serialize.tool.Config;
//import com.isnowfox.io.serialize.tool.SerializeMain;

public class MsgMain {

    public static void main(String[] args) {
        try {
            File dir = new File("lib");
            if (dir.exists()) {
                File[] fileArray = dir.listFiles();
                URLClassLoader cl = (URLClassLoader) ClassLoader.getSystemClassLoader();
                Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                addURL.setAccessible(true);
                for (File file : fileArray) {
                    addURL.invoke(cl, file.toURI().toURL());
                }
            }
            StartWarp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
