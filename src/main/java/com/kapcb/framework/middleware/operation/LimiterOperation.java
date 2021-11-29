package com.kapcb.framework.middleware.operation;

import com.kapcb.framework.common.constants.enums.StringPool;
import com.kapcb.framework.middleware.limiter.Limiter;
import com.kapcb.framework.middleware.limiter.LimiterManager;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Map;

/**
 * <a>Title: LimiterOperation </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterOperation <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/28 22:00
 * @since 1.0
 */
public abstract class LimiterOperation<T extends Limiter> {

    private final String name;

    private final String limiterName;

    private final String keyGenerator;

    private final String limiterManager;

    private final String key;

    private final String condition;

    private final Collection<String> argumentInjects;

    private final String fallBackResolver;

    private final Map<String, Object> customerArgument;

    private final String errorHandler;

    public LimiterOperation(LimiterOperation.Builder builder) {
        this.name = builder.name;
        this.limiterName = builder.limiterName;
        this.key = builder.key;
        this.keyGenerator = builder.keyGenerator;
        this.limiterManager = builder.limiterManager;
        this.condition = builder.condition;
        this.argumentInjects = builder.argumentInjects;
        this.fallBackResolver = builder.fallBackResolver;
        this.customerArgument = builder.customerArgument;
        this.errorHandler = builder.errorHandler;
    }

    public Map<String, Object> getCustomerArgument() {
        return this.customerArgument;
    }

    public abstract Class<? extends LimiterManager<T>> getDefaultLimiterManagerClass();

    public String getErrorHandler() {
        return this.errorHandler;
    }

    public String getName() {
        return this.name;
    }

    public String getLimiterName(){
        return this.limiterManager;
    }

    public String getKeyGenerator(){
        return this.keyGenerator;
    }

    public String getLimiterManager(){
        return this.limiterManager;
    }

    public String getKey(){
        return this.key;
    }

    public String getCondition(){
        return this.condition;
    }

    public String getFallBackResolver(){
        return this.fallBackResolver;
    }

    public Collection<String> getArgumentInjects(){
        return this.argumentInjects;
    }

    @Override
    public String toString() {
        return "LimiterOperation{" +
                "name='" + name + '\'' +
                ", limiterName='" + limiterName + '\'' +
                ", keyGenerator='" + keyGenerator + '\'' +
                ", limiterManager='" + limiterManager + '\'' +
                ", key='" + key + '\'' +
                ", condition='" + condition + '\'' +
                ", argumentInjects=" + argumentInjects +
                ", fallBackResolver='" + fallBackResolver + '\'' +
                ", customerArgument=" + customerArgument +
                ", errorHandler='" + errorHandler + '\'' +
                '}';
    }

    public abstract static class Builder {

        private String name = StringPool.EMPTY_STRING.value();

        private String limiterName;

        private String key = StringPool.EMPTY_STRING.value();

        private String keyGenerator = StringPool.EMPTY_STRING.value();

        private String limiterManager = StringPool.EMPTY_STRING.value();

        private String condition = StringPool.EMPTY_STRING.value();

        private Collection<String> argumentInjects;

        private String fallBackResolver;

        private Map<String, Object> customerArgument;

        private String errorHandler;

        public void name(String name) {
            Assert.hasText(name, "name must not be empty");
            this.name = name;
        }

        public void customerArgument(Map<String, Object> customerArgument) {
            this.customerArgument = customerArgument;
        }

        public void limiterName(String limiterName) {
            this.limiterName = limiterName;
        }

        public void key(String key) {
            Assert.notNull(key, "key must not be null");
            this.key = key;
        }

        public void argumentInjects(Collection<String> argumentInjects) {
            this.argumentInjects = argumentInjects;
        }

        public void errorHandler(String errorHandler) {
            this.errorHandler = errorHandler;
        }

        public void limiterManager(String limiterManager) {
            Assert.notNull(limiterManager, "limiter manager name must not be null");
            this.limiterManager = limiterManager;
        }

        public void condition(String condition) {
            Assert.notNull(condition, "condition must not be null");
            this.condition = condition;
        }

        public void keyGenerator(String keyGenerator) {
            Assert.notNull(condition, "key generator must not be null");
            this.keyGenerator = keyGenerator;
        }

        public void fallBackResolver(String fallBackResolver) {
            this.fallBackResolver = fallBackResolver;
        }

        public abstract LimiterOperation build();
    }
}
