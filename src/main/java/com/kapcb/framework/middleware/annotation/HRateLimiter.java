package com.kapcb.framework.middleware.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <a>Title: HRateLimiter </a>
 * <a>Author: Kapcb <a>
 * <a>Description: HRateLimiter <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/29 21:46
 * @since 1.0
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface HRateLimiter {

    String value() default "default";

    String limiterName() default "default";

    String[] keys() default {};

    double qps() default 1.0d;

    String keyGenerator() default "";

    String limiterManager() default "";

    String condition() default "";

    String[] argInjects() default {};

    String fallBackResolver() default "";

}
