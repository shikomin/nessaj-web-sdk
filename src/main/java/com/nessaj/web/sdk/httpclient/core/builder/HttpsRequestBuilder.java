package com.nessaj.web.sdk.httpclient.core.builder;

import com.nessaj.web.sdk.httpclient.common.enums.HttpMethod;
import com.nessaj.web.sdk.httpclient.core.HttpsRequest;
import org.apache.http.client.config.RequestConfig;

import java.io.File;
import java.util.LinkedHashMap;

/**
 * @author keming
 * @Date 2022/03/01 22:12
 */
public class HttpsRequestBuilder {

    private HttpsRequest httpsRequest;

    public HttpsRequestBuilder() {
        this.httpsRequest = new HttpsRequest();
    }

    public static HttpsRequestBuilder create() {
        return new HttpsRequestBuilder();
    }


    public HttpsRequestBuilder setUrl(String url) {
        this.httpsRequest.setUrl(url);
        return this;
    }

    public HttpsRequestBuilder setMethod(HttpMethod method) {
        this.httpsRequest.setMethod(method);
        return this;
    }

    public HttpsRequestBuilder setHeader(LinkedHashMap<String, String> header) {
        this.httpsRequest.setHeader(header);
        return this;
    }

    public HttpsRequestBuilder setParams(LinkedHashMap<Object, Object> params) {
        this.httpsRequest.setParams(params);
        return this;
    }

    public HttpsRequestBuilder setRequestConfig(RequestConfig requestConfig) {
        this.httpsRequest.setRequestConfig(requestConfig);
        return this;
    }

    public HttpsRequestBuilder setTlsVersion(String tlsVersion) {
        this.httpsRequest.setTlsVersion(tlsVersion);
        return this;
    }

    public HttpsRequestBuilder setKeystorePath(String keyStorePath) {
        this.httpsRequest.setKeystorePath(keyStorePath);
        return this;
    }

    public HttpsRequestBuilder setKeystoreType(String keystoreType) {
        this.httpsRequest.setKeystoreType(keystoreType);
        return this;
    }

    public HttpsRequestBuilder setKeystorePassword(String keystorePassword) {
        this.httpsRequest.setKeystorePassword(keystorePassword);
        return this;
    }

    public HttpsRequestBuilder setTruststorePath(String truststorePath) {
        this.httpsRequest.setTruststorePath(truststorePath);
        return this;
    }

    public HttpsRequestBuilder setTruststoreType(String truststoreType) {
        this.httpsRequest.setTruststoreType(truststoreType);
        return this;
    }

    public HttpsRequestBuilder setTruststorePassword(String truststorePassword) {
        this.httpsRequest.setTruststorePassword(truststorePassword);
        return this;
    }

    public HttpsRequestBuilder setMultipartData(File multipartData) {
        this.httpsRequest.setMultipartData(multipartData);
        return this;
    }

    public HttpsRequest build() {
        if (this.httpsRequest == null)
            this.httpsRequest = new HttpsRequest();
        return this.httpsRequest;
    }

}
