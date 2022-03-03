package redis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * @author keming
 * @Date 2022/03/03 22:01
 */
public class JedisTest {

    private Jedis jedis;

    @BeforeEach
    void jedisConnector() {
        jedis = new Jedis("124.223.11.152", 6379);
        jedis.auth("redis@123");
        jedis.select(0);
    }

    @Test
    void testString() {
        String res = jedis.set("name", "griff");
        System.out.println("result =>>>" + res);
        String name = jedis.get("name");
        System.out.println("name =>>>" + name);
    }

    @Test
    void testMap() {
        jedis.hset("user:1", "name", "topson");
        jedis.hset("user:1", "age", "25");
        Map<String, String> user1 = jedis.hgetAll("user:1");
        for (Map.Entry entry : user1.entrySet()) {
            System.out.println("field:" + entry.getKey() + ", value:" + entry.getValue());
        }
    }

    @AfterEach
    void closeResource() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
