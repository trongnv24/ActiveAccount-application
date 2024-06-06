package Aibles_Training.java.Spring.boot.active.account.service.impl;

import Aibles_Training.java.Spring.boot.active.account.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, String> redisTemplate;
    private final HashOperations<String, String, String> hashOperations;

    @Autowired
    public RedisServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void saveOTP(String username, String otp) {
        String key = "active_otp";
        String hashKey = username;
        hashOperations.put(key, hashKey, otp);
        redisTemplate.expire(key, 3, TimeUnit.MINUTES);
        log.info("OTP {} saved to Redis for username {}", otp, username);
    }
    }

