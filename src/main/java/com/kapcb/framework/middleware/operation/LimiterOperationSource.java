package com.kapcb.framework.middleware.operation;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * <a>Title: LimiterOperationSource </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterOperationSource <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/29 22:32
 * @since 1.0
 */
public interface LimiterOperationSource {

    Collection<LimiterOperation> getLimiterOperations(Method method, Class<?> clazz);

}
