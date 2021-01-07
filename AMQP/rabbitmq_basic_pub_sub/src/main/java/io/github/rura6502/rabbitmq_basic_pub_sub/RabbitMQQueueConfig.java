package io.github.rura6502.rabbitmq_basic_pub_sub;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfig {
  
  @Bean
  public FanoutExchange fanout() {
    return new FanoutExchange("test");
  }

  @Bean
  public Queue autoDeleteQueue1() {
    return new AnonymousQueue();
  }

  @Bean
  public Queue autoDeleteQueue2() {
    return new AnonymousQueue();
  }

  @Bean
  public Binding binding1(Queue autoDeleteQueue1, FanoutExchange fanout) {
    return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
  }

  @Bean
  public Binding binding2(Queue autoDeleteQueue2, FanoutExchange fanout) {
    return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
  }

  @Bean
  public RabbitMQReceiver receiver() {
    return new RabbitMQReceiver();
  }
}
