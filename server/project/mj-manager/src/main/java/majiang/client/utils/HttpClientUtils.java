package majiang.client.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zuoge85 on 15/5/30.
 */
public final class HttpClientUtils {
    private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);
    public static final Charset CHARSET = Charset.forName("utf-8");


    public static JsonNode getJson(String uri, Object... params) {
        String json = get(uri, params);
        return JsonUtils.deserialize(json);
    }

    public static String get(String uri, Object... params) {
        try {
            URIBuilder uriBuilder = new URIBuilder(uri);
            List<NameValuePair> paramsList = transform(params);

            String url = uriBuilder.setCharset(CHARSET).addParameters(paramsList).toString();
            log.debug("get url : {}", url);
            HttpResponse response = Request.Get(url).execute().returnResponse();
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("请求失败！" + response.toString());
            }
            log.debug("response:{}", response);
            return IOUtils.toString(response.getEntity().getContent(), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String post(String uri, Object paramsObject, Object... params) {
        try {
            URIBuilder uriBuilder = new URIBuilder(uri);
            List<NameValuePair> paramsList = transform(params);

            if (log.isDebugEnabled()) {
                String url = uriBuilder.setCharset(CHARSET).addParameters(paramsList).toString();
                log.debug("post url : {}", url);
            }
            HttpResponse response = Request.Post(uri).bodyForm(
                    paramsList, CHARSET
            ).execute().returnResponse();

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("请求失败！" + response.toString());
            }
            HttpEntity entity = response.getEntity();
            String body = IOUtils.toString(entity.getContent(), CHARSET);
            log.debug("response:{},{}", response, body);
            return body;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static List<NameValuePair> transform(Object[] params) {
        try {
            List<NameValuePair> list = new ArrayList<>();

            if (params != null) {
                if (params.length % 2 > 0) {
                    throw new RuntimeException("参数必须是偶数");
                }
                for (int i = 0; i < params.length; i++) {
                    String key = String.valueOf(params[i]);
                    Object param = params[++i];
                    paramHandler(list, key, param);
                }
            }
            return list;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void paramHandler(List<NameValuePair> list, String key, Object param) {
        if (param != null && param.getClass().isArray()) {
            Object[] paramArray = (Object[]) param;
            for (Object item : paramArray) {
                String value = String.valueOf(item);
                list.add(new BasicNameValuePair(key, value));
            }
        } else {
            String value = String.valueOf(param);
            list.add(new BasicNameValuePair(key, value));
        }
    }

    public static byte[] down(String headimgurl) throws IOException {
        HttpResponse response = Request.Get(headimgurl).execute().returnResponse();
        return IOUtils.toByteArray(response.getEntity().getContent());
    }
}
