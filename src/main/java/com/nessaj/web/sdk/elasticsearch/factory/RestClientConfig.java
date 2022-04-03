package com.nessaj.web.sdk.elasticsearch.factory;

import com.nessaj.web.sdk.common.constants.Constants;

import java.util.Arrays;

/**
 * @author keming
 * @Date 2022/03/22 16:44
 */
public class RestClientConfig {

    private static String[] hosts;

    private static int[] ports;

    private static String scheme;

    private static String userName;

    private static String password;

    static {
        RestClientConfig.hosts = System.getenv(Constants.HOSTS).split(";");
        RestClientConfig.ports = Arrays.stream(System.getenv(Constants.PORTS).split(";"))
                .mapToInt(Integer::parseInt).toArray();
        RestClientConfig.scheme = System.getenv(Constants.SCHEME);
        RestClientConfig.userName = System.getenv(Constants.USERNAME);
        RestClientConfig.password = System.getenv(Constants.PASSWORD);
    }

    public static String[] getHosts() {
        return hosts;
    }

    public static int[] getPorts() {
        return ports;
    }

    public static String getScheme() {
        return scheme;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }
}
