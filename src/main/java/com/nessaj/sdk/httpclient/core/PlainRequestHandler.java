package com.nessaj.sdk.httpclient.core;

import com.alibaba.fastjson.JSON;
import com.nessaj.sdk.httpclient.common.constants.ErrorMessage;
import com.nessaj.sdk.httpclient.common.constants.StringConstants;
import com.nessaj.sdk.httpclient.common.enums.HttpMethod;
import com.nessaj.sdk.httpclient.common.exception.NullFileNameException;
import com.nessaj.sdk.httpclient.utils.URLUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author keming
 * @Date 2022/03/01 16:03
 */
public class PlainRequestHandler {

    private static Logger logger = Logger.getLogger(PlainRequestHandler.class);

    /**
     * 根据method处理请求
     *
     * @param httpClient
     * @param request
     * @return
     */
    public Response handle(CloseableHttpClient httpClient, Request request) {
        HttpMethod method = request.getMethod();
        HttpResponse response = null;
        switch (method) {
            case GET:
                response = httpGet(httpClient, request);
                break;
            case POST:
                response = httpPost(httpClient, request);
                break;
            case PUT:
                response = httpPut(httpClient, request);
                break;
            case DELETE:
                response = httpDelete(httpClient, request);
        }
        return response;
    }

    /**
     * get方法 设置请求头、配置参数
     *
     * @param httpClient
     * @param request
     * @return
     */
    private HttpResponse httpGet(CloseableHttpClient httpClient, Request request) {
        String url = request.getUrl();
        if (request.hasParams()) {
            url = URLUtil.addParams2URL(url, request.getParams());
        }
        HttpGet httpGet = new HttpGet(url);
        if (request.hasHeader()) {
            httpGet = (HttpGet) setHeader(httpGet, request.getHeader());
        }
        httpGet.setConfig(request.getRequestConfig());
        HttpResponse httpResponse = responseHandler(httpClient, httpGet);
        return httpResponse;
    }

    /**
     * post方法，请求头、请求体（普通数据/二进制文件数据）
     *
     * @param httpClient
     * @param request
     * @return
     */
    private HttpResponse httpPost(CloseableHttpClient httpClient, Request request) {
        String url = request.getUrl();
        HttpPost httpPost = new HttpPost(url);
        if (request.hasHeader()) {
            httpPost = (HttpPost) setHeader(httpPost, request.getHeader());
        }
        HttpEntity httpEntity = null;
        try {
            httpEntity = geneHttpEntity(request);
        } catch (NullFileNameException e) {
            logger.error(ErrorMessage.GET_FILENAME_ERROR, e);
            return HttpResponse.custom().setContent(StringConstants.FAILED_TO_SEND_POST).build();
        }

        httpPost.setEntity(httpEntity);
        httpPost.setConfig(request.getRequestConfig());
        HttpResponse httpResponse = responseHandler(httpClient, httpPost);
        try {
            InputStream inStream = request.getMultipartData();
            if (inStream != null)
                inStream.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }

    /**
     * @param httpClient
     * @param request
     * @return
     */
    private HttpResponse httpPut(CloseableHttpClient httpClient, Request request) {
        return null;
    }

    /**
     * @param httpClient
     * @param request
     * @return
     */
    private HttpResponse httpDelete(CloseableHttpClient httpClient, Request request) {
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
     * 关闭资源
     *
     * @param httpClient
     * @param response
     */
    private void closeResource(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        try {
            if (httpClient != null)
                httpClient.close();
            if (response != null)
                response.close();
        } catch (IOException e) {
            logger.error(ErrorMessage.SEND_HTTP_REQUEST_EXCEPTION, e);
        }
    }

    /**
     * 根据是否上传二进制文件生成HttpEntity
     *
     * @param request
     * @return
     */
    private HttpEntity geneHttpEntity(Request request) throws NullFileNameException, NullPointerException {
        if (request.getMultipartData() != null) {
            InputStream inputStream = request.getMultipartData();
            String fileName = getFileNameFromParams(request.getParams());
            return MultipartEntityBuilder.create().addBinaryBody(StringConstants.FILE_PARAM, inputStream, ContentType.MULTIPART_FORM_DATA, fileName).build();
        }
        String jsonParams = JSON.toJSONString(request.getParams());
        return new StringEntity(jsonParams, ContentType.create(StringConstants.APPLICATION_JSON, StringConstants.UTF8));
    }

    /**
     * 从参数中获取文件名
     *
     * @param params
     * @return
     * @throws NullFileNameException
     */
    private String getFileNameFromParams(HashMap<Object, Object> params) throws NullFileNameException, NullPointerException {
        String fileName = (String) params.get(StringConstants.FILENAME_KEY);
        if (fileName == null || fileName.equals(StringConstants.EMPTY_STRING) || fileName.equals(StringConstants.SPACE_STRING))
            throw new NullFileNameException();
        return fileName;
    }

    /**
     * 发送请求生成响应
     *
     * @param httpClient
     * @param httpMethod
     * @return
     */
    private HttpResponse responseHandler(CloseableHttpClient httpClient, HttpRequestBase httpMethod) {
        CloseableHttpResponse response = null;
        HttpResponse httpResponse = null;
        try {
            response = httpClient.execute(httpMethod);
            HttpEntity responseEntity = response.getEntity();
            httpResponse = HttpResponse.custom().setStatusLine(response.getStatusLine().toString())
                    .setContentLength(responseEntity.getContentLength())
                    .setContent(EntityUtils.toString(responseEntity)).build();
        } catch (IOException e) {
            logger.error(ErrorMessage.SEND_HTTP_REQUEST_EXCEPTION, e);
        } finally {
            closeResource(httpClient, response);
        }
        return httpResponse;
    }

}
