package com.kapcb.framework.middleware.manager;

import com.kapcb.framework.middleware.behavior.Behavior;

import java.util.Collection;

/**
 * <a>Title: Manager </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Manager <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/1 22:28
 * @since 1.0
 */
public interface Manager<T extends Behavior> {

    T getBehavior(String name);

    Collection<String> getBehaviorNames();
}
