package com.nessaj.web.sdk.elasticsearch.factory;

import com.nessaj.web.sdk.common.constants.SymbolSet;

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
        RestClientConfig.hosts = System.getenv(SymbolSet.HOSTS).split(";");
        RestClientConfig.ports = Arrays.stream(System.getenv(SymbolSet.PORTS).split(";"))
                .mapToInt(Integer::parseInt).toArray();
        RestClientConfig.scheme = System.getenv(SymbolSet.SCHEME);
        RestClientConfig.userName = System.getenv(SymbolSet.USERNAME);
        RestClientConfig.password = System.getenv(SymbolSet.PASSWORD);
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
