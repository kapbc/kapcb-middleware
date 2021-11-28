package com.kapcb.framework.middleware.limiter;

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
public interface Limiter {

    String getLimiterName();

    boolean limit(Object limitKey, Map<String, Object> args);

    void unLimit(Object limitKey, Map<String, Object> args);
}
