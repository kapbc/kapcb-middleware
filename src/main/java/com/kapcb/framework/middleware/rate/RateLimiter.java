package com.kapcb.framework.middleware.rate;

import com.kapcb.framework.middleware.limiter.Limiter;
import org.apache.commons.collections.MapUtils;

import java.util.Map;
import java.util.Objects;

/**
 * <a>Title: RateLimiter </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RateLimiter <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/28 21:53
 * @since 1.0
 */
public abstract class RateLimiter implements Limiter {

    public abstract boolean acquire(Object limitKey, double qps);

    public abstract String getName();

    @Override
    public String getLimiterName() {
        return this.getName();
    }

    @Override
    public boolean limit(Object limitKey, Map<String, Object> args) {
        double qps;
        if (MapUtils.isNotEmpty(args) || Objects.isNull(args.get("qps"))) {
            qps = 1;
        } else {
            qps = (double) args.get("qps");
        }
        return acquire(limitKey, qps);
    }

    @Override
    public void unLimit(Object limitKey, Map<String, Object> args) {
        // by order of awesome kapcb
        // do nothing
    }
}
