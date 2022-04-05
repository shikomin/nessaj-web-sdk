package com.nessaj.sdk.httpclient.core;


import com.nessaj.sdk.httpclient.core.builder.HttpRequestBuilder;

/**
 * @author keming
 * @Date 2022/02/25 21:56
 */
public class HttpRequest extends Request {

    public HttpRequest() {
    }

    public static HttpRequestBuilder custom() {
        return HttpRequestBuilder.create();
    }

}
