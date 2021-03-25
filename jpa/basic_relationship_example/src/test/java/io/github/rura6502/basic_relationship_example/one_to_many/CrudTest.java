package io.github.rura6502.basic_relationship_example.one_to_many;

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

  /**
   * 로그를 확인하면 N:1관계와는 다르게 update 쿼리가 실행 됨.
   * 테이블상의 외래키는 N에 있으나 jpa entity에서 주인을 1쪽에 두었으므로
   * 1에 해당하는 엔티티를 삽입하고 추가로 n의 컬럼을 업데이트 하는 것
   */
  @Test
  public void test_insert() {
    Student3 st3 = new Student3("woo");
    Professor3 prof3 = new Professor3("prof.Kim");

    prof3.getStudent3s().add(st3);

    em.persist(st3);
    em.persist(prof3);

    em.flush();
    em.clear();

    Student3 findSt = em.find(Student3.class, st3.getStudentId());
    
    assertThat(findSt.getProfessor3().getProfessor3Id()).isEqualTo(prof3.getProfessor3Id());

  }

}
