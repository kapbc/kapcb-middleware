package com.kapcb.framework.middleware.exception;

/**
 * <a>Title: RabbitOperationException </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RabbitOperationException <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/18 20:41
 */
public class RabbitOperationException extends RuntimeException {

    private static final long serialVersionUID = -5959990233326385129L;

    public RabbitOperationException(String message) {
        super(message);
    }

    public RabbitOperationException(Throwable throwable) {
        super(throwable);
    }

    public RabbitOperationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
