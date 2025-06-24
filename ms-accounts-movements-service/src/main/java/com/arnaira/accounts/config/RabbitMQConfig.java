package com.arnaira.accounts.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue clienteCreadoQueue(){
        return new Queue("clienteCreadoQueue", false);
    }
}
