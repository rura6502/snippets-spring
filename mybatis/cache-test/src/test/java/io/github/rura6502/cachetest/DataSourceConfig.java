package io.github.rura6502.cachetest;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class DataSourceConfig
  implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {
    
    PostgreSQLTestContainer.container().start();

    final String jdbcUrl
      = PostgreSQLTestContainer
          .container().getJdbcUrl()
          .replaceAll("jdbc", "jdbc:log4jdbc")
          ;
    final String username
      = PostgreSQLTestContainer.container().getUsername();
    final String password
      = PostgreSQLTestContainer.container().getPassword();

    TestPropertyValues.of(
      "spring.datasource.driverClassName=" + "net.sf.log4jdbc.sql.jdbcapi.DriverSpy"
      , "spring.datasource.url=" + jdbcUrl
      , "spring.datasource.username=" + username
      , "spring.datasource.password=" + password
    ).applyTo(applicationContext.getEnvironment());

  }
  
}
