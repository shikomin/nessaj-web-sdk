package com.nessaj.web.sdk.httpclient.examples;


import com.nessaj.web.sdk.httpclient.common.enums.HttpMethod;
import com.nessaj.web.sdk.httpclient.core.HttpResponse;
import com.nessaj.web.sdk.httpclient.core.HttpsRequest;
import com.nessaj.web.sdk.httpclient.core.Sender;
import com.nessaj.web.sdk.httpclient.core.impl.HttpsSender;
import org.apache.http.client.config.RequestConfig;

import java.io.File;

/**
 * @author keming
 * @Date 2022/03/09 22:17
 */
public class UploadDemoWithSSL {

    public static void main(String[] args) {
        Sender sender = new HttpsSender();
        HttpsRequest request = HttpsRequest.custom()
                .setUrl("https://localhost:9095/module")
                .setMethod(HttpMethod.POST)
                .setHeader(null)
                .setParams(null)
                .setMultipartData(new File("E:/etc/nessaj-web-local/orig/rbk.jpg"))
                .setRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setConnectionRequestTimeout(5000)
                        .setSocketTimeout(5000)
                        .setRedirectsEnabled(true).build())
                .setKeystorePath("E:/Nessaj/ssl/nessaj-keystore.jks").setKeystoreType("jks").setKeystorePassword("Nessaj@123")
                .setTruststorePath("E:/Nessaj/ssl/nessaj-truststore.jks").setTruststoreType("jks").setTruststorePassword("Nessaj@123")
                .build();
        HttpResponse httpResponse = (HttpResponse) sender.send(request);
        System.out.println(httpResponse.toString());
    }

}
