package io.github.rura6502.basic_crud.select;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import io.github.rura6502.basic_crud.Clazz;
import io.github.rura6502.basic_crud.QClazz;
import io.github.rura6502.basic_crud.QStudent;
import io.github.rura6502.basic_crud.QStudentTeamDto;
import io.github.rura6502.basic_crud.Student;
import io.github.rura6502.basic_crud.StudentSearchCondition;
import io.github.rura6502.basic_crud.StudentTeamDto;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Transactional
public class SelectDynamicQuery {
  
  
  @Autowired
  private EntityManager em;


  JPAQueryFactory factory = null;
  QStudent qStudent = null;
  QClazz qClass = null;

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
    qClass = QClazz.clazz;
  }

  @Test
  void test_dynamic_query_with_BooleanBuilder1() {

    String nameParam = "Woo";
    Integer ageParam = 30;

    BooleanBuilder builder = new BooleanBuilder();
    if (nameParam != null) {
      builder.and(qStudent.name.eq(nameParam));
    }
    if (ageParam != null) {
      builder.and(qStudent.age.eq(ageParam));
    }

    List<Student> result
      = factory
          .selectFrom(qStudent)
          .where(builder)
          .fetch();

    assertThat(result).isEmpty();
  }

  
  @Test
  void test_dynamic_query_with_BooleanBuilder2() {

    StudentSearchCondition condition
      =  new StudentSearchCondition("Woo", "classA", 0, 10);

    BooleanBuilder builder = new BooleanBuilder();

    if (StringUtils.hasText(condition.getStudentName())) {
      builder.and(qStudent.name.eq(condition.getStudentName()));
    }
    if (StringUtils.hasText(condition.getClassName())) {
      builder.and(qClass.name.eq(condition.getClassName()));
    }
    if (condition.getAgeMin() != null) {
      builder.and(qStudent.age.goe(condition.getAgeMin()));
    }
    if (condition.getAgeMax() != null) {
      builder.and(qStudent.age.loe(condition.getAgeMax()));
    }

    List<StudentTeamDto> result
      =  factory
                  .select(new QStudentTeamDto(
                    qStudent.name.as("studentName")
                    , qStudent.age.as("studentAge")
                    , qClass.name.as("className")))
                  .from(qStudent)
                  .leftJoin(qStudent.clazz, qClass)
                  .where(builder)
                  .fetch()
                  ;
  
    assertThat(result).hasSize(1);
  }

  @Test
  void test_dynamic_query_with_Predicate() {
    List<Student> result
      = factory
          .selectFrom(qStudent)
          .where(nameEq("Woo"), ageEq(30))
          .fetch();

    assertThat(result).isEmpty();
  }

  private Predicate nameEq(String nameParam) {
    return nameParam != null ? qStudent.name.eq(nameParam) : null;
  }

  private Predicate ageEq(Integer ageParam) {
    return ageParam != null ? qStudent.age.eq(ageParam) : null;
  }
}