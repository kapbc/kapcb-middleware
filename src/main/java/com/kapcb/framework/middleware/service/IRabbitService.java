package com.kapcb.framework.middleware.service;

import org.springframework.amqp.rabbit.connection.CorrelationData;

import java.util.Map;

/**
 * <a>Title: IRabbitService </a>
 * <a>Author: Kapcb <a>
 * <a>Description: IRabbitService <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/18 21:22
 */
public interface IRabbitService {

    /**
     * send message
     *
     * @param exchange   String
     * @param routingKey String
     * @param message    String
     * @return boolean
     */
    boolean sendMessage(String exchange, String routingKey, Object message);

    /**
     * send message with correlation
     *
     * @param exchange        String
     * @param routingKey      String
     * @param message         String
     * @param correlationData CorrelationData
     * @return boolean
     */
    boolean sendMessage(String exchange, String routingKey, Map<String, Object> message, CorrelationData correlationData);

    /**
     * send message with correlation
     *
     * @param exchange        String
     * @param routingKey      String
     * @param message         String
     * @param correlationData CorrelationData
     * @return boolean
     */
    boolean sendMessage(String exchange, String routingKey, Object message, CorrelationData correlationData);
}
