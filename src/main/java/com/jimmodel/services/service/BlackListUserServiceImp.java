package com.jimmodel.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service(value = "blackListUserService")
public class BlackListUserServiceImp implements BlackListUserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Boolean isBlackListed(UUID userId) {
        return redisTemplate.hasKey(userId.toString());
    }

    @Override
    public void blackList(UUID userId, Duration expiration) {
        ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
        operations.set(userId.toString(), 1, expiration);
    }

    @Override
    public void unblackList(UUID userId) {
        ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
        operations.getOperations().delete(userId.toString());
    }
}
