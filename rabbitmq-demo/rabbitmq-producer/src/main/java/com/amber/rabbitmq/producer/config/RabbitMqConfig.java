package com.amber.rabbitmq.producer.config;



import com.amber.rabbitmq.producer.listener.MessageConfirmCallBack;
import com.amber.rabbitmq.producer.listener.MessageReturnCallBack;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * RabbitMQ配置类
 * @author amber
 */
@Configuration
public class RabbitMqConfig {
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(new MessageConfirmCallBack());
        rabbitTemplate.setReturnCallback(new MessageReturnCallBack());
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }
    @Bean
    public Queue queue() {
        return new Queue("direct-queue", true);
    }

    @Bean
    public Exchange directExchange() {
        return new DirectExchange("direct-exchange", true, false);
    }

    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with("direct-routingKey").noargs();
    }


    @Bean
    public Queue topicQueue1() {
        return new Queue("topic-queue-1", true);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topic-queue-2", true);
    }

    @Bean
    public Exchange topicExchange() {
        return new TopicExchange("topic-exchange", true, false);
    }

    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.1.").noargs();
    }
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.#").noargs();
    }

    @Bean
    public Queue fanoutQueue() {
        return new Queue("fanout-queue", true);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout-queue-2", true);
    }

    @Bean
    public Exchange fanoutExchange() {
        return new FanoutExchange("fanout-exchange", true, false);
    }

    @Bean
    public Binding fanoutBinding() {
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange()).with("fanout.test").noargs();
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange()).with("fanout.test.2").noargs();
    }
}
