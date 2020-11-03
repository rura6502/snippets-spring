package io.github.rura6502.basic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = "io.github.rura6502.basic")
public class BasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
  }


  @Autowired
  private StudentMapper studentMapper;
  
  @Bean
  ApplicationRunner runner() {
    return args -> {
      System.out.println(studentMapper.findAll().size());
    };
  }

}
