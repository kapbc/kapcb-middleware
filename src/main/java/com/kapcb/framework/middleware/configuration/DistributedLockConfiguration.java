package com.kapcb.framework.middleware.configuration;

import com.kapcb.framework.middleware.lock.ILock;
import com.kapcb.framework.middleware.lock.impl.RedisLuaLockService;
import com.kapcb.framework.middleware.lock.impl.RedissonLockService;
import com.kapcb.framework.middleware.lock.impl.ZooKeeperLockService;
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
        return new RedisLuaLockService();
    }

    @Bean("redissonLockService")
    public ILock redissonLockService() {
        return new RedissonLockService();
    }

    @Bean("zooKeeperLockService")
    public ILock zooKeeperLockService() {
        return new ZooKeeperLockService();
    }

}
