package com.kapcb.framework.middleware.spel;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.Assert;

import java.lang.reflect.AnnotatedElement;
import java.util.Map;

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

    protected Expression getExpression(Map<ExpressionKey, Expression> cache) {

    }

    protected static class ExpressionKey implements Comparable<ExpressionEvaluator.ExpressionKey> {

        private final AnnotatedElement annotatedElement;
        private final String expression;

        protected ExpressionKey(String expression, AnnotatedElement annotatedElement) {
            Assert.notNull(expression, "expression must not be null");
            Assert.notNull(annotatedElement, "annotated element must not be null");
            this.expression = expression;
            this.annotatedElement = annotatedElement;
        }

        @Override
        public int compareTo(ExpressionEvaluator.ExpressionKey other) {
            int result = this.annotatedElement.toString().compareTo(other.annotatedElement.toString());
            if (result == 0) {
                result = this.expression.compareTo(other.expression);
            }
            return result;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }


}
