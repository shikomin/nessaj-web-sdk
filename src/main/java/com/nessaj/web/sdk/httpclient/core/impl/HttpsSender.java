package com.nessaj.web.sdk.httpclient.core.impl;

import com.nessaj.web.sdk.httpclient.common.constants.ErrorMessage;
import com.nessaj.web.sdk.httpclient.common.constants.TlsVersion;
import com.nessaj.web.sdk.httpclient.core.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.security.KeyManagementException;
import java.security.KeyStore;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * @author keming
 * @Date 2022/02/25 12:03
 */
public class HttpsSender extends PlainRequestHandler implements Sender {

    private static Logger logger = Logger.getLogger(HttpsSender.class);

    @Override
    public Response send(Request request) {
        HttpsRequest httpsRequest = (HttpsRequest) request;
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(initKeyManager(httpsRequest), initTrustManager(httpsRequest), null);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            logger.error(ErrorMessage.SEND_HTTPS_REQUEST_EXCEPTION, e);
        }
        //设置规则限制
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                new String[]{TlsVersion.TLS_V1, TlsVersion.TLS_V1_1, TlsVersion.TLS_V1_2},
                null,
                new HttpsHostnameVerifier());
//        注册
//        Registry<ConnectionSocketFactory> socketFactoryRegistry = null;
//        socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", PlainConnectionSocketFactory.INSTANCE)
//                .register("https", sslConnectionSocketFactory).build();
//        池化管理
//        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        CloseableHttpClient httpClient;
        httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
        return handle(httpClient, request);
    }

    /**
     * 初始化Key Manager
     *
     * @return
     */
    public KeyManager[] initKeyManager(HttpsRequest request) {
        KeyManagerFactory keyFactory = null;

        try {
            keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            KeyStore keystore = KeyStore.getInstance(request.getKeystoreType());
            keystore.load(new FileInputStream(new File(request.getKeystorePath())), null);
            keyFactory.init(keystore, request.getKeystorePassword().toCharArray());
        } catch (NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException | KeyStoreException | IOException e) {
            logger.error(ErrorMessage.SEND_HTTPS_REQUEST_EXCEPTION, e);
        }
        return keyFactory.getKeyManagers();
    }

    /**
     * 初始化Trust Manager
     *
     * @return
     */
    public TrustManager[] initTrustManager(HttpsRequest request) {
        TrustManagerFactory trustFactory = null;
        try {
            trustFactory = TrustManagerFactory.getInstance("SunX509");
            KeyStore truststore = KeyStore.getInstance(request.getTruststoreType());
            truststore.load(new FileInputStream((request.getTruststorePath())), request.getTruststorePassword().toCharArray());
            trustFactory.init(truststore);
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            logger.error(ErrorMessage.SEND_HTTPS_REQUEST_EXCEPTION, e);
        }
        return trustFactory.getTrustManagers();
    }

}
