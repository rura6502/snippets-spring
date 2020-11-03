package io.github.rura6502.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.apache.ibatis.executor.SimpleExecutor;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentMapperTest1 {

  @Autowired
  private StudentMapper studentMapper;

	@Test
	void test_find_all() {
    assertEquals(6, studentMapper.findAll().size());
  }
  
  /**
   * resultMap을 활용
   * if를 활용하여 parameter가 null 또는 empty가 아닐 경우 foreach를 활용해서 IN 조건을 사용
   */
  @Test
  void test_find_all_with_condition_1() {
    List<String> names = List.of("김길동", "홍상후", "김은지");
    List<Gender> genders = List.of(Gender.MALE);
    
    assertEquals(
      2
      , studentMapper.findAllWithConditions(
        null, names, genders).size());
  }


  /**
   * 메소드 오버로딩이 되지 않음
   */
  @Test
  void find_all_with_condition_2() {
    List<String> names = List.of("김길동", "홍상후", "김은지");
    List<Gender> genders = List.of(Gender.MALE);

    assertThrows(
      MyBatisSystemException.class
      , () -> {
        studentMapper.findAllWithConditions(names, genders);
      });
  }

  /**
   * sql 태그를 사용
   */
  @Test
  void find_all_by_names() {
    assertEquals(
      3
      , studentMapper.findAllByName(List.of("김길동", "홍상후", "김은지")).size());
  }

  /**
   * sql 태그를 사용
   * sql 내부에 if문을 사용
   */
  @Test
  void find_all_by_names_is_null() {
    // String names
    //   = List.of("김길동", "홍상후", "김은지")
    //       .parallelStream()
    //       .map(name -> String.format("'%s'", name))
    //       .collect(Collectors.joining(","));
    assertEquals(
      6
      , studentMapper.findAllByName(null).size());
  }
}
