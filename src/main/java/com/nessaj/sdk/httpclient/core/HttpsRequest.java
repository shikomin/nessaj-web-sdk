package com.nessaj.sdk.httpclient.core;

import com.nessaj.sdk.httpclient.core.builder.HttpsRequestBuilder;

/**
 * @author keming
 * @Date 2022/03/01 22:09
 */
public class HttpsRequest extends Request {

    private String tlsVersion;

    private String keystorePath;

    private String keystoreType;

    private String keystorePassword;

    private String truststorePath;

    private String truststoreType;

    private String truststorePassword;

    public HttpsRequest() {
    }

    public static HttpsRequestBuilder custom() {
        return HttpsRequestBuilder.create();
    }

    public String getTlsVersion() {
        return tlsVersion;
    }

    public void setTlsVersion(String tlsVersion) {
        this.tlsVersion = tlsVersion;
    }

    public String getKeystorePath() {
        return keystorePath;
    }

    public void setKeystorePath(String keystorePath) {
        this.keystorePath = keystorePath;
    }

    public String getKeystoreType() {
        return keystoreType;
    }

    public void setKeystoreType(String keystoreType) {
        this.keystoreType = keystoreType;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public void setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
    }

    public String getTruststorePath() {
        return truststorePath;
    }

    public void setTruststorePath(String truststorePath) {
        this.truststorePath = truststorePath;
    }

    public String getTruststoreType() {
        return truststoreType;
    }

    public void setTruststoreType(String truststoreType) {
        this.truststoreType = truststoreType;
    }

    public String getTruststorePassword() {
        return truststorePassword;
    }

    public void setTruststorePassword(String truststorePassword) {
        this.truststorePassword = truststorePassword;
    }
}
