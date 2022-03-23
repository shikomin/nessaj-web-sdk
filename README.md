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
新增带ssl证书请求功能(http with ssl)

2022-03-09:
新增httpmime上传文件功能(upload multipartFile)

```java
public class UploadDemoWithSSL {

    public static void main(String[] args) {
        Sender sender = new HttpsSender();
        HttpsRequest request = HttpsRequest.custom()
                .setUrl("https://localhost:9095/module")
                .setMethod(HttpMethod.POST)
                .setHeader(null)
                .setParams(null)
                .setMultipartData(new File("E:/etc/nessaj-web-local/orig/rbk.jpg"))
                .setRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setConnectionRequestTimeout(5000)
                        .setSocketTimeout(5000)
                        .setRedirectsEnabled(true).build())
                .setKeystorePath("E:/Nessaj/ssl/nessaj-keystore.jks").setKeystoreType("jks").setKeystorePassword("Nessaj@123")
                .setTruststorePath("E:/Nessaj/ssl/nessaj-truststore.jks").setTruststoreType("jks").setTruststorePassword("Nessaj@123")
                .build();
        HttpResponse httpResponse = (HttpResponse) sender.send(request);
        System.out.println(httpResponse.toString());
    }

}
```

待施工...

## 2.redis-sdk

jedis templatet

## 3.kafka

sdk for the nessaj-web-projects

## 4.elasticsearch

如何创建rest-client?  
如何从一个实体类创建index?  
elasticsearch java high level rest client...

### 4.1 How to get a rest-client?

Set your system environment variables:

```shell
HOSTS=192.168.2.6\;192.168.2.7;
PORTS=9200\;9200;
SCHEME=http;
USERNAME=elastic
PASSWORD=Huawei@123;
```

Get a rest-client from **RestHighLevelClientFactory**

```java
RestHighLevelClient client=RestHighLevelClientFactory.getInstance().build();
```

### 4.2 How to create a index from a entity class.

this is a example for you to learn how to create a elasticsearch-index-entity:

```java

@Index(name = "cat")
public class Cat {

    @Type
    private String name;

    @Type(type = ElasticType.LONG)
    private String age;

    @Type
    private String gender;

    @Type(type = ElasticType.DATE)
    private Date birthday;

}
```


