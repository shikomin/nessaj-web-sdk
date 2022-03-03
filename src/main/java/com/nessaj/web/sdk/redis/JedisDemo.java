package com.nessaj.web.sdk.redis;


import redis.clients.jedis.Jedis;

/**
 * @author keming
 * @Date 2022/01/27 19:54
 */
public class JedisDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("124.223.11.152", 6379);
        jedis.auth("redis@123");
        jedis.select(0);
    }
}
