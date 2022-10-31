package com.sofka.carteroRabbitMQ.config;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqTopicConfig {
    @Bean
    Queue P1Queue() {
        return new Queue("P1Queue", false);
    }

    @Bean
    Queue P2Queue() {
        return new Queue("P2Queue", false);
    }

    @Bean
    Queue P3Queue() {
        return new Queue("P3Queue", false);
    }

    @Bean
    Queue P4Queue() {
        return new Queue("P4Queue", false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    Binding P1Binding(Queue P1Queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(P1Queue).to(topicExchange).with("queue.roomOdd");
    }

    @Bean
    Binding P3Binding(Queue P3Queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(P3Queue).to(topicExchange).with("queue.roomOdd");
    }

    @Bean
    Binding P1Binding1(Queue P1Queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(P1Queue).to(topicExchange).with("queue.room");
    }

    @Bean
    Binding P2Binding2(Queue P2Queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(P2Queue).to(topicExchange).with("queue.room");
    }
    @Bean
    Binding P3Bindin3(Queue P3Queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(P3Queue).to(topicExchange).with("queue.room");
    }

    @Bean
    Binding P4Binding4(Queue P4Queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(P4Queue).to(topicExchange).with("queue.room");
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        return simpleMessageListenerContainer;
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
