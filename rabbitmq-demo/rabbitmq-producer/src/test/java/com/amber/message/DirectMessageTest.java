package com.amber.message;

import com.alibaba.fastjson.JSONObject;
import com.amber.rabbitmq.producer.ProducerApplication;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = ProducerApplication.class)
public class DirectMessageTest {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void sendDirectMessage() {
        for (int i = 0; i < 5; i ++) {
            JSONObject message = new JSONObject();
            message.put("type", "direct message");
            rabbitTemplate.convertAndSend("direct-exchange","direct-routingKey-", "123");
            log.info("send successful!");
        }
    }


    @Test
    public void sendTopicMessage() {
        for (int i = 0; i < 50; i ++) {
            JSONObject message = new JSONObject();
            message.put("type", "topic message");
            rabbitTemplate.convertAndSend("topic-exchange","topic-routingKey", message.toJSONString());
            log.info("send successful!");
        }
    }
}
