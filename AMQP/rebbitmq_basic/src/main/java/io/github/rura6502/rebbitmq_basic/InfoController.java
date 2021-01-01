package io.github.rura6502.rebbitmq_basic;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testcontainers.containers.RabbitMQContainer;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoController {

  private final RabbitMQTestContainerConfig testContConfig;
  
  @GetMapping("/info")
  public Map<String, Object> getInfo() {
    RabbitMQContainer rmCont = testContConfig.getContainer();
    Map<String, Object> info = new HashMap<>();
    info.put("adminUsername", rmCont.getAdminUsername());
    info.put("adminPassword", rmCont.getAdminPassword());
    info.put("amqpUrl", rmCont.getAmqpUrl());
    info.put("amqpPort", rmCont.getAmqpPort());
    info.put("amqpsUrl", rmCont.getAmqpsUrl());
    info.put("amqpsPort", rmCont.getAmqpsPort());
    info.put("containerId", rmCont.getContainerId());
    info.put("networkMode", rmCont.getNetworkMode());
    return info;
  }
}