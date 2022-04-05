package com.nessaj.sdk.elasticsearch.factory;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author keming
 * @Date 2022/03/23 12:56
 */
public class RestHighLevelClientFactory {

    private static RestHighLevelClientFactory esClientFactory = null;

    private RestHighLevelClientFactory() {
    }

    public static RestHighLevelClientFactory getInstance() {
        if (esClientFactory == null) {
            synchronized (RestHighLevelClientFactory.class) {
                if (esClientFactory == null) {
                    esClientFactory = new RestHighLevelClientFactory();
                }
            }
        }
        return esClientFactory;
    }

    public RestHighLevelClient build() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(RestClientConfig.getUserName(), RestClientConfig.getPassword()));
        RestHighLevelClient client = new RestHighLevelClient(
                getRestClientBuilder().setHttpClientConfigCallback((httpClientBuilder) -> {
                    httpClientBuilder.disableAuthCaching();
                    // ssl context： httpClientBuilder.setSSLContext();
                    // return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider).setDefaultIOReactorConfig(
                    // IOReactorConfig.custom().setIoThreadCount(1).build();
                    return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                })
        );
        return client;
    }

    private RestClientBuilder getRestClientBuilder() {
        String[] hosts = RestClientConfig.getHosts();
        int[] ports = RestClientConfig.getPorts();
        String scheme = RestClientConfig.getScheme();
        HttpHost[] httpHosts = new HttpHost[hosts.length];
        for (int i = 0; i < hosts.length; i++) {
            httpHosts[i] = new HttpHost(hosts[i], ports[i], scheme);
        }
        return RestClient.builder(httpHosts);
    }

}
