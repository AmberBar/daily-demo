package com.amber.limiter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;

@SpringBootTest
public class LimiterRedisTest {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public Long redisLimiter() {
        String script =
                "local key = KEYS[1]\n" +
                        "local period = ARGV[1]\n" +
                        "local limit= ARGV[2]\n" +
                        "local times = redis.call('INCR',key)\n" +
                        "\n" +
                        "if times == 1 then\n" +
                        "    redis.call('expire',KEYS[1], period)\n" +
                        "end\n" +
                        "\n" +
                        "if times > tonumber(limit) then\n" +
                        "    return 0\n" +
                        "end\n" +
                        "return 1";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        return redisTemplate.execute(redisScript, Collections.singletonList("limiter:merchantId" + 1), 1, 10);
    }

    @Test
    public void limiter() {
        for (int i = 0; i < 20; i++) {

            System.out.println(redisLimiter());
        }
    }

}
