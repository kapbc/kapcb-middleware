package com.kapcb.framework.middleware.rate;

import com.kapcb.framework.middleware.limiter.Limiter;
import com.kapcb.framework.middleware.manager.AbstractLimiterManager;

import java.util.Collection;

/**
 * <a>Title: GuavaLimiterManager </a>
 * <a>Author: Kapcb <a>
 * <a>Description: GuavaLimiterManager <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/1 22:44
 * @since 1.0
 */
public class GuavaLimiterManager extends AbstractLimiterManager {

    @Override
    public Limiter getLimiterBehavior(String name) {
        return null;
    }

    @Override
    public Collection<String> getLimiterBehaviorNames() {
        return null;
    }
}
