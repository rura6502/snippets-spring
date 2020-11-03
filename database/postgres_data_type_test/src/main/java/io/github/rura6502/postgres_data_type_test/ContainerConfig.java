package io.github.rura6502.postgres_data_type_test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.sql.DataSource;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.Container.ExecResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.MountableFile;

@Configuration
public class ContainerConfig {



  @Bean
  public PostgreSQLContainer container() {
    PostgreSQLContainer<?> container = new PostgreSQLContainer<>();
    container.start();
    return container;
  }

  @Bean
  public DataSource dataSource(PostgreSQLContainer container) {

    final String jdbcUrl = container
                                          .getJdbcUrl()
                                          .replace("jdbc", "jdbc:log4jdbc");
    final String username = container.getUsername();
    final String password = container.getPassword();
    
    DataSourceBuilder builder = DataSourceBuilder.create();
    builder.driverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
    builder.url(jdbcUrl).username(username).password(password);
    return builder.build();
  }

  @Bean
  public ApplicationRunner runner() {
    return args -> {
      
      MountableFile file
        = MountableFile.forClasspathResource("import.sql");
      // container().copyFileToContainer(file, "~/");
      container().copyFileToContainer(file, "/import.sql");
      ExecResult result
        = container()
            .execInContainer(
              "/bin/bash"
              , "-c"
              , "psql -U test -d test -f /import.sql");
    
    };
  }
}
