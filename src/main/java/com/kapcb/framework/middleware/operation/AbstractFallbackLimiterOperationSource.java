package com.kapcb.framework.middleware.operation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.MethodClassKey;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <a>Title: AbstractFallbackLimiterOperationSource </a>
 * <a>Author: Kapcb <a>
 * <a>Description: AbstractFallbackLimiterOperationSource <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/29 22:34
 * @since 1.0
 */
@Slf4j
public abstract class AbstractFallbackLimiterOperationSource implements LimiterOperationSource {

    private static final Collection<LimiterOperation> NULL_CACHING_ATTRIBUTE = Collections.emptyList();

    private final Map<Object, Collection<LimiterOperation>> ATTRIBUTE_CACHE = new ConcurrentHashMap<>(8);

    public AbstractFallbackLimiterOperationSource() {

    }

    @Override
    public Collection<LimiterOperation> getLimiterOperations(Method method, Class<?> clazz) {
        if (Objects.equals(method.getDeclaringClass(), Object.class)) {
            return null;
        } else {
            Object cacheKey = this.getLimiterKey(method, clazz);
            Collection<LimiterOperation> cached = this.ATTRIBUTE_CACHE.get(cacheKey);
            if (cached != null) {
                return cached != NULL_CACHING_ATTRIBUTE ? cached : null;
            } else {
                Collection<LimiterOperation> cachedOperation = this.computeLimiterOperations(method, clazz);
                if (cachedOperation != null) {
                    this.ATTRIBUTE_CACHE.put(cacheKey, cachedOperation);
                } else {
                    this.ATTRIBUTE_CACHE.put(cacheKey, NULL_CACHING_ATTRIBUTE);
                }
                return cachedOperation;
            }
        }
    }

    private Collection<LimiterOperation> computeLimiterOperations(Method method, Class<?> clazz) {
        if (this.allowPublicMethodOnly() && !Modifier.isPublic(clazz.getModifiers())) {
            return null;
        } else {
            Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, clazz);
            Collection<LimiterOperation> limiterOperations = this.findLimiterOperations(mostSpecificMethod);
            if (limiterOperations != null) {
                return limiterOperations;
            } else {
                limiterOperations = this.findLimiterOperations(mostSpecificMethod.getDeclaringClass());
                if (limiterOperations != null && ClassUtils.isUserLevelMethod(method)) {
                    return limiterOperations;
                } else {
                    if (mostSpecificMethod != method) {
                        limiterOperations = this.findLimiterOperations(method);
                        if (limiterOperations != null) {
                            return limiterOperations;
                        }
                        limiterOperations = this.findLimiterOperations(method.getDeclaringClass());
                        if (limiterOperations != null && ClassUtils.isUserLevelMethod(method)) {
                            return limiterOperations;
                        }
                    }
                }
                return null;
            }
        }
    }

    protected Object getLimiterKey(Method method, Class<?> clazz) {
        return new MethodClassKey(method, clazz);
    }

    protected boolean allowPublicMethodOnly() {
        return false;
    }

    protected abstract Collection<LimiterOperation> findLimiterOperations(Method method);

    protected abstract Collection<LimiterOperation> findLimiterOperations(Class<?> clazz);
}
