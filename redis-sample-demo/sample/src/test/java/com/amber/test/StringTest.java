package com.amber.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class StringTest {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * incr number
     */
    @Test
    public void incrNumber() {
       for (int i = 0; i < 10; i++) {
           Long number = redisTemplate.opsForValue().increment("number");
           System.out.println(number);
       }
    }

    /**
     * incrby number 100
     */
    @Test
    public void incrNumberBy() {
        Long number = redisTemplate.opsForValue().increment("number", 100);
        System.out.println(number);
    }

    /**
     * decr number
     */
    @Test
    public void decrNumber() {
        Long number = redisTemplate.opsForValue().decrement("number");
        System.out.println(number);
    }

    /**
     * decrby number
     */
    @Test
    public void decrByNumber() {
        for (int i = 0; i < 10; i++) {
            Long number = redisTemplate.opsForValue().decrement("number", 100);
            System.out.println(number);
        }
    }

    @Test
    public void setnxTest() {
        String key = "number";
        redisTemplate.delete(key);
        Boolean number = redisTemplate.opsForValue().setIfAbsent(key, 100);
        System.out.println(number);

        Boolean number_2 = redisTemplate.opsForValue().setIfAbsent(key, 120);
        System.out.println(number_2);

        Assertions.assertEquals(number, true);
        Assertions.assertEquals(number_2, false);
    }

    /**
     * mset number 1 number_1 2
     * mget number number_1
     */
    @Test
    public void msetAndMget() {
        Map<String, Long> map = new HashMap<>();
        map.put("number", 1L);
        map.put("number_1", 2L);

        redisTemplate.opsForValue().multiSet(map);
        List<Object> objects = redisTemplate.opsForValue().multiGet(Arrays.asList("number", "number_1"));
        System.out.println(objects);
    }
}
