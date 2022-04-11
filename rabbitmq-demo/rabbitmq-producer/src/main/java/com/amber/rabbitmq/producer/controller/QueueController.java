package com.amber.rabbitmq.producer.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RequestMapping("/send/message")
@RestController
@Slf4j
public class QueueController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("direct")
    public void sendDirectMessage() {
        for (int i = 0; i < 5; i++) {
            JSONObject message = new JSONObject();
            message.put("type", "direct message");
            rabbitTemplate.convertAndSend("direct-exchange", "direct-routingKey", message.toJSONString());
            log.info("message successful!");
        }
    }

    @RequestMapping("topic")
    public void sendTopicMessage() {
        for (int i = 0; i < 500; i++) {
            JSONObject message = new JSONObject();
            message.put("type", "topic message" + i);
            rabbitTemplate.convertAndSend("topic-exchange", "topic.1", message.toJSONString());
            log.info("message successful!");
        }
    }

    @RequestMapping("fanout")
    public void sendFanoutMessage() {
        for (int i = 0; i < 50; i++) {
            JSONObject message = new JSONObject();
            message.put("type", "fanout message" + i);
            rabbitTemplate.convertAndSend("fanout-exchange", "ignore-key", message.toJSONString());
            log.info("message successful!");
        }
    }

    @RequestMapping("message-confirm")
    public void messageConfirm() {
        for (int i = 0; i < 10; i++) {
            JSONObject message = new JSONObject();
            message.put("goods", "apple");
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId("message-id" + i);
            Message messageText = new Message(message.getBytes(StandardCharsets.UTF_8.name()), null);
            correlationData.setReturnedMessage(messageText);
            // 错误的转发器名称，会触发publish ConfirmCallback
            rabbitTemplate.convertAndSend("direct-exchange1", "ignore-key", message.toJSONString(), correlationData);
        }
    }

    @RequestMapping("message-return")
    public void returnMessage() {
        for (int i = 0; i < 10; i++) {
            JSONObject message = new JSONObject();
            message.put("goods", "apple");
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId("message-return-id" + i);
            Message messageText = new Message(message.getBytes(StandardCharsets.UTF_8.name()), null);
            correlationData.setReturnedMessage(messageText);
            // 错误的routingKey
            rabbitTemplate.convertAndSend("direct-exchange", "ignore-key-1", message.toJSONString(), correlationData);
        }
    }


    @RequestMapping("message-ack")
    public void ackMessage() {
        for (int i = 0; i < 1; i++) {
            JSONObject message = new JSONObject();
            message.put("goods", "apple-ack");

            // 错误的routingKey
            rabbitTemplate.convertAndSend("direct-exchange", "direct-routingKey", message.toJSONString(),
                    new CorrelationData(UUID.randomUUID().toString()));
        }
    }
}
