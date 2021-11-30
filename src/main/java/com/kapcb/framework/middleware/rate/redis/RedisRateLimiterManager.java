package com.kapcb.framework.middleware.rate.redis;

import com.kapcb.framework.middleware.rate.RateLimiter;
import com.kapcb.framework.middleware.rate.RateLimiterManager;
import org.redisson.Redisson;
import org.redisson.config.Config;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <a>Title: RedisRateLimiterManager </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RedisRateLimiterManager <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/29 22:10
 * @since 1.0
 */
public class RedisRateLimiterManager extends RateLimiterManager {

    private Config config;
    private boolean dynamic;
    private final Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>(6);

    public RedisRateLimiterManager(Config config) {
        this.config = config;
    }

    private RedisRateLimiter createRateLimiter(String limiterName) {
        return new RedisRateLimiter(limiterName, Redisson.create(config));
    }

    @Override
    public RateLimiter getRateLimiter(String limiterName) {
        RateLimiter rateLimiter = this.rateLimiterMap.get(limiterName);
        if (Objects.isNull(rateLimiter) && this.dynamic) {
            synchronized (this.rateLimiterMap) {
                rateLimiter = this.rateLimiterMap.get(limiterName);
                if (Objects.isNull(rateLimiter)) {
                    rateLimiter = createRateLimiter(limiterName);
                    this.rateLimiterMap.put(limiterName, rateLimiter);
                }
            }
        }
        return rateLimiter;
    }

    @Override
    public Collection<String> getRateLimiterNames() {
        return this.rateLimiterMap.keySet();
    }
}
