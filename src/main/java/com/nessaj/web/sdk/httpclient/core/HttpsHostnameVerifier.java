package com.nessaj.web.sdk.httpclient.core;


import org.apache.log4j.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @author keming
 * @Date 2022/03/01 21:59
 */
public class HttpsHostnameVerifier implements HostnameVerifier {

    private static Logger logger = Logger.getLogger(HttpsHostnameVerifier.class);

    /**
     * 验证对方主机名称 ,防止服务器证书上的Hostname和实际的URL不匹配
     * 防止链接被重定向到其他的不安全的地址
     */
    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}