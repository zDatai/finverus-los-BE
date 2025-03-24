package com.zdatai.finverus.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class CacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheService.class);


    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public CacheService(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate= stringRedisTemplate;
    }

    @PostConstruct
    public void openRedisConnection(){
        try {
            stringRedisTemplate.opsForValue().get("key");
        } catch (Exception e){
            LOGGER.error("Unable to establish a Redis connection during application startup.");
        }
    }

    public void saveStringCacheObject(String key, String value, Long expireTimeInSecounds) {
        stringRedisTemplate.opsForValue().set(key, value, expireTimeInSecounds, TimeUnit.SECONDS);
    }

    public String getStringCacheObject(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void clearCacheObject(String key) {
        try {
            Boolean isDeleted = stringRedisTemplate.delete(key);
            if (Boolean.TRUE.equals(isDeleted)) {
                LOGGER.info("Cache key '{}' successfully cleared.", key);
            } else {
                LOGGER.warn("Cache key '{}' does not exist or could not be cleared.", key);
            }
        } catch (Exception e) {
            LOGGER.error("Error occurred while clearing cache key '{}': {}", key, e.getMessage());
        }
    }

    public Set<String> getAllKeys(String pattern) {
        try {
            return stringRedisTemplate.keys(pattern);
        } catch (Exception e) {
            LOGGER.error("Error occurred while retrieving keys with pattern '{}': {}", pattern, e.getMessage());
            return null;
        }
    }

}
