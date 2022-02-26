package com.nessaj.web.sdk.httpclient.core;

/**
 * @author keming
 * @Date 2022/02/25 21:42
 */
public abstract class Response {

    private String statusLine;

    private long contentLength;

    private String content;

    public String getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
