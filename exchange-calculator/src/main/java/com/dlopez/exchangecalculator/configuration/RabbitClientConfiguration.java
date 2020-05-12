package com.dlopez.exchangecalculator.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitClientConfiguration {

    @Value("${rabbitmq.audit-queue}")
    public String queueName;

    @Value("${rabbitmq.audit-queue-exchange}")
    private String exchageName;
 
    @Value("${rabbitmq.host}")
    private String host;
 
    @Value("${rabbitmq.port}")
    private Integer port;
 
    @Value("${rabbitmq.user}")
    private String user;
 
    @Value("${rabbitmq.password}")
    private String password;
 
    @Value("${rabbitmq.virtualhost}")
    private String virtualhost;
 
    @Bean
    public ConnectionFactory connectionFactory() throws Exception {
        final CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(user);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualhost);
        return connectionFactory;
    }

    @Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
 
    @Bean
    public RabbitTemplate rabbitTemplate() throws Exception {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
    }
 
    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }
 
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchageName);
    }
 
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }
}