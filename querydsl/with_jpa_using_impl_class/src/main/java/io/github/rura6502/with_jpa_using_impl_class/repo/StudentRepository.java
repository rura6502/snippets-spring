package io.github.rura6502.with_jpa_using_impl_class.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rura6502.with_jpa_using_impl_class.Student;
import io.github.rura6502.with_jpa_using_impl_class.repo.custom.StudentRepositoryCustom;

public interface StudentRepository
  extends JpaRepository<Student, Long>
                , StudentRepositoryCustom {
  
}
