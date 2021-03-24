package io.github.rura6502.with_jpa_using_impl_class;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.rura6502.with_jpa_using_impl_class.repo.StudentRepository;

@SpringBootTest
class WithJpaUsingImplClassApplicationTests {

  @Autowired
  private StudentRepository repo;

  @BeforeEach
  public void init() {
    repo.save(new Student(1l, "a", 30));
    repo.save(new Student(2l, "b", 34));
    repo.save(new Student(3l, "c", 75));
    repo.save(new Student(4l, "d", 12));
    repo.save(new Student(5l, "e", 34));
    repo.save(new Student(2l, "f", 45));
    repo.save(new Student(3l, "g", 64));
    repo.save(new Student(4l, "h", 123));
  }


	@Test
	void test_student_repository_impl() {

    List<Student> students = repo.search("a", 10, 50);

    assertThat(students).hasSize(1);
	}

}
