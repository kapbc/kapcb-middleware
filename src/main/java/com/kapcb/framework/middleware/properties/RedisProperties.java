package com.kapcb.framework.middleware.properties;

import com.kapcb.framework.middleware.enums.RedisSerializerEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <a>Title: RedisConfigurationProperties </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RedisConfigurationProperties <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/12 22:03
 */
@Data
@ConfigurationProperties(prefix = RedisProperties.PREFIX)
public class RedisProperties {

    public static final String PREFIX = "kapcb.redis";

    private Boolean enable = false;

    /**
     * Redis存储对象序列/反序列化器
     * 默认：{@linkplain RedisSerializerEnum#JACKSON}
     */
    private RedisSerializerEnum redisSerializerEnum = RedisSerializerEnum.JACKSON;

}
