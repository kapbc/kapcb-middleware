package com.kapcb.framework.middleware.enums;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * <a>Title: RedisSerializerEnum </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RedisSerializerEnum <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/29 23:13
 * @since 1.0
 */
public enum RedisSerializerEnum {

    /**
     * Redis序列化策略
     * 序列化任何 {@link java.io.Serializable} 对象
     */
    JDK {
        @Override
        public RedisSerializer<Object> redisSerializer() {
            return new JdkSerializationRedisSerializer();
        }
    },

    FASTJSON {
        @Override
        public RedisSerializer<Object> redisSerializer() {
            return new GenericFastJsonRedisSerializer();
        }
    },

    JACKSON {
        @Override
        public RedisSerializer<Object> redisSerializer() {
            return new GenericJackson2JsonRedisSerializer();
        }
    };

    public abstract RedisSerializer<Object> redisSerializer();

}
