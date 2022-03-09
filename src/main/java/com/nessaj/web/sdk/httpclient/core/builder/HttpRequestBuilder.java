package com.nessaj.web.sdk.httpclient.core.builder;

import com.nessaj.web.sdk.httpclient.common.enums.HttpMethod;
import com.nessaj.web.sdk.httpclient.core.HttpRequest;
import org.apache.http.client.config.RequestConfig;

import java.io.File;
import java.util.LinkedHashMap;

/**
 * @author keming
 * @Date 2022/02/26 20:09
 */
public class HttpRequestBuilder {

    private HttpRequest httpRequest;

    public HttpRequestBuilder() {
        this.httpRequest = new HttpRequest();
    }

    public static HttpRequestBuilder create() {
        return new HttpRequestBuilder();
    }

    public HttpRequestBuilder setUrl(String url) {
        this.httpRequest.setUrl(url);
        return this;
    }

    public HttpRequestBuilder setMethod(HttpMethod method) {
        this.httpRequest.setMethod(method);
        return this;
    }

    public HttpRequestBuilder setHeader(LinkedHashMap<String, String> header) {
        this.httpRequest.setHeader(header);
        return this;
    }

    public HttpRequestBuilder setParams(LinkedHashMap<Object, Object> params) {
        this.httpRequest.setParams(params);
        return this;
    }

    public HttpRequestBuilder setRequestConfig(RequestConfig requestConfig) {
        this.httpRequest.setRequestConfig(requestConfig);
        return this;
    }

    public HttpRequestBuilder setMultipartData(File multipartData) {
        this.httpRequest.setMultipartData(multipartData);
        return this;
    }

    public HttpRequest build() {
        if (httpRequest == null)
            this.httpRequest = new HttpRequest();
        return httpRequest;
    }

}
