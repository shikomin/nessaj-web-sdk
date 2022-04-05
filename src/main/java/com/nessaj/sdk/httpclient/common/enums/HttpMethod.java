package com.nessaj.sdk.httpclient.common.enums;

/**
 * @author keming
 * @Date 2022/02/25 19:00
 */
public enum HttpMethod {

    GET("get"), POST("post"), DELETE("delete"), PUT("put"), HEAD("head"), OPTIONS("options");

    private final String method;

    HttpMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

}
