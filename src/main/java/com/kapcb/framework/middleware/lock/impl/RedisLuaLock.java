package com.kapcb.framework.middleware.lock.impl;

import com.kapcb.framework.middleware.lock.ILock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Objects;

/**
 * <a>Title: RedisLuaLockServiceImpl </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RedisLuaLockServiceImpl <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/21 14:54
 */
@Slf4j
public class RedisLuaLock implements ILock {

    private static RedisTemplate<String, Object> redisTemplate;
    private static DefaultRedisScript<Long> lockRedisScript;
    private static DefaultRedisScript<Long> unlockRedisScript;
    private static final long REDIS_LOCK_SUCCESS_KEY = 1L;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisLuaLock.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        lockRedisScript = new DefaultRedisScript<>();
        lockRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/lock.lua")));
        lockRedisScript.setResultType(Long.class);

        unlockRedisScript = new DefaultRedisScript<>();
        unlockRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/unlock.lua")));
        unlockRedisScript.setResultType(Long.class);
    }

    @Override
    public boolean lock(String key, String value, int seconds) {
        Long executeResult = null;
        try {
            executeResult = redisTemplate.execute(lockRedisScript, Collections.singletonList(key), value, seconds);
        } catch (Exception e) {
            log.error("try get redis lua lock error, key is : {}, value is : {}, release time is : {}, error message is : {}", key, value, seconds, e.getMessage());
        }
        return Objects.equals(REDIS_LOCK_SUCCESS_KEY, executeResult);
    }

    @Override
    public boolean unlock(String key, String value) {
        Long executeResult = null;
        try {
            executeResult = redisTemplate.execute(unlockRedisScript, Collections.singletonList(key), value);
        } catch (Exception e) {
            log.error("unlock redis lua lock error, key is : {}, value is : {}, error message is : {}", key, value, e.getMessage());
        }
        return Objects.equals(REDIS_LOCK_SUCCESS_KEY, executeResult);
    }
}
