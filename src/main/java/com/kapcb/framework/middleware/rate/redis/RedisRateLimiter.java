package com.kapcb.framework.middleware.rate.redis;

import com.kapcb.framework.middleware.rate.RateLimiter;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;

/**
 * <a>Title: RedisRateLimiter </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RedisRateLimiter <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/29 22:09
 * @since 1.0
 */
public class RedisRateLimiter extends RateLimiter {

    private String limiterName;
    private RedissonClient redissonClient;

    public RedisRateLimiter(String limiterName, RedissonClient redissonClient) {
        this.limiterName = limiterName;
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean acquire(Object limitKey, double qps) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(limitKey.toString());
        rateLimiter.trySetRate(RateType.OVERALL, new Double(qps).longValue(), 1, RateIntervalUnit.SECONDS);
        return rateLimiter.tryAcquire();
    }

    @Override
    public String getName() {
        return this.limiterName;
    }
}
