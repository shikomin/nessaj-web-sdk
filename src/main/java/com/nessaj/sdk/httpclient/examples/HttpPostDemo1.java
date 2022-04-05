package com.nessaj.sdk.httpclient.examples;

import com.nessaj.sdk.httpclient.common.enums.HttpMethod;
import com.nessaj.sdk.httpclient.core.HttpRequest;
import com.nessaj.sdk.httpclient.core.HttpResponse;
import com.nessaj.sdk.httpclient.core.Sender;
import com.nessaj.sdk.httpclient.core.impl.HttpSender;
import org.apache.http.client.config.RequestConfig;

import java.util.LinkedHashMap;

/**
 * http post 方法示例
 * @author keming
 * @Date 2022/02/27 15:33
 */
public class HttpPostDemo1 {

    public static void main(String[] args) {
        Sender sender = new HttpSender();

        LinkedHashMap<String, String> header = new LinkedHashMap<>();
        LinkedHashMap<Object, Object> params = new LinkedHashMap<>();

        header.put("Content-Type", "application/json;charset=utf8");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");

        params.put("mid", "5");
        params.put("mname", "eng");
        params.put("minfo", "Anglo-Saxon");
        params.put("status", "uploaded");

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true).build();

        HttpRequest httpRequest = HttpRequest.custom()
                .setMethod(HttpMethod.POST)
                .setUrl("http://localhost:9097/module")
                .setHeader(header)
                .setParams(params)
                .setRequestConfig(requestConfig)
                .build();
        HttpResponse httpResponse = (HttpResponse) sender.send(httpRequest);
        System.out.println(httpResponse.toString());
    }

}
