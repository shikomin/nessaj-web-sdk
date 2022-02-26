package com.nessaj.web.sdk.httpclient.examples;

import com.nessaj.web.sdk.httpclient.common.enums.HttpMethod;
import com.nessaj.web.sdk.httpclient.core.HttpResponse;
import com.nessaj.web.sdk.httpclient.core.Request;
import com.nessaj.web.sdk.httpclient.core.builder.HttpRequestBuilder;
import com.nessaj.web.sdk.httpclient.core.interfaces.Sender;
import com.nessaj.web.sdk.httpclient.core.interfaces.impl.HttpSender;
import org.apache.http.client.config.RequestConfig;

/**
 * @author keming
 * @Date 2022/02/26 20:09
 */
public class HttpGetDemo1 {

    public static void main(String[] args) {
        Sender sender = new HttpSender();
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true).build();
        Request request = requestBuilder.setUrl("http://localhost:9097/healthcheck")
                .setMethod(HttpMethod.GET)
                .setHeader(null)
                .setParams(null)
                .setRequestConfig(requestConfig)
                .build();
        HttpResponse response = (HttpResponse) sender.send(request);
        System.out.println(response.toString());
    }

}
