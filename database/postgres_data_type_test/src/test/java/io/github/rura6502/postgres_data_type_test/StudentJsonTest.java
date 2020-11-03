package io.github.rura6502.postgres_data_type_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.rura6502.postgres_data_type_test.repository.StudentJsonTestRepository;
import io.github.rura6502.postgres_data_type_test.test_class.Clazz;
import io.github.rura6502.postgres_data_type_test.test_class.Student;

@SpringBootTest
public class StudentJsonTest {


  static class TestData {
    static Clazz clazzA = new Clazz(0, "classA");
    static Student student = new Student(0, "Kim", clazzA);
  }

  @Autowired
  private StudentJsonTestRepository repo;

  @Autowired
  private ObjectMapper objMapper;

  @Test
  @Order(0)
  public void save_test() {

    int update = repo.save(TestData.student);

    assertEquals(1, update);
  }


  @Test
  @Order(1)
  public void get_test() {
    
    Student _student = repo.findByName("Kim");
    
    assertEquals(TestData.student, _student);

  }
  
}
