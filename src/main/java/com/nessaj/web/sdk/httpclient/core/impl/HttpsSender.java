package com.nessaj.web.sdk.httpclient.core.impl;

import com.nessaj.web.sdk.httpclient.core.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
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
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException", e);
        } catch (KeyManagementException e) {
            logger.error("KeyManagementException", e);
        }
        //设置规则限制
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"},
                null,
                new HttpsHostnameVerifier());
        //注册
//        Registry<ConnectionSocketFactory> socketFactoryRegistry = null;
//        socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", PlainConnectionSocketFactory.INSTANCE)
//                .register("https", sslConnectionSocketFactory).build();
        //池化管理
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
            KeyStore keystore = KeyStore.getInstance("jks");
            keystore.load(new FileInputStream(new File("E:/Nessaj/ssl/nessaj-keystore.jks")), null);
            keyFactory.init(keystore, "Nessaj@123".toCharArray());
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException", e);
        } catch (UnrecoverableKeyException e) {
            logger.error("UnrecoverableKeyException", e);
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (CertificateException e) {
            logger.error("CertificateException", e);
        } catch (KeyStoreException e) {
            logger.error("KeyStoreException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
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
            truststore.load(new FileInputStream(new File(request.getTruststorePath())), request.getTruststorePassword().toCharArray());
            trustFactory.init(truststore);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trustFactory.getTrustManagers();
    }

}
