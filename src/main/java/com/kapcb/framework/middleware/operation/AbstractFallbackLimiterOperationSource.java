package com.kapcb.framework.middleware.operation;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.MethodClassKey;

import java.lang.reflect.Method;
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
            Object limiterKey = this.getLimiterKey(method, clazz);
            Collection<LimiterOperation> limiterOperations = this.ATTRIBUTE_CACHE.get(limiterKey);
            if (CollectionUtils.isNotEmpty(limiterOperations)) {

            }
        }

        return null;
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
