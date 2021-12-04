package com.kapcb.framework.middleware.spel;

import com.kapcb.framework.middleware.exception.VariableNotAvailableException;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * <a>Title: EvaluationContext </a>
 * <a>Author: Kapcb <a>
 * <a>Description: EvaluationContext <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 16:05
 * @since 1.0
 */
public class BaseEvaluationContext extends MethodBasedEvaluationContext {

    private final Set<String> unavailableVariables = new HashSet<>(1);

    public BaseEvaluationContext(Object rootObject, Method method, Object[] arguments, ParameterNameDiscoverer parameterNameDiscoverer) {
        super(rootObject, method, arguments, parameterNameDiscoverer);
    }

    public void addUnavailableVariables(String name) {
        this.unavailableVariables.add(name);
    }

    @Override
    public Object lookupVariable(String name) {
        if (this.unavailableVariables.contains(name)) {
            throw new VariableNotAvailableException(name);
        }
        return super.lookupVariable(name);
    }
}
