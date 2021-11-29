package com.kapcb.framework.middleware.annotation;

import com.kapcb.framework.middleware.limiter.Limiter;
import com.kapcb.framework.middleware.operation.LimiterOperation;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * <a>Title: LimiterAnnotationParser </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterAnnotationParser <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/29 8:30
 * @since 1.0
 */
public interface LimiterAnnotationParser<T extends Limiter> {

    Collection<LimiterOperation<T>> parserLimiterAnnotation(Class<?> clazz);

    Collection<LimiterOperation<T>> parserLimiterAnnotation(Method method);

}
