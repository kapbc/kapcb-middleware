package com.kapcb.framework.middleware.manager;

import com.kapcb.framework.middleware.lock.Locker;

import java.util.Collection;

/**
 * <a>Title: LockManager </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LockManager <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/1 22:35
 * @since 1.0
 */
public interface LockManager extends Manager<Locker> {

    @Override
    Locker getBehavior(String name);

    @Override
    Collection<String> getBehaviorNames();

}
