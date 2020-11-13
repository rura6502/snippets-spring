package io.github.rura6502.cachetest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "io.github.rura6502.cachetest")
public class CacheTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(CacheTestApplication.class, args);
  }

}
