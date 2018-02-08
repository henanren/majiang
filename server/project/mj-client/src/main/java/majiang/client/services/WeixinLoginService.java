package majiang.client.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.reflect.Reflection;
import majiang.client.WeixinConfig;
import majiang.client.model.WeixinUserInfo;
import majiang.client.utils.HttpClientUtils;
import majiang.client.utils.JsonUtils;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author zuoge85@gmail.com on 2016/12/7.
 */
@Service
public class WeixinLoginService {
    private static final Logger log = LoggerFactory.getLogger(WeixinLoginService.class);

    private static final Charset CHARSET = Charset.forName("utf-8");

    @Autowired
    private WeixinConfig weixinConfig;

    public Optional<WeixinUserInfo> checkWeixin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("微信登录请求,未登录!");
        String code = request.getParameter("code");
        if (code == null) {
            gotoWeixin(request, response);
            return null;
        } else {
            String appid;
            String secret;
            appid = weixinConfig.getAppID();
            secret = weixinConfig.getAppsecret();

            JsonNode json = HttpClientUtils.getJson(
                    weixinConfig.getTokenUrl(), "appid", appid, "secret",
                    secret, "code", code, "grant_type", "authorization_code"
            );

            log.debug("微信返回结果:" + json);
            if (!json.has("errcode")) {
                String refresh_token = json.get("refresh_token").asText();

                JsonNode refreshJson = HttpClientUtils.getJson(
                        weixinConfig.getRefresstokenUrl(), "appid", appid,
                        "grant_type", "refresh_token", "refresh_token", refresh_token
                );
                if (!json.has("errcode")) {

                    String userJsonStr = HttpClientUtils.get(
                            weixinConfig.getGetuserUrl(),
                            "access_token", refreshJson.get("access_token").asText(),
                            "openid", json.get("openid").asText(), "lang", "zh_CN"
                    );
                    log.debug("微信返回结果:" + userJsonStr);

                    WeixinUserInfo userInfo = JsonUtils.deserialize(userJsonStr, WeixinUserInfo.class);
                    if (userInfo.getErrcode() == null) {
                        //
                        log.debug("获取微信用户信息成功! 开始自动登录");
                        return Optional.of(userInfo);
                        //登录成功,应该跳转一次页面甩掉该死code 参数
                        //甩掉code 后会出现 Cookie 写失败的问题
//                    String referer = request.getHeader("Referer");
//                    String url = request.getRequestURL().toString();
//                    if(url.equals(referer)){//防止循环跳转
//                    return Optional.of(accountObject);
//                    }
//                    response.sendRedirect(url);
//                    return null;
                    } else {
                        log.debug("微信返回错误:" + userJsonStr);
                    }

                } else {
                    log.debug("微信返回错误:" + refreshJson);
                }

            } else {
                //code 参数错误,那么重新跳转微信授权页面
                gotoWeixin(request, response);
                return null;
            }
        }
        return Optional.empty();
    }


    public Map.Entry<WeixinUserInfo,String> checkAppWeixin(String codeJson) throws Exception {
        log.debug("微信登录请求,未登录!");
        String appid;
        String secret;
        appid = weixinConfig.getAppAppID();
        secret = weixinConfig.getAppAppsecret();


        JsonNode codeObj = JsonUtils.deserialize(codeJson);

        String code = codeObj.get("code").asText();
        JsonNode json = HttpClientUtils.getJson(
                weixinConfig.getTokenUrl(), "appid", appid, "secret",
                secret, "code", code, "grant_type", "authorization_code"
        );

        log.debug("微信返回结果:" + json);
        String msg;
        if (!json.has("errcode")) {
            String refresh_token = json.get("refresh_token").asText();

            JsonNode refreshJson = HttpClientUtils.getJson(
                    weixinConfig.getRefresstokenUrl(), "appid", appid,
                    "grant_type", "refresh_token", "refresh_token", refresh_token
            );
            if (!json.has("errcode")) {

                String userJsonStr = HttpClientUtils.get(
                        weixinConfig.getGetuserUrl(),
                        "access_token", refreshJson.get("access_token").asText(),
                        "openid", json.get("openid").asText(), "lang", "zh_CN"
                );
                log.debug("微信返回结果:" + userJsonStr);

                WeixinUserInfo userInfo = JsonUtils.deserialize(userJsonStr, WeixinUserInfo.class);
                if (userInfo.getErrcode() == null) {
                    //
                    log.debug("获取微信用户信息成功! 开始自动登录");

                    return new AbstractMap.SimpleImmutableEntry<>(userInfo, null);
                    //登录成功,应该跳转一次页面甩掉该死code 参数
                    //甩掉code 后会出现 Cookie 写失败的问题
//                    String referer = request.getHeader("Referer");
//                    String url = request.getRequestURL().toString();
//                    if(url.equals(referer)){//防止循环跳转
//                    return Optional.of(accountObject);
//                    }
//                    response.sendRedirect(url);
//                    return null;
                } else {
                    msg = "微信登录错误,userJsonStr:" + userJsonStr;
                    log.debug("微信返回错误:" + userJsonStr);
                }

            } else {
                log.debug("微信返回错误:" + refreshJson);
                msg = "微信登录错误,refreshJson:" + refreshJson;
            }

        } else {
            //code 参数错误,那么重新跳转微信授权页面
            log.debug("code 参数错误,那么重新跳转微信授权页面:");
            msg = "微信登录 code 错误,检查配置";
        }
        return new AbstractMap.SimpleImmutableEntry<WeixinUserInfo, String>(null, msg);
    }

    private void gotoWeixin(HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append(weixinConfig.getAuthorizeUrl());
        sb.append('?');
        sb.append("appid=");
        sb.append(weixinConfig.getAppID());
        sb.append("&redirect_uri=");

        String curUrl = weixinConfig.getSiteUrl() + request.getRequestURI();
        if (request.getQueryString() != null) {
            Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());
            //http://www.tashenghuo.com.cn/wap/topic/detail.html?id=1445&from=timeline&isappinstalled=0&code=011efLMJ1lcUVa0rppMJ1fFNMJ1efLMz&state=STATE
            parameterMap.remove("from");
            parameterMap.remove("isappinstalled");
            parameterMap.remove("code");
            parameterMap.remove("state");

            URIBuilder uriBuilder = new URIBuilder(curUrl);
            uriBuilder.setCharset(CHARSET);

            parameterMap.forEach((k, vs) -> {
                if (vs != null) {
                    for (String v : vs) {
                        uriBuilder.addParameter(k, v);
                    }
                }
            });

            curUrl = uriBuilder.toString();
        }
        sb.append(URLEncoder.encode(curUrl, "utf-8"));
        sb.append("&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");

        String url = sb.toString();
        log.info("尝试跳转到微信授权页面!{}", url);
        response.sendRedirect(url);
    }
}
