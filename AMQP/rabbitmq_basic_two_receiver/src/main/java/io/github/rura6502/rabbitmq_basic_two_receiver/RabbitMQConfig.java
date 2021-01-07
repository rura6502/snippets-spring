package io.github.rura6502.rabbitmq_basic_two_receiver;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.RabbitMQContainer;

import lombok.Getter;

@Component
public class RabbitMQConfig {
  
  @Getter
  private RabbitMQContainer container;

  @PostConstruct
  public void init() throws UnsupportedOperationException, IOException, InterruptedException {
    container = new RabbitMQContainer("rabbitmq");
    container.start();
    container.execInContainer("/opt/rabbitmq/sbin/rabbitmq-plugins", "enable", "rabbitmq_management");
  }
  
  @Bean
  public CachingConnectionFactory cachingConnectionFactory() {
    CachingConnectionFactory factory = new CachingConnectionFactory();
    factory.setHost(container.getHost());
    factory.setPort(container.getAmqpPort());
    factory.setUsername(container.getAdminUsername());
    factory.setPassword(container.getAdminPassword());
    return factory;
  }

  @Bean
  public RabbitTemplate rabbitTemplate(CachingConnectionFactory factory) {
    return new RabbitTemplate(factory);
  }

  @Bean
  public AmqpAdmin amqpAdmin(CachingConnectionFactory factory) {
    return new RabbitAdmin(factory);
  }

  @Bean
  public Queue queue() {
    return new Queue("hello");
  }
}
