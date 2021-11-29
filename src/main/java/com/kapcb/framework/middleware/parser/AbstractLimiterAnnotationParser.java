package com.kapcb.framework.middleware.parser;

import com.kapcb.framework.middleware.limiter.Limiter;
import com.kapcb.framework.middleware.operation.LimiterOperation;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * <a>Title: AbstractLimiterAnnotationParser </a>
 * <a>Author: Kapcb <a>
 * <a>Description: AbstractLimiterAnnotationParser <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/29 21:39
 * @since 1.0
 */
public abstract class AbstractLimiterAnnotationParser<T extends Limiter> implements LimiterAnnotationParser<T> {

    @Override
    public Collection<LimiterOperation<T>> parserLimiterAnnotation(Class<?> clazz) {
        return parserAnnotation(clazz);
    }

    @Override
    public Collection<LimiterOperation<T>> parserLimiterAnnotation(Method method) {
        return parserAnnotation(method);
    }

    public abstract Collection<LimiterOperation<T>> parserAnnotation(AnnotatedElement annotatedElement);
}
