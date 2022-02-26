package com.nessaj.web.sdk.httpclient.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author keming
 * @Date 2022/02/26 16:36
 */
public class URLUtil {

    public static String addParams2URL(String url, HashMap<String, String> paramsMap) {
        StringBuffer params = new StringBuffer();
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            params.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        return url + params.toString().substring(0, params.length() - 1);
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new LinkedHashMap<>();
        map.put("name", "Tadokoro");
        map.put("age", "24");
        map.put("identity", "student");
        System.out.println(addParams2URL("http://localhost:9090/aaa?", map));
    }

}
