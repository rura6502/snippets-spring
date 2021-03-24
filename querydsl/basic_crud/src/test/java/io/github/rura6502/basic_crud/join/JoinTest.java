package io.github.rura6502.basic_crud.join;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import com.querydsl.core.Tuple;
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
public class JoinTest {
  
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

  @Test
  void test_join() {
    List<Student> result
      = factory
          .selectFrom(qStudent)
          .join(qStudent.clazz, qClazz)
          //.innerJoin(qStudent.clazz, qClazz)  //same result
          .where(qClazz.name.eq("classA"))
          .fetch();
          
    assertThat(result)
      .extracting("name")
      .containsExactly("Woo", "Kim", "Lee");
  }

  @Test
  void test_left_join() {
    List<Tuple> result
      = factory
          .select(qStudent, qClazz)
          .from(qStudent)
          .leftJoin(qStudent.clazz, qClazz) // 학생 리스트가 모두 출력 됨
          // .join(qStudent.clazz, qClazz)        // class가 있는 학생들만 출력 됨
          .on(qClazz.name.eq("classA"))       // join() 사용 시 이 부분은 where와 결과가 같음
          //.where(qClazz.name.eq("classA"))  // where가 가독성이 더 좋을수 있음
          .fetch();

    assertThat(result.size()).isEqualTo(5);
  }


  @PersistenceUnit
  EntityManagerFactory emf;

  @Test
  void test_no_fetch_join() {

    em.flush();
    em.clear();

    Student student = factory.selectFrom(qStudent)
                                              .where(qStudent.name.eq("Woo"))
                                              .fetchOne();

    boolean isMember1Loaded = emf.getPersistenceUnitUtil().isLoaded(student.getClazz());

    assertThat(isMember1Loaded).isFalse();
  }

  @Test
  void test_fetch_join() {

    em.flush();
    em.clear();

    Student student = factory.selectFrom(qStudent)
                                              .join(qStudent.clazz, qClazz).fetchJoin()
                                              .where(qStudent.name.eq("Woo"))
                                              .fetchOne();

    boolean isMember2Loaded = emf.getPersistenceUnitUtil().isLoaded(student.getClazz());

    assertThat(isMember2Loaded).isTrue();
  }



  @Test
  void test_join_group_by() {
    
    List<Tuple> result
      = factory
          .select(
            qClazz.name, qStudent.age.sum())
          .from(qStudent)
          .join(qStudent.clazz, qClazz)
          .groupBy(qClazz.name)
          .fetch();
    Tuple classA = result.get(0);
    Tuple classB = result.get(1);

    assertEquals(60, classA.get(qStudent.age.sum()));
    assertEquals(70, classB.get(qStudent.age.sum()));
  }


}
