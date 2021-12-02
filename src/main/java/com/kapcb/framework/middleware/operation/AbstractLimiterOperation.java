package com.kapcb.framework.middleware.operation;

import com.kapcb.framework.common.constants.enums.StringPool;
import com.kapcb.framework.middleware.limiter.Limiter;

import java.util.Collection;

/**
 * <a>Title: AbstractLimiterOperation </a>
 * <a>Author: Kapcb <a>
 * <a>Description: AbstractLimiterOperation <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/1 21:47
 * @since 1.0
 */
public abstract class AbstractLimiterOperation<T extends Limiter> implements Operation {

    private final String name;
    private final String key;
    private final String condition;
    private final Collection<String> argumentInjects;
    private final String fallbackResolver;
    private final String errorHandler;
    private final String keyGenerator;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getCondition() {
        return this.condition;
    }

    @Override
    public Collection<String> getArgumentInjects() {
        return this.argumentInjects;
    }

    @Override
    public String getFallbackResolver() {
        return this.fallbackResolver;
    }

    @Override
    public String getErrorHandler() {
        return this.errorHandler;
    }

    @Override
    public String getKeyGenerator() {
        return this.keyGenerator;
    }

    protected AbstractLimiterOperation(Builder builder) {
        this.name = builder.name;
        this.key = builder.key;
        this.condition = builder.condition;
        this.argumentInjects = builder.argumentInjects;
        this.fallbackResolver = builder.fallbackResolver;
        this.errorHandler = builder.errorHandler;
        this.keyGenerator = builder.keyGenerator;
    }


    public abstract static class Builder {

        private String name = StringPool.EMPTY_STRING.value();
        private String key = StringPool.EMPTY_STRING.value();
        private String condition = StringPool.EMPTY_STRING.value();
        private Collection<String> argumentInjects;
        private String fallbackResolver;
        private String errorHandler;
        private String keyGenerator;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder condition(String condition) {
            this.condition = condition;
            return this;
        }

        public Builder argumentInjects(Collection<String> argumentInjects) {
            this.argumentInjects = argumentInjects;
            return this;
        }

        public Builder fallbackResolver(String fallbackResolver) {
            this.fallbackResolver = fallbackResolver;
            return this;
        }

        public Builder errorHandler(String errorHandler) {
            this.errorHandler = errorHandler;
            return this;
        }

        public Builder keyGenerator(String keyGenerator) {
            this.keyGenerator = keyGenerator;
            return this;
        }

        public abstract AbstractLimiterOperation build();
    }

}
