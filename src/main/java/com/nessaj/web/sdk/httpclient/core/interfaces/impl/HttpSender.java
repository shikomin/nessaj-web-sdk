package com.nessaj.web.sdk.httpclient.core.interfaces.impl;

import com.alibaba.fastjson.JSON;
import com.nessaj.web.sdk.httpclient.common.enums.HttpMethod;
import com.nessaj.web.sdk.httpclient.core.HttpRequest;
import com.nessaj.web.sdk.httpclient.core.HttpResponse;
import com.nessaj.web.sdk.httpclient.core.Request;
import com.nessaj.web.sdk.httpclient.core.Response;
import com.nessaj.web.sdk.httpclient.core.builder.HttpResponseBuilder;
import com.nessaj.web.sdk.httpclient.core.interfaces.Sender;
import com.nessaj.web.sdk.httpclient.utils.URLUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author keming
 * @Date 2022/02/25 21:42
 */
public class HttpSender implements Sender {

    @Override
    public Response send(Request request) {
        HttpRequest httpRequest = (HttpRequest) request;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpMethod method = request.getMethod();
        HttpResponse response = null;
        switch (method) {
            case GET:
                response = httpGet(httpClient, httpRequest);
                break;
            case POST:
                response = httpPost(httpClient, httpRequest);
                break;
            case PUT:
                response = httpPut(httpClient, httpRequest);
                break;
            case DELETE:
                response = httpDelete(httpClient, httpRequest);
        }
        return response;
    }

    /**
     * http get方法
     *
     * @param httpClient
     * @param request
     * @return
     */
    private HttpResponse httpGet(CloseableHttpClient httpClient, HttpRequest request) {
        String url = request.getUrl();
        if (request.hasParams()) {
            url = URLUtil.addParams2URL(url, request.getParams());
        }
        HttpGet httpGet = new HttpGet(url);
        if (request.hasHeader()) {
            httpGet = (HttpGet) setHeader(httpGet, request.getHeader());
        }
        httpGet.setConfig(request.getRequestConfig());
        HttpResponse httpResponse = execute(httpClient, httpGet);
        return httpResponse;
    }

    /**
     * @param httpClient
     * @param request
     * @return
     */
    private HttpResponse httpPost(CloseableHttpClient httpClient, HttpRequest request) {
        String url = request.getUrl();
        HttpPost httpPost = new HttpPost(url);
        if (request.hasHeader()) {
            httpPost = (HttpPost) setHeader(httpPost, request.getHeader());
        }
        String jsonParams = JSON.toJSONString(request.getParams());
        StringEntity stringEntity = new StringEntity(jsonParams, ContentType.create("application/json", "utf-8"));
        httpPost.setEntity(stringEntity);
        httpPost.setConfig(request.getRequestConfig());
        HttpResponse httpResponse = execute(httpClient, httpPost);
        return httpResponse;
    }

    /**
     * @param httpClient
     * @param request
     * @return
     */
    private HttpResponse httpPut(CloseableHttpClient httpClient, HttpRequest request) {
        return null;
    }

    /**
     * @param httpClient
     * @param request
     * @return
     */
    private HttpResponse httpDelete(CloseableHttpClient httpClient, HttpRequest request) {
        return null;
    }

    /**
     * 设置请求头
     *
     * @param httpMethod
     * @param header
     * @return
     */
    private HttpRequestBase setHeader(HttpRequestBase httpMethod, Map<String, String> header) {
        for (Map.Entry<String, String> entry : header.entrySet()) {
            httpMethod.setHeader(entry.getKey(), entry.getValue());
        }
        return httpMethod;
    }

    /**
     * @param httpClient
     * @param response
     */
    private void closeResource(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送请求生成响应
     *
     * @param httpClient
     * @param httpMethod
     * @return
     */
    private HttpResponse execute(CloseableHttpClient httpClient, HttpRequestBase httpMethod) {
        CloseableHttpResponse response = null;
        HttpResponse httpResponse = null;
        try {
            response = httpClient.execute(httpMethod);
            HttpEntity responseEntity = response.getEntity();
            HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
            httpResponse = httpResponseBuilder.setStatusLine(response.getStatusLine().toString())
                    .setContentLength(responseEntity.getContentLength())
                    .setContent(EntityUtils.toString(responseEntity)).build();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeResource(httpClient, response);
        }
        return httpResponse;
    }

}
