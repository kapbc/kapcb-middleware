package com.kapcb.framework.middleware.limiter;

import com.kapcb.framework.middleware.behavior.Behavior;

import java.util.Map;

/**
 * <a>Title: Limiter </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Limiter <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/28 13:51
 * @since 1.0
 */
public interface Limiter extends Behavior {

    String getName();

    boolean limit(Object key, Map<String, Object> args);

    void unLimit(Object key, Map<String, Object> args);
}
