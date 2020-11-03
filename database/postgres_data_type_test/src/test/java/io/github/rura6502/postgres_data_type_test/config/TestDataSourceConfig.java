package io.github.rura6502.postgres_data_type_test.config;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class TestDataSourceConfig
  implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(ConfigurableApplicationContext ctx) {

    TestPostgreSQLContainerConfig
      .container().start();

    final String jdbcUrl
      = TestPostgreSQLContainerConfig
          .container().getJdbcUrl().replaceAll("jdbc", "jdbc:log4jdbc");
    final String username
      = TestPostgreSQLContainerConfig
          .container().getUsername();
    final String password
      = TestPostgreSQLContainerConfig
          .container().getPassword();

    TestPropertyValues.of(
      "spring.datasource.driverClassName=" + "net.sf.log4jdbc.sql.jdbcapi.DriverSpy"
      , "spring.datasource.url=" + jdbcUrl
      , "spring.datasource.username=" + username
      , "spring.datasource.password=" + password
    ).applyTo(ctx.getEnvironment());
  }
} 


