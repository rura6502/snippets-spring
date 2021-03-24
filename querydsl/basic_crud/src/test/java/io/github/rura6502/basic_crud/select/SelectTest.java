package io.github.rura6502.basic_crud.select;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import io.github.rura6502.basic_crud.Clazz;
import io.github.rura6502.basic_crud.QStudent;
import io.github.rura6502.basic_crud.Student;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Transactional
public class SelectTest {

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
    // q = new QStudent("q");      // 같은 테이블을 조인할 때 중복 방지로 alias를 사용
  }

  @Test
  void test_get_students_using_fetch() {
    List<Student> students
      = factory.select(qStudent).from(qStudent).fetch();

    assertEquals(5, students.size());
  }

  /**
   * fetchResults()
   * fetchCount()
   */
  @Test
  void test_get_students_using_fetctResult() {
    JPAQuery<Student> query
      = factory
          .select(qStudent)
          .from(qStudent);
    
    QueryResults<Student> queryResult = query.fetchResults();
    Long count = query.fetchCount();

    assertAll(
      () -> assertEquals(5, queryResult.getTotal())
      , () -> assertEquals(5, count)
    );
    
  }

  @Test
  void test_find_students_by_name() {
    List<Student> students
      = factory.selectFrom(qStudent)
            .where(
              qStudent.name.eq("Kim")
              .or(qStudent.name.eq("Woo"))

              // same
              // q.name.eq("Kim")
              // , q.name.eq("Woo")
            ).fetch();
    
    assertEquals(2, students.size());
  }

  /**
   * sort
   */
  @Test
  void test_find_students_desc_age() {
    List<Student> students
      = factory
          .selectFrom(qStudent)
          .orderBy(
            qStudent.age.desc()
            , qStudent.name.asc())
          .fetch();

    assertEquals(70, students.get(0).getAge());
  }

  /**
   * paging
   */
  @Test
  void test_pageing_students() {
    List<Student> students
      = factory
          .selectFrom(qStudent)
          .offset(2)
          .limit(2)
          .fetch();

    assertEquals("Lee", students.get(0).getName());
    assertEquals("Hong", students.get(1).getName());
  }

  @Test
  void test_case() {

    List<Tuple> result
      =  factory
            .select(
              qStudent
              , qStudent.age
                .when(20).then("20대 시작")
                .when(30).then("30대 시작")
                .otherwise("대상아님").as("대상여부")
              , new CaseBuilder()
                  .when(qStudent.age.between(0, 30)).then("young")
                  .otherwise("old").as("구분"))
            .from(qStudent)
            .fetch();

    assertThat(result).filteredOn(t -> t.get(1, String.class), "30대 시작").hasSize(1);
    assertThat(result).filteredOn(t -> t.get(2, String.class), "old").hasSize(2);
  }

  @Test
  void concat() {
    Tuple result
      = factory
          .select(
            qStudent.name
            , qStudent.age.stringValue().concat("살").as("나이"))
          .from(qStudent)
          .where(qStudent.name.eq("Woo"))
          .fetchOne();

          assertThat(result)
            .extracting(t -> t.get(1, String.class))
            .isEqualTo("5살");
    
  }

  /**
   * aggregation
   */
  @Test
  public void test_aggregation() {
    List<Tuple> result
      = factory
          .select(
            qStudent.count()
            , qStudent.age.min()
            , qStudent.age.max()
          )
          .from(qStudent).fetch();

    assertAll(
      () -> assertEquals(5, result.get(0).get(qStudent.count()))
      , () -> assertEquals(5, result.get(0).get(qStudent.age.min()))
      , () -> assertEquals(70, result.get(0).get(qStudent.age.max()))
    );
  }
}
