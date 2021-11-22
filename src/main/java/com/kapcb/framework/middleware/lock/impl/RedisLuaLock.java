package com.kapcb.framework.middleware.lock.impl;

import com.kapcb.framework.middleware.lock.ILock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <a>Title: RedisLuaLockServiceImpl </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RedisLuaLockServiceImpl <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/21 14:54
 */
@Slf4j
public class RedisLuaLock implements ILock {

    private static RedisTemplate<String, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisLuaLock.redisTemplate = redisTemplate;
    }

    @Override
    public boolean lock(String key, String value, int seconds) {
        return false;
    }

    @Override
    public boolean unlock(String key, String value) {
        return false;
    }
}
