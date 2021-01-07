package io.github.rura6502.rabbitmq_basic_two_receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import lombok.RequiredArgsConstructor;

@RabbitListener(queues = "hello")
@RequiredArgsConstructor
public class RabbitMQReceiver {

  private final String receiverId;

  @RabbitHandler
  public void receive(String in) throws InterruptedException {
    
    System.out.printf("%s received : %s%n", receiverId, in);
    if (in.contains("error")) {
      throw new RuntimeException("error!!");
    }
    Thread.sleep(5000);
    System.out.printf("%s process done : %s%n", receiverId, in);
  }
  
}
