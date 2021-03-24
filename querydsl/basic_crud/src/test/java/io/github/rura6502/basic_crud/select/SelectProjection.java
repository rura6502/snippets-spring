package io.github.rura6502.basic_crud.select;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import io.github.rura6502.basic_crud.Clazz;
import io.github.rura6502.basic_crud.QStudent;
import io.github.rura6502.basic_crud.QStudentDtoWithAnnotation;
import io.github.rura6502.basic_crud.Student;
import io.github.rura6502.basic_crud.StudentDTO;
import io.github.rura6502.basic_crud.StudentDtoWithAnnotation;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Transactional
public class SelectProjection {
  
  
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

    em.flush();
    em.clear();

    factory = new JPAQueryFactory(em);
    qStudent = QStudent.student;
  }

  @Test
  void test_projection_basic() {
    List<String> result
      = factory
          .select(qStudent.name)
          .from(qStudent)
          .fetch();
    assertThat(result).containsExactly("Woo", "Kim", "Lee", "Hong", "Um");
  }

  @Test
  void test_projection_tuple() {
    List<Tuple> result
      = factory
          .select(
            qStudent.name
            , qStudent.age)
          .from(qStudent)
          .fetch();
    
    for (Tuple tuple: result) {
      System.out.printf("%s, %s%n", tuple.get(qStudent.name)
                                                      , tuple.get(qStudent.age));
    }
    assertTrue(true);
  }


  @Test
  void test_projection_DTO() {

    List<StudentDTO> result
      = factory
          .select(

          // using setter(need setter)
            Projections.bean(StudentDTO.class, qStudent.name, qStudent.age))

            // using field
            // Projections.fields(StudentDTO.class, qStudent.name, qStudent.age))

            // using constructor, 타입을 기준으로 입력됨
            // Projections.constructor(StudentDTO.class, qStudent.name, qStudent.age))

            // DTO의 필드 이름이 다를 경우 as 를 사용해서 매칭시킬 수 있음
            // 파라미터의 순서, 타입을 사용해서 매칭시키나 런타임 오류 가능성이 있어 @QueryProjection 사용을 추천
            // Projections.constructor(StudentDTO.class, qStudent.name.as("studentName"), qStudent.age))
          .from(qStudent)
          .fetch();
    
    for (StudentDTO dto: result) {
      System.out.printf("%s, %d%n", dto.getName()
                                                      , dto.getAge());
    }
    assertTrue(true);
  }

  @Test
  void test_projection_DTO_with_sub_query() {

    QStudent qStudentSub = new QStudent("studentSub");

    List<StudentDTO> result
      = factory
          .select(
            Projections.bean(
              StudentDTO.class
              , qStudent.name
              , qStudent.age
              , ExpressionUtils.as(
                JPAExpressions
                  .select(qStudentSub.age.max())
                  .from(qStudentSub), "max_age")))
          .from(qStudent)
          .fetch();
    
    for (StudentDTO dto: result) {
      System.out.printf("%s, %d%n", dto.getName()
                                                      , dto.getAge());
    }
    assertTrue(true);
  }

  @Test
  void test_projection_DTO_with_QueryProjection_annotation() {

    QStudent qStudentSub = new QStudent("studentSub");

    List<StudentDtoWithAnnotation> result
      = factory
          .select(
            new QStudentDtoWithAnnotation(qStudent.name, qStudent.age)
          )
          .from(qStudent)
          .fetch();
    
    for (StudentDtoWithAnnotation dto: result) {
      System.out.printf("%s, %d%n", dto.getName()
                                                      , dto.getAge());
    }
    assertTrue(true);
  }
}


