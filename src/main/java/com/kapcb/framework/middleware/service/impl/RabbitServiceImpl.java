package com.kapcb.framework.middleware.service.impl;

import com.kapcb.framework.middleware.service.IRabbitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 * <a>Title: RabbitServiceImpl </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RabbitServiceImpl <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/18 21:22
 */
@Slf4j
public class RabbitServiceImpl implements IRabbitService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public boolean sendMessage(String exchange, String routingKey, Object message) {
        boolean result = false;
        try {
            String messageId = UUID.randomUUID().toString();
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(messageId);
            log.info("the messageId is : " + messageId);
            rabbitTemplate.convertSendAndReceive(exchange, routingKey, message, correlationData);
            result = true;
        } catch (Exception e) {
            log.error("send direct message error, the exception message is : " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean sendMessage(String exchange, String routingKey, Map<String, Object> message, CorrelationData correlationData) {
        boolean result = false;
        try {
            String messageId = UUID.randomUUID().toString();
            correlationData.setId(messageId);
            log.info("the messageId is : " + messageId);
            rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
            result = true;
        } catch (Exception e) {
            log.error("send direct message error, the exception message is : " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean sendMessage(String exchange, String routingKey, Object message, CorrelationData correlationData) {
        boolean result = false;
        try {
            String messageId = UUID.randomUUID().toString();
            correlationData.setId(messageId);
            log.info("the messageId is : " + messageId);
            rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
            result = true;
        } catch (Exception e) {
            log.error("send fanout message error, the exception message is : " + e.getMessage());
        }
        return result;
    }
}
