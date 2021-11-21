package com.kapcb.framework.middleware.annotation;

import com.kapcb.framework.middleware.configuration.DistributedLockConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <a>Title: EnableDistributedLockService </a>
 * <a>Author: Kapcb <a>
 * <a>Description: EnableDistributedLockService <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/21 15:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
@Import(value = DistributedLockConfiguration.class)
public @interface EnableDistributedLockService {
}
