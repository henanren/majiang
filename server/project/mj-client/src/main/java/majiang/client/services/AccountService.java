package majiang.client.services;

import com.fasterxml.jackson.databind.node.ObjectNode;
import majiang.client.BossConfig;
import majiang.client.WeixinConfig;
import majiang.client.db.DbManager;
import majiang.client.model.LoginTokenInfo;
import majiang.client.model.UserAccount;
import majiang.client.model.WeixinUserInfo;
import majiang.client.utils.HttpClientUtils;
import majiang.client.utils.JsonUtils;
import majiang.client.utils.RSACoderUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zuoge85@gmail.com on 2016/12/8.
 */
@Service
public class AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    public static final String ACCOUNT_TOKEN_NAME = "accountToken";
    public static final String ICON_TYPE_NAME = "icon";
    @Autowired
    private DbManager dbManager;

    @Autowired
    private BossConfig bossConfig;

    @Autowired
    private WeixinConfig weixinConfig;

    public String checkLoginToEncrypt(UserAccount userAccount) throws Exception {
        if (userAccount == null) {
            return null;
        }
        String userInfo = JsonUtils.serialize(userAccount.getWeixinUserInfo());
        byte[] data = RSACoderUtils.encryptByPublicKey(
                userInfo.getBytes("utf8"),
                bossConfig.getPublicKey()
        );
        String dataStr = Base64.encodeBase64URLSafeString(data);

        return dataStr;
    }


    public UserAccount checkLogin(String accountToken) {
        if (accountToken == null) {
            return null;
        }
        LoginTokenInfo loginTokenInfo = dbManager.get(LoginTokenInfo.class, accountToken);
        if (loginTokenInfo == null) {
            return null;
        }
        WeixinUserInfo weixinUserInfo = dbManager.get(WeixinUserInfo.class, loginTokenInfo.getUserId());
        if (weixinUserInfo == null) {
            return null;
        }
        return new UserAccount(loginTokenInfo, weixinUserInfo);
    }

    public byte[] getIcon(String uuid) {
        return dbManager.get(ICON_TYPE_NAME, uuid);
    }

    public UserAccount login(WeixinUserInfo weixinUserInfo, HttpServletResponse response) {
        String userId = weixinUserInfo.getOpenid();
        weixinUserInfo.setUserId(userId);


        String headimgurl = weixinUserInfo.getHeadimgurl();
        if (headimgurl != null) {
            try {
                byte[] down = HttpClientUtils.down(headimgurl);
                dbManager.save("icon", userId, down);
            } catch (IOException e) {
                log.error("下载错误!{}", e.getMessage(), e);
            }
        }
        weixinUserInfo.setHeadimgurl(weixinConfig.getSiteUrl() + "/icon/" +userId+".jpg");

        dbManager.save(userId, weixinUserInfo);
        String accountToken = UUID.randomUUID().toString().replace("-", "");
        LoginTokenInfo loginTokenInfo = new LoginTokenInfo(
                System.currentTimeMillis(),
                accountToken,
                userId
        );
        dbManager.save(accountToken, loginTokenInfo);

        Cookie cookie = new Cookie(ACCOUNT_TOKEN_NAME, accountToken);
        cookie.setMaxAge(100 * 24 * 60 * 60);
        response.addCookie(cookie);
        return new UserAccount(loginTokenInfo, weixinUserInfo);
    }

}
