package com.kapcb.framework.middleware.configuration;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;

/**
 * <a>Title: RabbitDeadLetterQueueConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RabbitDeadLetterQueueConfiguration <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/24 8:31
 */
public abstract class RabbitDeadLetterQueueConfiguration implements DeadLetterQueue {

    public abstract Queue deadQueue(String deadQueue, String deadExchange, long ttl);


}
