package com.amber.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class HashTest {
    private static final String key = "user";
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void get() {
        redisTemplate.opsForHash().put(key, "name", "amber");
        Object name = redisTemplate.opsForHash().get(key, "name");
        System.out.println(name);
    }

    @Test
    public void hgetAll() {
        redisTemplate.opsForHash().put(key, "name", "amber");
        redisTemplate.opsForHash().put(key, "age", 12);

        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        System.out.println(entries);
    }

    @Test
    public void demo() {
        redisTemplate.opsForHash().put("10007005", "3020", "amber");
        redisTemplate.opsForHash().put(key, "age", 12);

        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        System.out.println(entries);
    }
}
