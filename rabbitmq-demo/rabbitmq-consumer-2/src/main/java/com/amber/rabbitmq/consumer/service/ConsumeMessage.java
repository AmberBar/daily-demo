package com.amber.rabbitmq.consumer.service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
public class ConsumeMessage {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queues = "direct-queue")
    public void directMessageAcknowledgeMode(Message message, Channel channel, @Header("spring_returned_message_correlation") String messageId) throws IOException {
        log.info("revice direct-queue message = {}", message);
        // requeue 是否重回队列，一般情况下都不重回队列
        try {
            throw new Exception("test error");
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//            log.info("message basic ack true DeliveryTag={}" , message.getMessageProperties().getDeliveryTag());
        } catch (Exception e) {
            Long increment = stringRedisTemplate.opsForValue().increment(messageId);
            if (Objects.nonNull(increment) && 4 >= increment) {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            }
        }

    }

}
