package com.amber.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import java.util.Random;
import java.util.Set;

@SpringBootTest
public class SetTest {
    private static final String key = "set";
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * sadd key value
     */
    @Test
    public void sadd() {

        for (int i = 0; i < 100; i++) {
            Long aLong = redisTemplate.opsForSet().add(key, i);
            System.out.println(aLong);
        }
    }

    @Test
    public void smembers() {
        Set<Object> members =   redisTemplate.opsForSet().members(key);
        System.out.println(members);
    }


    @Test
    public void spopAndSrandMember() {

        SetOperations<String, Object> operations = redisTemplate.opsForSet();
        System.out.println("size:" + operations.size(key));
        Object o = operations.randomMember(key);
        System.out.println(o + "size:" + operations.size(key));
        Object pop = operations.pop(key);
        System.out.println(pop + "size:" + operations.size(key));
    }

    @Test
    public void union() {
        String set1= "set_1";
        String set2= "set_2";
        SetOperations<String, Object> operations = redisTemplate.opsForSet();
//        for (int i = 0; i < 100 ; i++) {
//            operations.add(set1, new Random().nextInt(100));
//            operations.add(set2, new Random().nextInt(100));
//        }
        // 并集
        Set<Object> union = operations.union(set1, set2);
        System.out.println(union);
        System.out.println(union.size());
        // 交集
        Set<Object> intersect = operations.intersect(set1, set2);
        System.out.println(intersect);
        System.out.println(intersect.size());


        Long new_intersect = operations.intersectAndStore(set1, set2, "new_intersect");
        System.out.println(new_intersect);

        // 差集
        Set<Object> difference = operations.difference(set1, set2);
        System.out.println(difference);
        System.out.println(difference.size());
    }
}
