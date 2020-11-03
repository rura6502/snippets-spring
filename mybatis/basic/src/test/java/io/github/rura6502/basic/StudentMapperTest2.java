package io.github.rura6502.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentMapperTest2 {

  @Autowired
  private StudentMapper studentMapper;

	@Test
	void test_save_one() {

    Student student = new Student(999l, "Ki", Gender.MALE, 20, null);

    int updated = studentMapper.save(student);

    assertEquals(1, updated);

    List<Student> students = studentMapper.findAllByName(List.of("Ki"));

    assertEquals(1, students.size());
    assertEquals(student, students.get(0));

    
  }
  
}
