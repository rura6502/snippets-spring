package io.github.rura6502.basic_crud.update;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import com.querydsl.jpa.impl.JPAQueryFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import io.github.rura6502.basic_crud.Clazz;
import io.github.rura6502.basic_crud.QStudent;
import io.github.rura6502.basic_crud.Student;

@SpringBootTest
@Transactional
public class UpdateTest {
  
  
  @Autowired
  private EntityManager em;


  JPAQueryFactory factory = null;
  QStudent qStudent = null;

  @BeforeEach
  public void initData() {
    Clazz classA = new Clazz("classA");
    Clazz classB = new Clazz("classB");
    em.persist(classA);
    em.persist(classB);

    em.persist(new Student("Woo", 5, classA)); 
    em.persist(new Student("Kim", 30, classA));
    em.persist(new Student("Lee", 25, classA));
    em.persist(new Student("Hong", 70, classB));
    em.persist(new Student("Um", 54));

    factory = new JPAQueryFactory(em);
    qStudent = QStudent.student;
  }

  @Test
  void update_bulk() {
    long count
      = factory
          .update(qStudent)
          .set(qStudent.name, "탈퇴")
          .where(qStudent.age.between(40, 100))
          .execute();

    em.flush();
    em.clear();
    /**
     * 위 update 쿼리는 Persistence Context와 상관 없이 바로 실행되어
     * Persistence Context와 데이터가 안맞는 경우가 발생함.
     * 다시 조회하더라도 영속성 컨텍스트의 내용이 우선됨(repeatable read)
     * flush, clear 작업이 필요. 아래 예제 참조
     */
    

    assertThat(count).isEqualTo(2);
  }

  @Test
  void update_bulk_add_age_one_and_test_flush_clear() {

    long count
      = factory
          .update(qStudent)
          .set(qStudent.age, qStudent.age.add(1))
          .execute();

    List<Student> allStudentsInPersistenceContext
      = factory.selectFrom(qStudent).fetch();

    assertThat(allStudentsInPersistenceContext)
      .extracting("age", Integer.class)
      .containsExactly(new Integer[]{5, 30, 25, 70, 54});

    em.flush();
    em.clear();

    List<Student> allStudentsInClearedPersistenceContext
      = factory.selectFrom(qStudent).fetch();

    assertThat(allStudentsInClearedPersistenceContext)
      .extracting("age", Integer.class)
      .containsExactly(new Integer[]{6, 31, 26, 71, 55});
  }

  @Test
  void test_delete() {
    factory.delete(qStudent).execute();
    assertThat(factory.selectFrom(qStudent).fetch()).isEmpty();
  }
}


