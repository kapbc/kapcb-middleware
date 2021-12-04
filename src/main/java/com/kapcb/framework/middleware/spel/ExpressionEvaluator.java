package com.kapcb.framework.middleware.spel;

import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.Objects;

/**
 * <a>Title: ExpressionEvaluator </a>
 * <a>Author: Kapcb <a>
 * <a>Description: ExpressionEvaluator <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 10:36
 * @since 1.0
 */
public class ExpressionEvaluator {

    private final SpelExpressionParser spelExpressionParser;

    private final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    protected ExpressionEvaluator(SpelExpressionParser spelExpressionParser) {
        Assert.notNull(spelExpressionParser, "spring expression language parser must not be null");
        this.spelExpressionParser = spelExpressionParser;
    }

    protected ExpressionEvaluator() {
        this(new SpelExpressionParser());
    }

    protected SpelExpressionParser getExpressionParser() {
        return this.spelExpressionParser;
    }

    protected ParameterNameDiscoverer getParameterNameDiscoverer() {
        return this.parameterNameDiscoverer;
    }

    protected Expression getExpression(Map<ExpressionKey, Expression> cache, AnnotatedElementKey annotatedElementKey, String springExpression) {
        ExpressionKey expressionKey = createExpressionKey(annotatedElementKey, springExpression);
        Expression expression = cache.get(expressionKey);
        if (Objects.isNull(expression)) {
            expression = getExpressionParser().parseExpression(springExpression);
            cache.put(expressionKey, expression);
        }
        return expression;
    }

    private ExpressionEvaluator.ExpressionKey createExpressionKey(AnnotatedElementKey annotatedElementKey, String expression) {
        return new ExpressionKey(expression, annotatedElementKey);
    }

    protected static class ExpressionKey implements Comparable<ExpressionEvaluator.ExpressionKey> {

        private final AnnotatedElementKey annotatedElementKey;
        private final String expression;

        protected ExpressionKey(String expression, AnnotatedElementKey annotatedElementKey) {
            Assert.notNull(expression, "expression must not be null");
            Assert.notNull(annotatedElementKey, "annotated element key must not be null");
            this.expression = expression;
            this.annotatedElementKey = annotatedElementKey;
        }

        @Override
        public int compareTo(ExpressionKey other) {
            int result = this.annotatedElementKey.toString().compareTo(other.annotatedElementKey.toString());
            if (result == 0) {
                result = this.expression.compareTo(other.expression);
            }
            return result;
        }

        @Override
        public int hashCode() {
            return this.annotatedElementKey.hashCode() * 29 + this.expression.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ExpressionKey)) {
                return false;
            }
            ExpressionKey expressionKey = (ExpressionKey) obj;
            return this.annotatedElementKey.equals(expressionKey.annotatedElementKey) && ObjectUtils.nullSafeEquals(this.expression, expressionKey.expression);
        }

        @Override
        public String toString() {
            return this.annotatedElementKey + " with expression \"" + this.expression + "\"";
        }
    }
}
