package com.nessaj.web.sdk.httpclient.core.interfaces;

import com.nessaj.web.sdk.httpclient.core.HttpRequest;
import com.nessaj.web.sdk.httpclient.core.Request;
import com.nessaj.web.sdk.httpclient.core.Response;

/**
 * @author keming
 * @Date 2022/02/25 21:41
 */
public interface Sender {

    Response send(Request request);

}
