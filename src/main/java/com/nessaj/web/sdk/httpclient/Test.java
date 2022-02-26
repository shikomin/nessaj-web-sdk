package com.nessaj.web.sdk.httpclient;

import java.net.URLEncoder;

/**
 * @author keming
 * @Date 2022/02/25 18:18
 */
public class Test {
    public static void main(String[] args) {
        String str="&name=griff&age=24&school=华东师范大学&sid=2019150012&";
        System.out.println(URLEncoder.encode(str));
    }
}
