package com.kapcb.framework.middleware.limiter.rate;

import com.kapcb.framework.middleware.limiter.LimiterManager;
import com.kapcb.framework.middleware.operation.LimiterOperation;

/**
 * <a>Title: RateLimiterOperation </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RateLimiterOperation <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/28 22:33
 * @since 1.0
 */
public class RateLimiterOperation extends LimiterOperation<RateLimiter> {

    public RateLimiterOperation(Builder builder) {
        super(builder);
    }

    @Override
    public Class<? extends LimiterManager<RateLimiter>> getDefaultLimiterManagerClass() {
        return null;
    }


}
