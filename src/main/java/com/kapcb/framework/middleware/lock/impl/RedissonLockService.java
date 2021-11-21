package com.kapcb.framework.middleware.lock.impl;

import com.kapcb.framework.middleware.lock.ILock;

/**
 * <a>Title: RedissonLockServiceImpl </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RedissonLockServiceImpl <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/21 14:56
 */
public class RedissonLockService implements ILock {

    @Override
    public boolean lock(String key, String value, int seconds) {
        return false;
    }

    @Override
    public boolean unlock(String key, String value) {
        return false;
    }
}
