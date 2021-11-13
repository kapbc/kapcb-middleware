package com.kapcb.framework.middleware.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <a>Title: EnableRabbitService </a>
 * <a>Author: Kapcb <a>
 * <a>Description: EnableRabbitService <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/13 14:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
public @interface EnableRabbitService {
}
