package com.kapcb.framework.middleware.lock.impl;

import com.kapcb.framework.middleware.lock.ILock;

/**
 * <a>Title: RedisLuaLockServiceImpl </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RedisLuaLockServiceImpl <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/21 14:54
 */
public class RedisLuaLockService implements ILock {

    @Override
    public boolean lock(String key, String value, int seconds) {
        return false;
    }

    @Override
    public boolean unlock(String key, String value) {
        return false;
    }
}
