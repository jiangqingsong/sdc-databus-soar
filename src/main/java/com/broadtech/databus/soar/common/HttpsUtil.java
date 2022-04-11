package com.broadtech.databus.soar.common;
/*
 * @author herunchen
 * @date 2021/9/26 9:37 上午
 * @describe
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HttpsUtil {

    private static PoolingHttpClientConnectionManager connectionManager;

    private static RequestConfig requestConfig;

    private static final int MAX_TIMEOUT = 10000;

    static {
        //创建连接池
        connectionManager = new PoolingHttpClientConnectionManager();
        //创建连接池大小
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(connectionManager.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();

        //连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        //读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);

        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        // 在提交请求之前 测试连接是否可用
        configBuilder.setStaleConnectionCheckEnabled(true);
        requestConfig = configBuilder.build();
    }

    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private static CloseableHttpClient createSSLClientDefault() {
        SSLContextBuilder builder = new SSLContextBuilder();
        CloseableHttpClient httpclient = null;
        CookieStore cookieStore = new BasicCookieStore();
        try {
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(),
                    NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", new PlainConnectionSocketFactory()).register("https", sslConnectionSocketFactory)
                    .build();


            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(100);
            httpclient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).setConnectionManager(cm)
                    .setDefaultCookieStore(cookieStore)
                    .build();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpclient;
    }


    /**
     * post请求 参数key value方式
     * @param headers
     * @param apiUrl
     * @param params
     * @return
     */
    public static String doPost(Map<String, String> headers, String apiUrl, Map<String, Object> params,CloseableHttpClient httpClient) {

        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            //拼接参数
            StringBuilder paramString = new StringBuilder(apiUrl);

            if (params.size() > 0){
                paramString.append("?");
                for (String paramName : params.keySet()){
                    paramString.append(paramName + "=" + params.get(paramName) + "&");
                }
            }

            HttpPost httpPost = new HttpPost(paramString.toString());


            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }

            httpPost.setConfig(requestConfig);

            response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }


    /**
     * post请求 json参数
     *
     * @param headers 请求头
     * @param apiUrl  地址
     * @param json    参数
     * @return
     */
    public static String doPost(Map<String, String> headers, String apiUrl, String json) {
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;


        try {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json, "UTF-8");// 解决中文乱码问题
            // stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            //设置超时
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000)
                    .setSocketTimeout(5000).build();
            httpPost.setConfig(requestConfig);
            response = createSSLClientDefault().execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                log.error("调用设备登录异常");
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }


    /**
     * 发送post请求 不设置header
     *
     * @param apiUrl 地址
     * @param json   参数
     * @return
     */
    public static String doPost(String apiUrl, String json) {
        return doPost(new HashMap<>(), apiUrl, json);
    }



    /**
     * post请求 参数key value 无header方式
     * @param apiUrl
     * @param params
     * @return
     */
    public static Map<String,Object> doPost(String apiUrl, Map<String, Object> params) {

        CloseableHttpResponse response = null;
        String httpStr = null;
        Map<String,Object> message = new HashMap<>();
        try {
            //拼接参数
            StringBuilder paramString = new StringBuilder(apiUrl);
            if (params.size() > 0){
                paramString.append("?");
                for (String paramName : params.keySet()){
                    paramString.append(paramName + "=" + params.get(paramName) + "&");
                }
            }

            //发送请求
            HttpPost httpPost = new HttpPost(paramString.toString());
            httpPost.setConfig(requestConfig);

            CloseableHttpClient httpClient = createSSLClientDefault();
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }

            HttpEntity entity = response.getEntity();

            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");

            message.put("httpClient",httpClient);
            message.put("httpStr",httpStr);
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
        }
        return message;
    }
}
