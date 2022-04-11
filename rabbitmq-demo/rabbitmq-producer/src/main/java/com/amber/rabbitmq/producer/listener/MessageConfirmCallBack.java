package com.amber.rabbitmq.producer.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author amber
 */
@Slf4j
@Component
public class MessageConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String cause) {
        log.info("correlationData={}, b={} cause={}", correlationData, b, cause);
    }
}
