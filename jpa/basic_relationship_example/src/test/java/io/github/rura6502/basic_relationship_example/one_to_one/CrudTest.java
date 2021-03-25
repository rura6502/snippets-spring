package io.github.rura6502.basic_relationship_example.one_to_one;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class CrudTest {

  @PersistenceContext
  private EntityManager em;

  @Test
  public void insert_test() {
    Student4 st = new Student4("Woo");
    Computer cp = new Computer("CP01");

    cp.setStudent4(st);

    em.persist(st);
    em.persist(cp);

    Student4 findSt1 = em.find(Student4.class, st.getStudent4Id());
    assertThat(findSt1.getComputer()).isNull();

    em.flush();
    em.clear();

    Student4 findSt2 = em.find(Student4.class, st.getStudent4Id());
    assertThat(findSt2.getComputer()).isNotNull();

    

    // cp.setStudent4(st);
    // em.persist(st);

    // Student4 findSt2 = em.find(Student4.class, st.getStudent4Id());
    // assertThat(findSt2.getComputer().getComputerId()).isEqualTo(cp.getComputerId());

  }

}
