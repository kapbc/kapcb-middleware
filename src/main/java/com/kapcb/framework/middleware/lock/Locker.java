package com.kapcb.framework.middleware.lock;

import com.kapcb.framework.middleware.behavior.Behavior;


/**
 * <a>Title: Lock </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Lock <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/1 21:16
 * @since 1.0
 */
public interface Locker extends Behavior {

    boolean lock(String key, String value);

    void unlock(String key, String value);

}
