package com.kapcb.framework.middleware.rate;

import com.kapcb.framework.middleware.limiter.Limiter;
import com.kapcb.framework.middleware.manager.AbstractLimiterManager;

import java.util.Collection;

/**
 * <a>Title: RedissonLimiterManager </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RedissonLimiterManager <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/1 22:43
 * @since 1.0
 */
public class RedissonLimiterManager extends AbstractLimiterManager {

    @Override
    public Limiter getLimiterBehavior(String name) {
        return null;
    }

    @Override
    public Collection<String> getLimiterBehaviorNames() {
        return null;
    }
}
