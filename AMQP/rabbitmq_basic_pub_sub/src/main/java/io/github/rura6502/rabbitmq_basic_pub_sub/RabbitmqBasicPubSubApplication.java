package io.github.rura6502.rabbitmq_basic_pub_sub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitmqBasicPubSubApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqBasicPubSubApplication.class, args);
	}

}
