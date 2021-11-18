package com.kapcb.framework.middleware.exception;

/**
 * <a>Title: RedisOperationException </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RedisOperationException <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/18 20:41
 */
public class RedisOperationException extends RuntimeException {

    private static final long serialVersionUID = -5959990233326385129L;

    public RedisOperationException(String message) {
        super(message);
    }

    public RedisOperationException(Throwable throwable) {
        super(throwable);
    }

    public RedisOperationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
