package com.kapcb.framework.middleware.operation;

import com.kapcb.framework.middleware.parser.LimiterAnnotationParser;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <a>Title: AnnotationCacheOperationSource </a>
 * <a>Author: Kapcb <a>
 * <a>Description: AnnotationCacheOperationSource <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/1 8:27
 * @since 1.0
 */
public class AnnotationCacheOperationSource extends AbstractFallbackLimiterOperationSource implements Serializable {

    public final boolean publicMethodOnly;

    private final Set<LimiterAnnotationParser> limiterAnnotationParsers;

    public AnnotationCacheOperationSource() {
        this(true);
    }

    public AnnotationCacheOperationSource(boolean publicMethodOnly) {
        this.publicMethodOnly = publicMethodOnly;
        this.limiterAnnotationParsers = new LinkedHashSet<>(1);
        this.limiterAnnotationParsers.add(null);
    }

    @Override
    protected Collection<LimiterOperation> findLimiterOperations(Method method) {
        return null;
    }

    @Override
    protected Collection<LimiterOperation> findLimiterOperations(Class<?> clazz) {
        return null;
    }

}
