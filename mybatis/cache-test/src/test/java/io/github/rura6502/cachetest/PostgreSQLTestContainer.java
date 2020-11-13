package io.github.rura6502.cachetest;
import java.io.IOException;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.Container.ExecResult;
import org.testcontainers.utility.MountableFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostgreSQLTestContainer {

  private static PostgreSQLContainer container;

  private static final String INIT_SCRIPT_PATH = "insert.sql";

  public static PostgreSQLContainer<?> container() {
    if (container != null) {
      return container;
    }

    container= new PostgreSQLContainer<>("postgres:12.4-alpine")
                                  .withReuse(false);
    container.start();
    MountableFile file = MountableFile.forClasspathResource(INIT_SCRIPT_PATH);
    container.copyFileToContainer(file, "/" + INIT_SCRIPT_PATH);
    ExecResult result = null;
    try {
      result = container
              .execInContainer(
                "/bin/bash"
                , "-c"
                , "psql -U test -d test -f /" + INIT_SCRIPT_PATH);
    } catch (UnsupportedOperationException | IOException | InterruptedException e) {
      e.printStackTrace();
    }
    log.error("init JobHistory query result = {}", result.getExitCode());
    log.error("std out = {}", result.getStdout());
    log.error("std err = {}", result.getStderr());
      

    return container;
  }
  
}
