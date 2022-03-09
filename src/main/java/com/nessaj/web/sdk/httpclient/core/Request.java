package com.nessaj.web.sdk.httpclient.core;

import com.nessaj.web.sdk.httpclient.common.enums.HttpMethod;
import com.sun.istack.internal.NotNull;
import org.apache.http.client.config.RequestConfig;

import java.io.File;
import java.util.LinkedHashMap;

/**
 * @author keming
 * @Date 2022/02/25 21:42
 */
public abstract class Request {

    private String url;

    private HttpMethod method;

    private LinkedHashMap<String, String> header;

    private LinkedHashMap<Object, Object> params;

    private RequestConfig requestConfig;

    private File multipartData;

    public boolean hasHeader() {
        return (this.header != null) && (!this.header.isEmpty());
    }

    public boolean hasParams() {
        return (this.params != null) && (!this.params.isEmpty());
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public LinkedHashMap<String, String> getHeader() {
        return header;
    }

    public void setHeader(LinkedHashMap<String, String> header) {
        this.header = header;
    }

    public LinkedHashMap<Object, Object> getParams() {
        return params;
    }

    public void setParams(LinkedHashMap<Object, Object> params) {
        this.params = params;
    }

    public RequestConfig getRequestConfig() {
        return requestConfig;
    }

    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public File getMultipartData() {
        return multipartData;
    }

    public void setMultipartData(File multipartData) {
        this.multipartData = multipartData;
    }

}
