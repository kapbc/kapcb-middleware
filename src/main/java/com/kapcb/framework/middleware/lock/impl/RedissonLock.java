package com.kapcb.framework.middleware.lock.impl;

import com.kapcb.framework.middleware.lock.ILock;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

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

    public Lock getLock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        return lock;
    }

    public void unlock(Lock lock){
        lock.unlock();
    }



    @Override
    public boolean lock(String key, String value, int seconds) throws InterruptedException {
        RLock lock = redissonClient.getLock(key);
        boolean result = lock.tryLock(5L, seconds, TimeUnit.SECONDS);
        log.info("redisson get lock for key : {}, success", key);
        return result;
    }

    @Override
    public boolean unlock(String key, String value) {
        boolean result = false;
        RLock lock = redissonClient.getLock(key);
        if (Objects.nonNull(lock)) {
            lock.unlock();
            result = true;
            log.info("un lock redisson lock {} success", key);
        }
        return result;
    }

}
