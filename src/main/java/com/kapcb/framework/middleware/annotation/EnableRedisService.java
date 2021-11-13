package com.kapcb.framework.middleware.annotation;

import com.kapcb.framework.middleware.configuration.CustomRedisAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <a>Title: EnableRedisService </a>
 * <a>Author: Kapcb <a>
 * <a>Description: EnableRedisService <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/13 14:10
 */
@Target(value= ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Import(CustomRedisAutoConfiguration.class)
public @interface EnableRedisService {
}
