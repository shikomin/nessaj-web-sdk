package com.nessaj.sdk.httpclient.examples;

import com.nessaj.sdk.httpclient.common.enums.HttpMethod;
import com.nessaj.sdk.httpclient.core.HttpRequest;
import com.nessaj.sdk.httpclient.core.HttpResponse;
import com.nessaj.sdk.httpclient.core.Sender;
import com.nessaj.sdk.httpclient.core.impl.HttpSender;
import org.apache.http.client.config.RequestConfig;

/**
 * http get 方法示例
 * @author keming
 * @Date 2022/02/26 20:09
 */
public class HttpGetNoParamsDemo {

    public static void main(String[] args) {
        Sender sender = new HttpSender();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true).build();
        HttpRequest httpRequest = HttpRequest.custom()
                .setUrl("http://localhost:9097/healthcheck")
                .setMethod(HttpMethod.GET)
                .setHeader(null)
                .setParams(null)
                .setRequestConfig(requestConfig)
                .build();
        HttpResponse response = (HttpResponse) sender.send(httpRequest);
        System.out.println(response.toString());
    }

}
