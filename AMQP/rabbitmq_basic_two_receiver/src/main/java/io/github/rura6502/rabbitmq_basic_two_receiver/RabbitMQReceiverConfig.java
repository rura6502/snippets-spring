package io.github.rura6502.rabbitmq_basic_two_receiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQReceiverConfig {

  @Bean
  public RabbitMQReceiver receiver1() {
    return new RabbitMQReceiver("001");
  }

  @Bean
  public RabbitMQReceiver receiver2() {
    return new RabbitMQReceiver("002");
  }

  
}
