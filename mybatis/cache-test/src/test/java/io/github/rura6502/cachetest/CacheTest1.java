package io.github.rura6502.cachetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(initializers = DataSourceConfig.class)
public class CacheTest1 {

  @Autowired
  private CacheTestMapper mapper;


  @Test
  public void test_work() {
    assertNotNull(mapper.selectAll());
  }

  @Test
  public void test_select_not_caching() {
    assertEquals(6, mapper.selectWithoutCache(2450).size());
    assertEquals(6, mapper.selectWithoutCache(2450).size());
    assertEquals(6, mapper.selectWithoutCache(2450).size());
    assertEquals(6, mapper.selectWithoutCache(2450).size());
    assertEquals(6, mapper.selectWithoutCache(2450).size());
  }

  @Test
  public void test_select_caching() {
    assertEquals(6, mapper.selectWithCache(2450).size());
    assertEquals(6, mapper.selectWithCache(2450).size());
    assertEquals(6, mapper.selectWithCache(2450).size());
    assertEquals(6, mapper.selectWithCache(2450).size());
    assertEquals(6, mapper.selectWithCache(2450).size());
  }

  

}
