package com.kapcb.framework.middleware.rate;

import com.kapcb.framework.middleware.limiter.LimiterManager;

import java.util.Collection;

/**
 * <a>Title: RateLimiterManager </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RateLimiterManager <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/28 21:58
 * @since 1.0
 */
public abstract class RateLimiterManager implements LimiterManager<RateLimiter> {

    public abstract RateLimiter getRateLimiter(String limiterName);

    public abstract Collection<String> getRateLimiterNames();

    @Override
    public RateLimiter getLimiter(String limiterName) {
        return getRateLimiter(limiterName);
    }

    @Override
    public Collection<String> getLimiterNames() {
        return getRateLimiterNames();
    }
}
