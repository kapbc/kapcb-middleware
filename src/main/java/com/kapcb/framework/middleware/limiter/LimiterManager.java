package com.kapcb.framework.middleware.limiter;

import java.util.Collection;

/**
 * <a>Title: LimiterManager </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterManager <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/28 13:52
 * @since 1.0
 */
public interface LimiterManager<T extends Limiter> {

    T getLimiter(String limiterName);

    Collection<String> getLimiterNames();

}
