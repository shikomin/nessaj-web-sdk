package com.nessaj.web.sdk.httpclient.examples;


import com.nessaj.web.sdk.httpclient.common.enums.HttpMethod;
import com.nessaj.web.sdk.httpclient.core.HttpRequest;
import com.nessaj.web.sdk.httpclient.core.Request;
import com.nessaj.web.sdk.httpclient.core.Response;
import com.nessaj.web.sdk.httpclient.core.Sender;
import com.nessaj.web.sdk.httpclient.core.impl.HttpSender;
import org.apache.http.client.config.RequestConfig;

import java.util.LinkedHashMap;

/**
 * @author keming
 * @Date 2022/03/06 20:30
 */
public class Download {

    public static void main(String[] args) {
        String url = "https://www1671.ff-04.com/token=AKDwQnbNMHotWHRP9wVevA" +
                "/1646582574/43.156.0.0/31/c/54/08402c6d84f2492e449e4f88ef2db54c-360p.mp4";
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true).build();
        LinkedHashMap<String, String> header = new LinkedHashMap<>();
        header.put("authority", "www1671.ff-04.com");
        header.put("path", "/token=AKDwQnbNMHotWHRP9wVevA" +
                "/1646582574/43.156.0.0/31/c/54/08402c6d84f2492e449e4f88ef2db54c-360p.mp4");
        header.put("scheme", "https");
        header.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9," +
                "image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        header.put("accept-encoding", "gzip, deflate, br");
        header.put("accept-language", "zh-CN,zh;q=0.9");
        header.put("fname", "av01");
        header.put("referer", "https://www1671.ff-04.com/token=AKDwQnbNMHotWHRP9wVevA/1646582574/43.156.0.0/31/c/54/08402c6d84f2492e449e4f88ef2db54c-360p.mp4");
        header.put("cache-control","max-age=0");
        header.put("if-range","\"5c6dc56e-1695362e\"");
        header.put("range","bytes=21594112-376569855");
        header.put("sec-ch-ua","\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"99\", \"Google Chrome\";v=\"99\"");
        header.put("sec-ch-ua-mobile","?0");
        header.put("sec-ch-ua-platform","Windows");
        header.put("sec-fetch-dest","document");
        header.put("sec-fetch-mode","navigate");
        header.put("sec-fetch-site ","none");
        header.put("sec-fetch-user:","?1");
        header.put("upgrade-insecure-requests","1");
        header.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36");

        Request httpRequest = HttpRequest.custom()
                .setMethod(HttpMethod.GET)
                .setUrl(url)
                .setRequestConfig(requestConfig)
                .setHeader(header)
                .setParams(null)
                .build();
        Sender sender = new HttpSender();
        Response response = sender.send(httpRequest);
        System.out.println(response.getContentLength());
    }

}
