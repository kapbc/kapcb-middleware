package com.kapcb.framework.middleware.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <a>Title: RedissonConfigurationProperties </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RedissonConfigurationProperties <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/21 16:53
 */
@Data
@ConfigurationProperties(prefix = "kapcb.redisson")
public class RedissonConfigurationProperties {

    private int timeout = 3000;

    private Boolean enable;

    private Boolean single;

    private String address;

    private String username;

    private String password;

    private int connectionPoolSize = 64;

    private int connectionMinimumIdleSize = 10;

    private int slaveConnectionPoolSize = 250;

    private int masterConnectionPoolSize = 250;

    private String[] sentinelAddress;

    private String masterName;

}
