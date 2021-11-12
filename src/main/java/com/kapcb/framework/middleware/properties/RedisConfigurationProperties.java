package com.kapcb.framework.middleware.properties;

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
@ConfigurationProperties(prefix = "kapcb.redis")
public class RedisConfigurationProperties {
}
