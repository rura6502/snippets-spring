package io.github.rura6502.spring_integration_getting_started;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.config.EnableIntegration;

import java.util.Scanner;

@SpringBootApplication
@EnableIntegration
public class SpringIntegrationGettingStartedApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringIntegrationGettingStartedApplication.class, args);
  }

  @Bean
  ApplicationRunner runner() {
    return args -> {
      Scanner scanner = new Scanner(System.in);
      System.out.print("Please enter q and press <enter> to exit the program: ");

      while (true) {
        String input = scanner.nextLine();
        if ("q".equals(input.trim())) {
          break;
        }
      }
      System.exit(0);
    };
  }
}
