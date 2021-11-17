package com.kapcb.framework.middleware.configuration;

import com.kapcb.framework.middleware.properties.RabbitAutoConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * <a>Title: CustomRabbitAutoConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description: CustomRabbitAutoConfiguration <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/13 14:14
 */
@Slf4j
@EnableConfigurationProperties(RabbitAutoConfigurationProperties.class)
@ConditionalOnProperty(prefix = "kapcb.rabbit.enable", havingValue = "true", matchIfMissing = false)
public class CustomRabbitAutoConfiguration {

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean(value = "rabbitTemplate")
    @Scope("singleton")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        log.info("[ begin to add rabbit template into spring application context ]");
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
