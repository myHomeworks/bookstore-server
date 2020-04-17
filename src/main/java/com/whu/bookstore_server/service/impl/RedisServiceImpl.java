package com.whu.bookstore_server.service.impl;

import com.whu.bookstore_server.domain.Admin;
import com.whu.bookstore_server.domain.Visitor;
import com.whu.bookstore_server.service.RedisService;
import com.whu.bookstore_server.domain.User;
import com.whu.bookstore_server.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Override
    public String checkSessionId(String sessionId) {
        return stringRedisTemplate.opsForValue().get(sessionId);
    }

    @Override
    public void saveSessionId(String sessionId, String username) {
        stringRedisTemplate.opsForValue().set(sessionId, username, 30, TimeUnit.DAYS);

    }

    @Override
    public void delSessionId(String sessionId) {
        redisTemplate.delete(sessionId);
    }

    @Override
    public void saveObjectInstanceBySessionId(String sessionId, Object obj) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        if (obj instanceof User || obj instanceof Visitor) {
            operations.set(sessionId, obj, 2, TimeUnit.HOURS);
        } else if (obj instanceof Admin) {
            operations.set(sessionId, obj, 60, TimeUnit.MINUTES);
        } else {
            log.error("Invalid Role!");
        }
    }

    @Override
    public Object getObjectInstanceBySessionId(String sessionId) {
        return redisTemplate.opsForValue().get(sessionId);
    }

    @Override
    public void updateExpireTime(String key) {
        redisTemplate.expire(key, 30, TimeUnit.MINUTES);
    }


}
