package com.nessaj.web.sdk.httpclient.examples;

import com.nessaj.web.sdk.httpclient.common.enums.HttpMethod;
import com.nessaj.web.sdk.httpclient.core.HttpRequest;
import com.nessaj.web.sdk.httpclient.core.HttpResponse;
import com.nessaj.web.sdk.httpclient.core.builder.HttpRequestBuilder;
import com.nessaj.web.sdk.httpclient.core.interfaces.Sender;
import com.nessaj.web.sdk.httpclient.core.interfaces.impl.HttpSender;
import org.apache.http.client.config.RequestConfig;

import java.util.LinkedHashMap;

/**
 * @author keming
 * @Date 2022/02/27 12:45
 */
public class HttpGetHasParamsDemo {

    public static void main(String[] args) {
        Sender sender = new HttpSender();
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("mid", "1");
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true).build();
        HttpRequest httpRequest = httpRequestBuilder.setMethod(HttpMethod.GET)
                .setUrl("http://localhost:9097/module")
                .setHeader(null)
                .setParams(params)
                .setRequestConfig(requestConfig)
                .build();
        HttpResponse httpResponse = (HttpResponse) sender.send(httpRequest);
        System.out.println(httpResponse.toString());
    }

}
