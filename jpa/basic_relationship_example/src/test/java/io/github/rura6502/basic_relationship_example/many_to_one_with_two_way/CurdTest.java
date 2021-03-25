package io.github.rura6502.basic_relationship_example.many_to_one_with_two_way;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.rura6502.basic_relationship_example.many_to_one_with_one_way.Professor1;
import io.github.rura6502.basic_relationship_example.many_to_one_with_one_way.Student1;

@SpringBootTest
@Transactional
public class CurdTest {

  @PersistenceContext
  private EntityManager em;

  @BeforeEach
  public void initData() {
    Professor1 prof1 = new Professor1(null, "prof.Woo");
    em.persist(prof1);

    Student1 st1 = new Student1(null, "Nam", prof1);
    em.persist(st1);

    em.flush();
    em.clear();
  }

  @Test
  public void get_prof_from_student_using_reference() {
    Student1 st = em.find(Student1.class, 1l);
    assertThat(st.getProfessor1()).isNotNull();
    assertThat(st.getProfessor1().getName()).isEqualTo("prof.Woo");
  }
  
}
