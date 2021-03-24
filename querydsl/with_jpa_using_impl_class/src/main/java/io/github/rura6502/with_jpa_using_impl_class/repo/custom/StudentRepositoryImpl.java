package io.github.rura6502.with_jpa_using_impl_class.repo.custom;

import java.util.List;

import javax.persistence.EntityManager;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import io.github.rura6502.with_jpa_using_impl_class.QStudent;
import io.github.rura6502.with_jpa_using_impl_class.Student;

public class StudentRepositoryImpl implements StudentRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  private final QStudent qStudent;

  public StudentRepositoryImpl(EntityManager entityManager) {
    this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    this.qStudent = QStudent.student;
  }

  public List<Student> search(String name, int ageMin, int ageMax) {
    return jpaQueryFactory.selectFrom(qStudent)
        .where(qStudent.name.eq(name).and(qStudent.age.gt(ageMin)).and(qStudent.age.lt(ageMax))).fetch();
  }

  @Override
  public Page<Student> searchPageSimple(String name, int ageMin, int ageMax, Pageable pageable) {
    
    QueryResults<Student> results = 
      jpaQueryFactory.selectFrom(qStudent)
      .where(
        getStudentSearchConditionBuilder(name, ageMin, ageMax))
      .orderBy(qStudent.age.desc())
      .offset(pageable.getOffset())
      .limit(pageable.getPageSize())
      .fetchResults();
      ;

    List<Student> students = results.getResults();
    long total = results.getTotal();

    return new PageImpl<>(students, pageable, total);
  }

  @Override
  public Page<Student> searchPageComplex(String name, int ageMin, int ageMax, Pageable pageable) {
    
    List<Student> results = 
      jpaQueryFactory.selectFrom(qStudent)
      .where(
        getStudentSearchConditionBuilder(name, ageMin, ageMax))
      .offset(pageable.getOffset())
      .limit(pageable.getPageSize())
      .fetch();
      ;

    JPAQuery<Student> countQuery = 
      jpaQueryFactory.selectFrom(qStudent)
      .where(
        getStudentSearchConditionBuilder(name, ageMin, ageMax))
      ;

    return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchCount);
  }


  private BooleanBuilder getStudentSearchConditionBuilder(String name, Integer ageMin, Integer ageMax) {

    BooleanBuilder builder = new BooleanBuilder();

    if (StringUtils.hasText(name)) {
      builder.and(qStudent.name.eq(name));
    }
    if (ageMin != null) {
      builder.and(qStudent.age.goe(ageMin));
    }
    if (ageMax != null) {
      builder.and(qStudent.age.loe(ageMax));
    }

    return builder;



  }
}
