package com.kapcb.framework.middleware.operation;

import java.util.Collection;

/**
 * <a>Title: Operation </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Operation <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/1 21:34
 * @since 1.0
 */
public interface Operation {

    String getName();

//    void setName(String name);

    String getKey();

//    void setKey(String key);

    String getCondition();

//    void setCondition(String condition);

    Collection<String> getArgumentInjects();

//    void setArgumentInjects(Collection<String> argumentInjects);

    String getFallbackResolver();

//    void setFallbackResolver(String fallbackResolver);

    String getErrorHandler();

//    void setErrorHandler(String errorHandler);

    String getKeyGenerator();

//    void setKeyGenerator(String keyGenerator);

}
