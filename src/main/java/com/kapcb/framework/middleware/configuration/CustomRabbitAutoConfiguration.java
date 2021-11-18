package com.kapcb.framework.middleware.configuration;

import com.kapcb.framework.middleware.properties.RabbitAutoConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.Objects;

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
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("RabbitConfirmCallback        correlationData : {}", correlationData);
            log.info("RabbitConfirmCallback        ack : {}", ack);
            log.info("RabbitConfirmCallback        cause : {}", cause);
            if (ack && Objects.nonNull(correlationData)) {
                String id = correlationData.getId();
                SettableListenableFuture<CorrelationData.Confirm> future = correlationData.getFuture();
                Message returnedMessage = correlationData.getReturnedMessage();
                Class<? extends CorrelationData> aClass = correlationData.getClass();
                log.info("the correlationData's id is : {}", id);
                log.info("the correlationData's future is : {}", future);
                log.info("the correlationData's returnedMessage is : {}", returnedMessage);
                log.info("the correlationData's aClass is : {}", aClass);
                log.info("the rabbit exchange have received the message!");
            } else {
                log.info("send message to rabbitmq exchange error!");
                log.info("send message to exchange fail, the reason is : {}", cause);
            }
        });

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("RabbitReturnCallback        message : {}", message);
            log.info("RabbitReturnCallback        replyCode : {}", replyCode);
            log.info("RabbitReturnCallback        replyText : {}", replyText);
            log.info("RabbitReturnCallback        exchange : {}", exchange);
            log.info("RabbitReturnCallback        routingKey : {}", routingKey);
            log.info("the message consumer haven't received the message!");
        });
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
