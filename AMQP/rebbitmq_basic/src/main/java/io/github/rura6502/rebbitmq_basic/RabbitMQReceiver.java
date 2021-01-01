package io.github.rura6502.rebbitmq_basic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class RabbitMQReceiver {

  @RabbitHandler
  public void receive(String in) {
    System.out.printf("received : %s%n", in);
  }
  
}
