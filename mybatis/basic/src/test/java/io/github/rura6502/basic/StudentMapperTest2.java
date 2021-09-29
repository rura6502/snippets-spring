package io.github.rura6502.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentMapperTest2 {

  @Autowired
  private StudentMapper studentMapper;

	@Test
	void test_save_one_and_call_method() {

    Student student = new Student(null, "Ki", Gender.MALE, 20, null);

    long updated = studentMapper.save(student);

    assertEquals(1l, updated);

    List<Student> students
      = studentMapper.findAllByName(List.of(Student.addMr("Ki")));

    assertEquals(1, students.size());
    assertEquals(student.setName(
                            Student.addMr(student.getName()))
                          , students.get(0));

  }

  @Test
  void test_save_return_id() {

    Student studentAA = new Student(null, "aa", Gender.MALE, 20, null);
    Student studentBB = new Student(null, "bb", Gender.MALE, 20, null);
    Student studentCC = new Student(null, "acc", Gender.MALE, 20, null);

    studentMapper.save(studentAA);
    studentMapper.save(studentBB);
    studentMapper.save(studentCC);

    assertEquals(studentAA.getId() + 1, studentBB.getId());
    assertEquals(studentBB.getId() + 1, studentCC.getId());

  }

  @Test
  void test_save_as_map_return_id() {

    Map<String, Object> studentAA = new HashMap<>();
    // studentAA.put("id", null);
    studentAA.put("name", "aa");
    studentAA.put("gender", Gender.MALE);
    studentAA.put("age", "20");
    Map<String, Object> studentBB = new HashMap<>();
    // studentBB.put("id", null);
    studentBB.put("name", "bb");
    studentBB.put("gender", Gender.MALE);
    studentBB.put("age", "20");
    Map<String, Object> studentCC = new HashMap<>();
    // studentCC.put("id", null);
    studentCC.put("name", "cc");
    studentCC.put("gender", Gender.MALE);
    studentCC.put("age", "20");

    studentMapper.saveAsMap(studentAA);
    studentMapper.saveAsMap(studentBB);
    studentMapper.saveAsMap(studentCC);

    assertEquals(Integer.parseInt(studentAA.get("id") + "") + 1
                          , Integer.parseInt(studentBB.get("id") + ""));
    assertEquals(Integer.parseInt(studentBB.get("id") + "") + 1
                          , Integer.parseInt(studentCC.get("id") + ""));
  }
}
