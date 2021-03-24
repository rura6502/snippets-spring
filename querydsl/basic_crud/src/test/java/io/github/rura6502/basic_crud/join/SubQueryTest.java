package io.github.rura6502.basic_crud.join;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import io.github.rura6502.basic_crud.Clazz;
import io.github.rura6502.basic_crud.QClazz;
import io.github.rura6502.basic_crud.QStudent;
import io.github.rura6502.basic_crud.Student;

@SpringBootTest
@Transactional
public class SubQueryTest {
  
  @Autowired
  private EntityManager em;

  private JPAQueryFactory factory = null;
  private QStudent qStudent = null;
  private QClazz qClazz = null;

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

    em.flush();
    em.clear();

    factory = new JPAQueryFactory(em);
    qStudent = QStudent.student;
    qClazz = QClazz.clazz;
  }

  /**
   * select, where절의 서브쿼리 가능.
   * from절의 서브쿼리는 지원하지 않는다. 해결방안
   * - join으로 변경해서 사용(불가능할 수도 있음)
   * - 애플리케이션에서 분리해서 사용
   * - nativeSQL을 사용
   */
  @Test
  void test_sub_query() {
    QStudent qStudentSub = new QStudent("studentSub");
    List<Student> students
      = factory
          .selectFrom(qStudent)
          .where(qStudent.age.eq(
            JPAExpressions
              .select(qStudentSub.age.max())
              .from(qStudentSub)))
          .fetch();
    
    assertThat(students)
      .extracting("age")
      .containsExactly(70);
  }

  
}
