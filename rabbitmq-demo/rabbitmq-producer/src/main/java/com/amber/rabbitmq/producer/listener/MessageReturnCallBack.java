package com.amber.rabbitmq.producer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author amber
 */
@Slf4j
@Component
public class MessageReturnCallBack implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("================start=====================");
        log.info("message={}", message);
        log.info("replyCode={}", replyCode);
        log.info("replyText={}", replyText);
        log.info("exchange={}", exchange);
        log.info("routingKey={}", routingKey);
        log.info("===============end======================");
    }
}
