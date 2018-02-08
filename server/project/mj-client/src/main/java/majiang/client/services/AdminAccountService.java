package majiang.client.services;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import game.boss.dao.dao.AdminDao;
import game.boss.dao.entity.AdminDO;
import majiang.client.portal.AdminAccount;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * @author zuoge85@gmail.com on 2017/1/7.
 */
@Service
public class AdminAccountService {
    private static final String salt = "nxTuij";

    @Autowired
    private AdminDao adminDao;

    private Cache<String, Optional<AdminAccount>> accountCache;

    @PostConstruct
    private void init() {
        accountCache = CacheBuilder.newBuilder().maximumSize(100).build();
    }


    public AdminAccount login(String name, String password) {
        AdminDO object = adminDao.findObject(
                AdminDO.Table.NAME, name
        );
        if (object != null && object.getPassword().equals(digestPassword(password))) {
            if (object.getToken() != null) {
                accountCache.invalidate(object.getToken());
            }
            object.setToken(generateToken());
            adminDao.update(object);

            AdminAccount adminAccount = new AdminAccount(object);

            accountCache.put(object.getToken(), Optional.of(adminAccount));
            return adminAccount;
        }
        return null;
    }


    public AdminAccount getByToken(String accessToken) throws ExecutionException {
        return accountCache.get(accessToken, () -> {
            AdminDO object = adminDao.findObject(
                    AdminDO.Table.TOKEN, accessToken
            );
            if (object == null) {
                return Optional.empty();
            }
            AdminAccount adminAccount = new AdminAccount(object);
            return Optional.of(adminAccount);
        }).orElse(null);
    }


    public static void main(String[] args) {
        System.out.println(RandomStringUtils.randomAlphanumeric(6));
        System.out.println(UUID.randomUUID() + RandomStringUtils.randomAlphanumeric(32));
        System.out.println(digestPassword("admin"));
    }

    private static String generateToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "") + RandomStringUtils.randomAlphanumeric(16);
    }

    private static String digestPassword(String password) {
        return Base64.encodeBase64URLSafeString(DigestUtils.sha256(salt + password));
    }

}
