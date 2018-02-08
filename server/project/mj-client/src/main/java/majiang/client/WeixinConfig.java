package majiang.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zuoge85@gmail.com on 2016/12/7.
 */
@Component
@ConfigurationProperties(prefix = "weixin")
public class WeixinConfig {
    private String appAppID;
    private String appAppsecret;

    private String appID;
    private String appsecret;
    private String clienttokenUrl;
    private String jsticketUrl;

    private String authorizeUrl;

    private String tokenUrl;

    private String refresstokenUrl;

    private String getuserUrl;

    private String siteUrl;


    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getClienttokenUrl() {
        return clienttokenUrl;
    }

    public void setClienttokenUrl(String clienttokenUrl) {
        this.clienttokenUrl = clienttokenUrl;
    }

    public String getJsticketUrl() {
        return jsticketUrl;
    }

    public void setJsticketUrl(String jsticketUrl) {
        this.jsticketUrl = jsticketUrl;
    }

    public String getAuthorizeUrl() {
        return authorizeUrl;
    }

    public void setAuthorizeUrl(String authorizeUrl) {
        this.authorizeUrl = authorizeUrl;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getRefresstokenUrl() {
        return refresstokenUrl;
    }

    public void setRefresstokenUrl(String refresstokenUrl) {
        this.refresstokenUrl = refresstokenUrl;
    }

    public String getGetuserUrl() {
        return getuserUrl;
    }

    public void setGetuserUrl(String getuserUrl) {
        this.getuserUrl = getuserUrl;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getAppAppID() {
        return appAppID;
    }

    public void setAppAppID(String appAppID) {
        this.appAppID = appAppID;
    }

    public String getAppAppsecret() {
        return appAppsecret;
    }

    public void setAppAppsecret(String appAppsecret) {
        this.appAppsecret = appAppsecret;
    }

    @Override
    public String toString() {
        return "WeixinConfig{" +
                "appAppID='" + appAppID + '\'' +
                ", appAppsecret='" + appAppsecret + '\'' +
                ", appID='" + appID + '\'' +
                ", appsecret='" + appsecret + '\'' +
                ", clienttokenUrl='" + clienttokenUrl + '\'' +
                ", jsticketUrl='" + jsticketUrl + '\'' +
                ", authorizeUrl='" + authorizeUrl + '\'' +
                ", tokenUrl='" + tokenUrl + '\'' +
                ", refresstokenUrl='" + refresstokenUrl + '\'' +
                ", getuserUrl='" + getuserUrl + '\'' +
                ", siteUrl='" + siteUrl + '\'' +
                '}';
    }
}
