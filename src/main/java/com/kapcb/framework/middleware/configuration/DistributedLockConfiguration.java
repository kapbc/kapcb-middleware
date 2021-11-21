package com.kapcb.framework.middleware.configuration;

import com.kapcb.framework.middleware.lock.ILock;
import com.kapcb.framework.middleware.lock.impl.RedisLuaLock;
import com.kapcb.framework.middleware.lock.impl.RedissonLock;
import com.kapcb.framework.middleware.lock.impl.ZooKeeperLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

/**
 * <a>Title: DistributedLockConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description: DistributedLockConfiguration <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/21 14:59
 */
@Slf4j
public class DistributedLockConfiguration {

    @Bean("redisLuaLockService")
    public ILock redisLuaLockService() {
        return new RedisLuaLock();
    }

    @Bean("redissonLockService")
    public ILock redissonLockService() {
        return new RedissonLock();
    }

    @Bean("zooKeeperLockService")
    public ILock zooKeeperLockService() {
        return new ZooKeeperLock();
    }

}
