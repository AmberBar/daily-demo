package com.amber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis-policy")
public class RedisPolicyController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/noeviction/{key}/{value}")
    public String noeviction(@PathVariable String key, @PathVariable String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        value = stringRedisTemplate.opsForValue().get(key);
        return value;
    }
}
