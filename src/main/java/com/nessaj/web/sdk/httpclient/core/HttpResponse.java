package com.nessaj.web.sdk.httpclient.core;


/**
 * @author keming
 * @Date 2022/02/25 22:10
 */
public class HttpResponse extends Response {

    @Override
    public String toString() {
        return "HttpResponse{\n\t\tstatus line: " + this.getStatusLine()
                + "\n\t\tcontent-length: " + this.getContentLength()
                + "\n\t\tcontent: " + this.getContent() + "\n}";
    }
}
