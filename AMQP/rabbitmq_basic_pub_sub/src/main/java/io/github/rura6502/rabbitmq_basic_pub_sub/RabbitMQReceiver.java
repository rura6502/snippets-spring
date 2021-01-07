package io.github.rura6502.rabbitmq_basic_pub_sub;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RabbitMQReceiver {

  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  public void receiver1(String message) throws InterruptedException {
    this.receive("receiver1", message);
  }

  @RabbitListener(queues = "#{autoDeleteQueue2.name}")
  public void receiver2(String message) throws InterruptedException {
    this.receive("receiver2", message);
  }

  public void receive(String receiverId, String message) throws InterruptedException {
    
    System.out.printf("%s received : %s%n", receiverId, message);
    Thread.sleep(5000);
    System.out.printf("%s process done : %s%n", receiverId, message);
  }
  
}
