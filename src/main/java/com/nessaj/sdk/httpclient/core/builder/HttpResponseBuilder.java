package com.nessaj.sdk.httpclient.core.builder;

import com.nessaj.sdk.httpclient.core.HttpResponse;

/**
 * @author keming
 * @Date 2022/02/26 19:53
 */
public class HttpResponseBuilder {

    private HttpResponse httpResponse;

    public HttpResponseBuilder() {
        this.httpResponse = new HttpResponse();
    }

    public static HttpResponseBuilder create() {
        return new HttpResponseBuilder();
    }

    public HttpResponseBuilder setStatusLine(String statusLine) {
        httpResponse.setStatusLine(statusLine);
        return this;
    }

    public HttpResponseBuilder setContentLength(long contentLength) {
        httpResponse.setContentLength(contentLength);
        return this;
    }

    public HttpResponseBuilder setContent(String content) {
        httpResponse.setContent(content);
        return this;
    }

    public HttpResponse build() {
        if (httpResponse == null)
            this.httpResponse = new HttpResponse();
        return httpResponse;
    }

}
