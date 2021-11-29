package com.kapcb.framework.middleware.rate;

import com.kapcb.framework.middleware.limiter.LimiterManager;
import com.kapcb.framework.middleware.operation.LimiterOperation;

import java.util.HashMap;
import java.util.Map;

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
        return RateLimiterManager.class;
    }

    public static class Builder extends LimiterOperation.Builder {

        private double qps;

        public double getQps() {
            return this.qps;
        }

        public void setQps(double qps) {
            this.qps = qps;
        }

        @Override
        public LimiterOperation build() {
            Map<String, Object> customerArgument = new HashMap<>(2);
            customerArgument.put("qps", this.qps);
            this.customerArgument(customerArgument);
            return new RateLimiterOperation(this);
        }
    }
}
