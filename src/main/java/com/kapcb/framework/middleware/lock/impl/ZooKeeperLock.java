package com.kapcb.framework.middleware.lock.impl;

import com.kapcb.framework.middleware.lock.ILock;

/**
 * <a>Title: ZooKeeperLockServiceImpl </a>
 * <a>Author: Kapcb <a>
 * <a>Description: ZooKeeperLockServiceImpl <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/21 14:57
 */
public class ZooKeeperLock implements ILock {
    
    @Override
    public boolean lock(String key, String value, int seconds) {
        return false;
    }

    @Override
    public boolean unlock(String key, String value) {
        return false;
    }

}
