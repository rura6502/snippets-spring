package io.github.rura6502.extend_example.joined;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CrudTest {

  @PersistenceContext
  private EntityManager em;

  @Test
  public void test_insert_basic() {

    Student1 st1 = new Student1("student1_1", "classA");
    em.persist(st1);

    Member1 findMem = em.find(Member1.class, st1.getMemberId());

    assertThat(findMem).isNotNull();
  }
  
}
