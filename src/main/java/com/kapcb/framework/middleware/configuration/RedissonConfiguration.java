package com.kapcb.framework.middleware.configuration;

import com.kapcb.framework.common.constants.enums.StringPool;
import com.kapcb.framework.middleware.lock.ILock;
import com.kapcb.framework.middleware.lock.impl.RedissonLock;
import com.kapcb.framework.middleware.properties.RedissonConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * <a>Title: RedissonConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RedissonConfiguration <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/21 16:52
 */
@Slf4j
@ConditionalOnProperty(prefix = "kapcb.redisson.enable", havingValue = "true")
@EnableConfigurationProperties(value = {RedissonConfigurationProperties.class})
public class RedissonConfiguration {

    @Resource
    private RedissonConfigurationProperties redissonProperties;

    /**
     * 哨兵模式
     *
     * @return RedissonClient
     */
    @Bean
    @ConditionalOnProperty(prefix = "kapcb.redisson.single", havingValue = "false", matchIfMissing = false)
    public RedissonClient redissonSentinel() {
        Config config = new Config();
        SentinelServersConfig sentinelServersConfig = config.useSentinelServers()
                .addSentinelAddress(redissonProperties.getAddress().split(StringPool.COMMA.value()))
                .setMasterName(redissonProperties.getMasterName())
                .setTimeout(redissonProperties.getTimeout())
                .setMasterConnectionPoolSize(redissonProperties.getMasterConnectionPoolSize())
                .setSlaveConnectionPoolSize(redissonProperties.getSlaveConnectionPoolSize());
        if (StringUtils.isNotBlank(redissonProperties.getPassword())) {
            sentinelServersConfig.setPassword(redissonProperties.getPassword());
        }
        return Redisson.create(config);
    }

    /**
     * 单机模式
     *
     * @return RedissonClient
     */
    @Bean
    @ConditionalOnProperty(prefix = "kapcb.redisson.single", havingValue = "true", matchIfMissing = false)
    public RedissonClient redissonSingle() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer()
                .setAddress(redissonProperties.getAddress())
                .setUsername(redissonProperties.getUsername())
                .setPassword(redissonProperties.getPassword())
                .setConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize());
        if (StringUtils.isNotBlank(redissonProperties.getPassword())) {
            singleServerConfig.setPassword(redissonProperties.getPassword());
        }
        return Redisson.create(config);
    }

    @Bean(name = "redissonLock")
    @ConditionalOnBean(RedissonClient.class)
    public ILock redissonLock(RedissonClient redissonClient) {
        RedissonLock redissonLock = new RedissonLock();
        redissonLock.setRedissonClient(redissonClient);
        return redissonLock;
    }
}
