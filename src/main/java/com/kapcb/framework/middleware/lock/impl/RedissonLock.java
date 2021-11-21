package com.kapcb.framework.middleware.lock.impl;

import com.kapcb.framework.middleware.lock.ILock;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;

/**
 * <a>Title: RedissonLock </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RedissonLock <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/21 14:56
 */
@Slf4j
public class RedissonLock implements ILock {

    private static RedissonClient redissonClient;

    public void setRedissonClient(RedissonClient redissonClient) {
        RedissonLock.redissonClient = redissonClient;
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
