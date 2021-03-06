package com.nessaj.sdk.httpclient.core;


import com.nessaj.sdk.httpclient.core.builder.HttpResponseBuilder;

/**
 * @author keming
 * @Date 2022/02/25 22:10
 */
public class HttpResponse extends Response {

    public HttpResponse(){}

    public static HttpResponseBuilder custom(){
        return HttpResponseBuilder.create();
    }

    @Override
    public String toString() {
        return "HttpResponse{\n\tstatus line: " + this.getStatusLine()
                + "\n\tcontent-length: " + this.getContentLength()
                + "\n\tcontent: " + this.getContent() + "\n}";
    }
}
