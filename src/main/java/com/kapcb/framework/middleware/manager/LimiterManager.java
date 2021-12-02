package com.kapcb.framework.middleware.manager;

import com.kapcb.framework.middleware.limiter.Limiter;

import java.util.Collection;

/**
 * <a>Title: LimiterManager </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterManager <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/1 22:28
 * @since 1.0
 */
public interface LimiterManager extends Manager<Limiter> {

    @Override
    Limiter getBehavior(String name);

    @Override
    Collection<String> getBehaviorNames();
}
