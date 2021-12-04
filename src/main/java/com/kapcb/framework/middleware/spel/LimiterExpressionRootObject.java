package com.kapcb.framework.middleware.spel;

import com.kapcb.framework.middleware.limiter.Limiter;

import java.lang.reflect.Method;

/**
 * <a>Title: LimiterExpressionRootObject </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterExpressionRootObject <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 16:00
 * @since 1.0
 */
public class LimiterExpressionRootObject {

    private final Limiter limiter;

    private final Method method;

    private final Object[] args;

    private final Object target;

    private final Class<?> targetClass;

    public LimiterExpressionRootObject(Limiter limiter, Method method, Object[] args, Object target, Class<?> targetClass) {
        this.limiter = limiter;
        this.method = method;
        this.args = args;
        this.target = target;
        this.targetClass = targetClass;
    }

    public Limiter getLimiter() {
        return limiter;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }

    public Object getTarget() {
        return target;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }
}
