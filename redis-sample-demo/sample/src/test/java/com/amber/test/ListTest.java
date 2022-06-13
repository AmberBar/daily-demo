package com.amber.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class ListTest {
    private static final String key = "list";
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * incr number
     */
    @Test
    public void lpush() {

        for (int i = 0; i < 100; i++) {
            Long aLong = redisTemplate.opsForList().leftPush(key, i);
            System.out.println(aLong);
        }
    }

    @Test
    public void lpop() {

        for (int i = 0; i < 100; i++) {
            Object o = redisTemplate.opsForList().leftPop(key);
            System.out.println(o);
        }
    }


    @Test
    public void lrange() {
        lpush();
        Object o = redisTemplate.opsForList().range(key, 0, -1);
        System.out.println(o);
    }
}
