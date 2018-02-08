package majiang.client.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.cache.CacheBuilder;
import majiang.client.WeixinConfig;
import majiang.client.db.DbManager;
import majiang.client.utils.HttpClientUtils;
import majiang.client.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zuoge85@gmail.com on 2016/12/7.
 */
@Service
public class WeiXinService {
    private static final Logger log = LoggerFactory.getLogger(WeiXinService.class);

    @Autowired
    private WeixinConfig weixinConfig;
    @Autowired
    private DbManager dbManager;

    protected com.google.common.cache.Cache<String, Optional<AccessToken>> localCache;
    protected com.google.common.cache.Cache<String, Optional<String>> jsticketCache;

    public WeiXinService() {
    }


    @PostConstruct
    public void init() {
        localCache = CacheBuilder.newBuilder().expireAfterWrite(
                6200, TimeUnit.SECONDS
        ).maximumSize(1).build();

        jsticketCache = CacheBuilder.newBuilder().expireAfterWrite(
                6200, TimeUnit.SECONDS
        ).maximumSize(1).build();
    }

    public ObjectNode weixinConifg(String url) {
        url = weixinConfig.getSiteUrl() + url;
        String nonceStr = UUID.randomUUID().toString().replace("-", "");
        long timestamp = System.currentTimeMillis() / 1000;

        String str1 = "jsapi_ticket=" + getJsapiTicket()
                + "&noncestr=" + nonceStr + "&timestamp="
                + timestamp + "&url=" + url;

        String signature = org.apache.commons.codec.digest.DigestUtils.sha1Hex(str1);

        ObjectNode config = JsonUtils.getObjectMapper().createObjectNode();
        config.put("debug", false);
        config.put("appId", weixinConfig.getAppID());
        config.put("timestamp", timestamp);
        config.put("nonceStr", nonceStr);
        config.put("signature", signature);

        config.putArray("jsApiList")
                .add("onMenuShareTimeline")
                .add("onMenuShareAppMessage")
                .add("onMenuShareQQ")
                .add("onMenuShareWeibo")
                .add("onMenuShareQZone")
                .add("startRecord")
                .add("stopRecord")
                .add("onVoiceRecordEnd")
                .add("playVoice")
                .add("pauseVoice")
                .add("stopVoice")
                .add("onVoicePlayEnd")
                .add("uploadVoice")
                .add("downloadVoice")
                .add("chooseImage")
                .add("previewImage")
                .add("uploadImage")
                .add("downloadImage")
                .add("translateVoice")
                .add("getNetworkType")
                .add("openLocation")
                .add("getLocation")
                .add("hideOptionMenu")
                .add("showOptionMenu")
                .add("hideMenuItems")
                .add("showMenuItems")
                .add("hideAllNonBaseMenuItem")
                .add("showAllNonBaseMenuItem")
                .add("closeWindow")
                .add("scanQRCode")
                .add("chooseWXPay")
                .add("openProductSpecificView")
                .add("addCard")
                .add("chooseCard")
                .add("openCard");
        return config;
    }


    public String getJsapiTicket() {
        String appid = weixinConfig.getAppID();
        String secret = weixinConfig.getAppsecret();
        return getJsapiTicket(appid, secret);
    }

    public String getJsapiTicket(String appid, String secret) {
        String accessTokenName = "jsapi_ticket-" + appid;
        try {
            return jsticketCache.get(accessTokenName, () -> {
                JsonNode json = HttpClientUtils.getJson(
                        weixinConfig.getJsticketUrl(),
                        "access_token", getAccessToken(), "type", "jsapi"
                );

                if (!json.has("errcode") || json.get("errcode").asInt() == 0) {
                    String token = json.get("ticket").asText();
                    int expires_in = json.get("expires_in").asInt();
                    return Optional.of(token);
                } else {
                    log.error("获取jsapi_ticket 失败,腾讯返回:{}", json);
                    return Optional.empty();
                }
            }).orElse(null);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAccessToken() {
        String appid = weixinConfig.getAppID();
        String secret = weixinConfig.getAppsecret();
        String clienttokenUrl = weixinConfig.getClienttokenUrl();

        String accessTokenName = "accessToken-" + appid;
        try {
            AccessToken accessToken = localCache.get(accessTokenName, () -> {
                JsonNode json = HttpClientUtils.getJson(clienttokenUrl,
                        "grant_type", "client_credential", "appid", appid, "secret", secret
                );
                if (!json.has("errcode")) {
                    String token = json.get("access_token").asText();
                    int expires_in = json.get("expires_in").asInt();


                    return Optional.of(new AccessToken(token, System.currentTimeMillis() + ((long) (expires_in * 0.6)) * 1000));
                } else {
                    throw new Exception("获取access_token 失败,腾讯返回:" + json);
                }
            }).orElse(null);
            return accessToken.getToken();
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        }
    }


    protected <CK, CV> com.google.common.cache.Cache<CK, Optional<CV>> newLocalCache(String name, int maxSeconds, int maxNums) {
        com.google.common.cache.Cache<CK, Optional<CV>> cache = CacheBuilder.newBuilder().expireAfterWrite(
                maxSeconds, TimeUnit.SECONDS
        ).maximumSize(maxNums).build();
        return cache;
    }

    private static class AccessToken {
        private String token;
        private long expiresTime;

        public AccessToken() {
        }

        public AccessToken(String token, long expiresTime) {
            this.token = token;
            this.expiresTime = expiresTime;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public long getExpiresTime() {
            return expiresTime;
        }

        public void setExpiresTime(long expiresTime) {
            this.expiresTime = expiresTime;
        }

        @Override
        public String toString() {
            return "AccessToken{" +
                    "token='" + token + '\'' +
                    ", expiresTime=" + expiresTime +
                    '}';
        }
    }
}
