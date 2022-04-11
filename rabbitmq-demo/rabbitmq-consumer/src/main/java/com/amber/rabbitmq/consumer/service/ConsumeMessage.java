package com.amber.rabbitmq.consumer.service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ConsumeMessage {

    //    @RabbitListener(queues = "direct-queue")
//    public void directMessage(String messgae) {
//        log.info("revice direct-queue message = {}", messgae);
//    }
    @RabbitListener(queues = "direct-queue")
    public void directMessageAcknowledgeMode(Message message, Channel channel) throws IOException {
        log.info("revice direct-queue message = {}", message);
        // requeue 是否重回队列，一般情况下都不重回队列
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("message basic ack true DeliveryTag={}" , message.getMessageProperties().getDeliveryTag());
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }


    @RabbitListener(queues = "topic-queue-1")
    public void topic1Message(String messgae) {
        log.info("revice topic-queue-1 message = {}", messgae);
    }

    @RabbitListener(queues = "topic-queue-2", concurrency = "3")
    public void topic2Message(String messgae) {
        log.info("revice topic-queue-2 message = {}", messgae);
    }


    @RabbitListener(queues = "fanout-queue", concurrency = "3")
    public void fanoutQueueMessage(String messgae) {
        log.info("revice fanoutQueueMessage message = {}", messgae);
    }

    @RabbitListener(queues = "fanout-queue-2", concurrency = "3")
    public void fanoutQueue2Message(String messgae) {
        log.info("revice fanoutQueue2Message message = {}", messgae);
    }

}
