package com.kapcb.framework.middleware.manager;

import com.kapcb.framework.middleware.limiter.Limiter;

import java.util.Collection;

/**
 * <a>Title: AbstractLimiterManager </a>
 * <a>Author: Kapcb <a>
 * <a>Description: AbstractLimiterManager <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/1 22:36
 * @since 1.0
 */
public abstract class AbstractLimiterManager implements LimiterManager {

    public abstract Limiter getLimiterBehavior(String name);

    public abstract Collection<String> getLimiterBehaviorNames();

    @Override
    public Limiter getBehavior(String name) {
        return getLimiterBehavior(name);
    }

    @Override
    public Collection<String> getBehaviorNames() {
        return getLimiterBehaviorNames();
    }
}
