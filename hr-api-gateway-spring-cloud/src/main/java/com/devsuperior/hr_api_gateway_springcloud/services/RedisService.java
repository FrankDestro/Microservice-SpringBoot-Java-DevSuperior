package com.devsuperior.hr_api_gateway_springcloud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String getPublicKey(String keyPublic) {
        return stringRedisTemplate.opsForValue().get("keyPublic");
    }
}
