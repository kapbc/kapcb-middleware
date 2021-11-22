package com.kapcb.framework.middleware.configuration;

import com.kapcb.framework.middleware.lock.ILock;
import com.kapcb.framework.middleware.lock.impl.RedisLuaLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <a>Title: RedisLuaConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RedisLuaConfiguration <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/21 18:03
 */
@Slf4j
@ConditionalOnBean(value = {RedisTemplate.class})
public class RedisLuaConfiguration {

    @Bean(name = "redisLuaLock")
    public ILock RedisLuaLock(RedisTemplate<String, Object> redisTemplate) {
        RedisLuaLock redisLuaLock = new RedisLuaLock();
        redisLuaLock.setRedisTemplate(redisTemplate);
        return redisLuaLock;
    }
}
