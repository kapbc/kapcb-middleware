package com.kapcb.framework.middleware.annotation;

import com.kapcb.framework.middleware.configuration.RedisLuaConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <a>Title: EnableRedisLuaLock </a>
 * <a>Author: Kapcb <a>
 * <a>Description: EnableRedisLuaLock <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/21 17:56
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
@Import(value = RedisLuaConfiguration.class)
public @interface EnableRedisLuaLock {
}
