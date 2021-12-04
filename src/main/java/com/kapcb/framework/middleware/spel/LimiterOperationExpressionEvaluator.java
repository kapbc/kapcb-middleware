package com.kapcb.framework.middleware.spel;

import com.kapcb.framework.middleware.limiter.Limiter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <a>Title: LimiterOperationExpressionEvaluator </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterOperationExpressionEvaluator <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 15:55
 * @since 1.0
 */
public class LimiterOperationExpressionEvaluator extends ExpressionEvaluator {

    private final Map<ExpressionKey, Expression> keyCache = new ConcurrentHashMap<>(32);

    private final Map<ExpressionKey, Expression> conditionCache = new ConcurrentHashMap<>(32);

    private final Map<ExpressionKey, Expression> unlessCache = new ConcurrentHashMap<>(32);

    public BaseEvaluationContext createEvaluationContext(Limiter limiter, Method method, Object[] args, Object target, Class<?> targetClass, Method targetMethod, Map<String, Object> injectArgs, BeanFactory beanFactory) {
        LimiterExpressionRootObject rootObject = new LimiterExpressionRootObject(limiter, method, args, target, targetClass);
        BaseEvaluationContext baseEvaluationContext = new BaseEvaluationContext(rootObject, targetMethod, args, getParameterNameDiscoverer());

        for (String key : injectArgs.keySet()) {
            baseEvaluationContext.setVariable(key, injectArgs.get(key));
        }

        if (Objects.nonNull(beanFactory)) {
            baseEvaluationContext.setBeanResolver(new BeanFactoryResolver(beanFactory));
        }
        return baseEvaluationContext;
    }

    public Object key(String keyExpression, AnnotatedElementKey annotatedElementKey, EvaluationContext evaluationContext) {
        return getExpression(this.keyCache, annotatedElementKey, keyExpression).getValue(evaluationContext);
    }

    public Boolean condition(String conditionExpression, AnnotatedElementKey annotatedElementKey, EvaluationContext evaluationContext) {
        return getExpression(this.conditionCache, annotatedElementKey, conditionExpression).getValue(evaluationContext, Boolean.class);
    }

    public Boolean unless(String unlessExpression, AnnotatedElementKey annotatedElementKey, EvaluationContext evaluationContext) {
        return getExpression(this.unlessCache, annotatedElementKey, unlessExpression).getValue(evaluationContext, Boolean.class);
    }

    void clear() {
        this.keyCache.clear();
        this.conditionCache.clear();
        this.unlessCache.clear();
    }

}
