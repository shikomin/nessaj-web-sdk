package com.nessaj.web.sdk.httpclient.examples;

import com.nessaj.web.sdk.httpclient.common.enums.HttpMethod;
import com.nessaj.web.sdk.httpclient.core.HttpResponse;
import com.nessaj.web.sdk.httpclient.core.HttpsRequest;
import com.nessaj.web.sdk.httpclient.core.Sender;
import com.nessaj.web.sdk.httpclient.core.impl.HttpsSender;
import org.apache.http.client.config.RequestConfig;

/**
 * @author keming
 * @Date 2022/03/01 21:39
 */
public class HttpGetWithSSL {

    public static void main(String[] args) {
        Sender sender = new HttpsSender();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true).build();
        HttpsRequest request = HttpsRequest.custom()
                .setUrl("https://localhost:9097/healthcheck")
                .setMethod(HttpMethod.GET)
                .setHeader(null)
                .setParams(null)
                .setRequestConfig(requestConfig)
                .setKeystorePath("E:/Nessaj/ssl/nessaj-keystore.jks").setKeystoreType("jks").setKeystorePassword("Nessaj@123")
                .setTruststorePath("E:/Nessaj/ssl/nessaj-truststore.jks").setTruststoreType("jks").setTruststorePassword("Nessaj@123")
                .build();
        HttpResponse httpResponse = (HttpResponse) sender.send(request);
        System.out.println(httpResponse.toString());
    }

}
