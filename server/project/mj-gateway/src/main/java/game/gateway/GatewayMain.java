package game.gateway;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;


public class GatewayMain {
	public static final void main(String ...args){
		try {
			File dir = new File("lib");
			if(dir.exists()){
				File[] fileArray = dir.listFiles();
				URLClassLoader cl = (URLClassLoader)ClassLoader.getSystemClassLoader();
				Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);  
	            addURL.setAccessible(true);  
				for (File file : fileArray) {
					addURL.invoke(cl, file.toURI().toURL()); 
				}
			}
			StartWarp.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
