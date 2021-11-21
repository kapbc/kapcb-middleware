package com.kapcb.framework.middleware.lock;

/**
 * <a>Title: IRedisLock </a>
 * <a>Author: Kapcb <a>
 * <a>Description: IRedisLock <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/21 14:51
 */
public interface ILock {

    boolean lock(String key, String value, int seconds);

    boolean unlock(String key, String value);
}
