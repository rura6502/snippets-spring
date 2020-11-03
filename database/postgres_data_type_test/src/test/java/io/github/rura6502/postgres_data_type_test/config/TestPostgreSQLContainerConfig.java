package io.github.rura6502.postgres_data_type_test.config;

import java.io.IOException;

import org.testcontainers.containers.Container.ExecResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.MountableFile;

public class TestPostgreSQLContainerConfig {

  private static PostgreSQLContainer<?> container;

  public static PostgreSQLContainer<?> container() {
    if (container != null) {
      return container;
    }

    container= new PostgreSQLContainer<>().withReuse(false);
    container.start();
    MountableFile file
        = MountableFile.forClasspathResource("import.sql");
    container.copyFileToContainer(file, "/import.sql");
    ExecResult result = null;
    try {
      result = container
              .execInContainer(
                "/bin/bash"
                , "-c"
                , "psql -U test -d test -f /import.sql");
    } catch (UnsupportedOperationException | IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return container;
  }
  
}
