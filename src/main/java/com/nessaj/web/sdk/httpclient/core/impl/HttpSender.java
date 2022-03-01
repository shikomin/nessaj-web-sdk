package com.nessaj.web.sdk.httpclient.core.impl;

import com.nessaj.web.sdk.httpclient.core.Request;
import com.nessaj.web.sdk.httpclient.core.Response;
import com.nessaj.web.sdk.httpclient.core.PlainRequestHandler;
import com.nessaj.web.sdk.httpclient.core.Sender;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author keming
 * @Date 2022/02/25 21:42
 */
public class HttpSender extends PlainRequestHandler implements Sender {

    @Override
    public Response send(Request request) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        return handle(httpClient, request);
    }

}
