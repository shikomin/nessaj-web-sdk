# nessaj-web-sdk

## 1.httpclient-sdk  
使用方法：  
创建一个**sender** 
通过**HttpRequestBuilder**配置好请求参数  
调用sender的send方法发送请求,并获取响应
```java
public class HttpGetHasParamsDemo {

    public static void main(String[] args) {
        Sender sender = new HttpSender();
        LinkedHashMap<Object, Object> params = new LinkedHashMap<>();
        params.put("mid", "1");
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true).build();
        HttpRequest httpRequest = HttpRequest.custom()
                .setMethod(HttpMethod.GET)
                .setUrl("http://localhost:9097/module")
                .setHeader(null)
                .setParams(params)
                .setRequestConfig(requestConfig)
                .build();
        HttpResponse httpResponse = (HttpResponse) sender.send(httpRequest);
        System.out.println(httpResponse.toString());
    }

}
```

2022-02-27:
新增带ssl证书请求功能

2022-03-09:
新增httpmime上传文件功能

待施工...  


## 2.redis-sdk  
待施工...


## 3.kafka

sdk for the nessaj-web-projects
