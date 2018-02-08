package majiang.client.services;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import game.boss.dao.dao.UserAgentTokenDao;
import game.boss.dao.dao.UserDao;
import game.boss.dao.entity.UserAgentTokenDO;
import game.boss.dao.entity.UserDO;
import majiang.client.portal.AgentAccount;
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
public class AgentUserAccountService {
    private static final String salt = "n532ij";

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAgentTokenDao userAgentTokenDao;

    private Cache<String, Optional<AgentAccount>> accountCache;

    @PostConstruct
    private void init() {
        accountCache = CacheBuilder.newBuilder().maximumSize(100).build();
    }

    public AgentAccount login(int id, String password) {
        UserDO userDO = userDao.findObject(
                UserDO.Table.ID, id
        );
        if (userDO != null && userDO.getPassword().equals(digestPassword(password))) {
            UserAgentTokenDO tokenDO = new UserAgentTokenDO();
            tokenDO.setToken(generateToken());
            tokenDO.setUserId(id);
            userAgentTokenDao.insert(
                    tokenDO
            );

            AgentAccount account = new AgentAccount(userDO, tokenDO);
            accountCache.put(tokenDO.getToken(), Optional.of(account));
            return account;
        }
        return null;
    }


    public AgentAccount getByToken(String accessToken) throws ExecutionException {
        return accountCache.get(accessToken, () -> {
            UserAgentTokenDO tokenDO = userAgentTokenDao.findObject(
                    UserAgentTokenDO.Table.TOKEN, accessToken
            );
            if (tokenDO == null) {
                return Optional.empty();
            }
            UserDO userDO = userDao.findObject(
                    UserDO.Table.ID, tokenDO.getId()
            );

            AgentAccount account = new AgentAccount(userDO, tokenDO);
            return Optional.of(account);
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
