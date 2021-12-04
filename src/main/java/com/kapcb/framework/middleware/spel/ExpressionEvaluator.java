package com.kapcb.framework.middleware.spel;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.Assert;

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

    protected ParameterNameDiscoverer getParameterNameDiscoverer(){
        return this.parameterNameDiscoverer;
    }

    protected Expression getExpression(){

    }

}
