package majiang.client.db;

import majiang.client.utils.JsonUtils;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.fusesource.leveldbjni.JniDBFactory.factory;

/**
 * @author zuoge85@gmail.com on 2016/12/8.
 */
@Component
public class DbManager {

    public static final String CHARSET_NAME = "utf8";
    private DB db;
    private volatile boolean isClose = true;

    @PostConstruct
    public void init() throws IOException {
        Options options = new Options();
        options.createIfMissing(true);
        File path = new File("db/weixinDb.db");
        if (!path.exists()) {
            path.mkdirs();
        }
        db = factory.open(path, options);
        isClose = false;


        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public <T> T get(Class<T> cls, String key) {
        try {
            byte[] keyBytes = (cls.getName() + "_" + key).getBytes(CHARSET_NAME);
            byte[] value = db.get(keyBytes);

            if (value == null) {
                return null;
            }
            return JsonUtils.deserialize(new String(value, CHARSET_NAME), cls);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void save(String key, T t) {
        Class<?> cls = t.getClass();
        try {
            byte[] keyBytes = (cls.getName() + "_" + key).getBytes(CHARSET_NAME);
            db.put(
                    keyBytes,
                    JsonUtils.serialize(t).getBytes(CHARSET_NAME)
            );
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    public byte[] get(String type, String key) {
        try {
            byte[] keyBytes = (type + "_" + key).getBytes(CHARSET_NAME);
            return db.get(keyBytes);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(String type, String key, byte[] data) {
        try {
            byte[] keyBytes = (type + "_" + key).getBytes(CHARSET_NAME);
            db.put(
                    keyBytes,
                    data
            );
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    @PreDestroy
    public void close() throws IOException {
        if (!isClose) {
            synchronized (this) {
                if (!isClose) {
                    db.close();
                }
            }
        }
    }
}


