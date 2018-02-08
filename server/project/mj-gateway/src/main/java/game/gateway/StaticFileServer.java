package game.gateway;

import com.isnowfox.core.net.httpserver.HttpStaticFileServer;

import java.io.File;

/**
 *
 */
public class StaticFileServer {
    public static void main(String[] args) throws Exception {
        HttpStaticFileServer.start(
                8080, false, new File("/Volumes/doc/wk/h5game/majiang/server/test")
        );
    }
}
