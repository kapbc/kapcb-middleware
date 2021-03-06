package com.kapcb.framework.middleware.configuration;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kapcb.framework.common.constants.enums.StringPool;
import com.kapcb.framework.middleware.properties.RedisProperties;
import com.kapcb.framework.middleware.service.IRedisService;
import com.kapcb.framework.middleware.service.impl.RedisServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * <a>Title: CustomRedisAutoConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description: CustomRedisAutoConfiguration <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/12 22:02
 */
@Slf4j
@Configuration
@EnableCaching
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
@ConditionalOnProperty(value = "kapcb.redis.enable", havingValue = "true", matchIfMissing = true)
public class CustomRedisConfiguration extends CachingConfigurerSupport {

    /**
     * ?????? redis ?????????????????????????????????2??????
     * ??????@Cacheable??????????????????
     *
     * @return RedisCacheConfiguration
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        FastJsonRedisSerializer<Object> redisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer)).entryTtl(Duration.ofHours(2));
        return configuration;
    }

    @SuppressWarnings("all")
    @Bean(value = "redisTemplate")
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        log.info("[ begin to add redis template into spring application context ]");
        // ??????????????????, ??????????????????Redis?????????
        lettuceConnectionFactory.setShareNativeConnection(false);
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        ObjectMapper objectMapper = new ObjectMapper();
        BasicPolymorphicTypeValidator polymorphicTypeValidator = BasicPolymorphicTypeValidator.builder().allowIfBaseType(Object.class).build();

        // ???????????????????????????field,get???set,????????????????????????ANY???????????????private???public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // ????????????????????????????????????????????????final????????????final?????????????????????String,Integer??????????????????
        // ????????????????????????????????????????????????
        objectMapper.activateDefaultTyping(polymorphicTypeValidator, ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.registerModule(new Jdk8Module());
        // ??????Module
        objectMapper.registerModule(new JavaTimeModule());
        // ???????????????????????????
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    @ConditionalOnBean(value = RedisTemplate.class)
    public IRedisService iRedisService(@Qualifier("redisTemplate") RedisTemplate<String, Object> redisTemplate) {
        return new RedisServiceImpl(redisTemplate);
    }

    /**
     * ??????????????????Redis??????????????????????????????????????????????????????
     *
     * @return CacheErrorHandler
     */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        log.info("initial -> [ Redis CacheErrorHandler ]");
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheGetError???key -> [{}]", key, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                log.error("Redis occur handleCachePutError???key -> [{}]???value -> [{}]", key, value, e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheEvictError???key -> [{}]", key, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("Redis occur handleCacheClearError???", e);
            }
        };
    }

    /**
     * ???????????????key???????????????????????????????????????
     * ????????????+?????????+???????????????????????????????????????key,??????@Cacheable??????value???????????????key???????????????
     *
     * @return KeyGenerator
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, param) -> {
            Map<String, Object> container = new HashMap<>(2);
            Class<?> targetClass = target.getClass();
            container.put(StringPool.CLASS.value(), targetClass);
            container.put(StringPool.METHOD_NAME.value(), method.getName());
            container.put(StringPool.PACKAGE.value(), targetClass.getPackage());
            for (int i = 0; i < param.length; i++) {
                container.put(String.valueOf(i), param[i]);
            }
            String jsonString = JSON.toJSONString(container);
            return DigestUtil.sha256Hex(jsonString);
        };
    }
}
