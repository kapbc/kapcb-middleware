package com.kapcb.framework.middleware.configuration;

import com.kapcb.framework.middleware.operation.LimiterOperationSource;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * <a>Title: LimiterOperationSourcePointcut </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterOperationSourcePointcut <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/30 23:16
 * @since 1.0
 */
public abstract class LimiterOperationSourcePointcut extends StaticMethodMatcherPointcut implements Serializable {

    public LimiterOperationSourcePointcut() {
    }

    protected abstract LimiterOperationSource getLimiterOperationSource();

    @Override
    public boolean matches(Method method, Class<?> clazz) {
        LimiterOperationSource limiterOperationSource = this.getLimiterOperationSource();
        return Objects.nonNull(limiterOperationSource) && CollectionUtils.isNotEmpty(limiterOperationSource.getLimiterOperations(method, clazz));
    }

    @Override
    public int hashCode() {
        return LimiterOperationSourcePointcut.class.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " : " + this.getLimiterOperationSource();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof LimiterOperationSourcePointcut)) {
            return false;
        } else {
            LimiterOperationSourcePointcut limiterOperationSourcePointcut = (LimiterOperationSourcePointcut) obj;
            return ObjectUtils.nullSafeEquals(this.getLimiterOperationSource(), limiterOperationSourcePointcut.getLimiterOperationSource());
        }
    }
}
