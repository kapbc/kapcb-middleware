package com.kapcb.framework.middleware.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <a>Title: RabbitAutoConfigurationProperties </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RabbitAutoConfigurationProperties <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/17 8:29
 */
@Data
@ConfigurationProperties(prefix = "kapcb.rabbit")
public class RabbitAutoConfigurationProperties {

    private Boolean enable = false;

    private Boolean mandatory = true;

}
