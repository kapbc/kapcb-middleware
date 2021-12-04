package com.kapcb.framework.middleware.exception;

import org.springframework.expression.EvaluationException;

/**
 * <a>Title: VariableNotAvailableException </a>
 * <a>Author: Kapcb <a>
 * <a>Description: VariableNotAvailableException <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 16:08
 * @since 1.0
 */
public class VariableNotAvailableException extends EvaluationException {

    private final String name;

    public final String getName() {
        return this.name;
    }

    public VariableNotAvailableException(String name) {
        super("variable not available");
        this.name = name;
    }
}
