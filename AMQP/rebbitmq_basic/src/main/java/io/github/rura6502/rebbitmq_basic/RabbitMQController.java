package io.github.rura6502.rebbitmq_basic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RabbitMQController {

  private final RabbitTemplate rabbitTemplate;

  @GetMapping("/send/{message}")
  public void String(@PathVariable("message") String message) {
    rabbitTemplate.convertAndSend("hello", message);
    System.out.printf("send message : %s%n", message);
  }
  
}
