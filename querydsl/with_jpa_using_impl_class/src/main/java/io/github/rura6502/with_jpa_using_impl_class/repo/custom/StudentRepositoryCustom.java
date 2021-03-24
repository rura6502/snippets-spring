package io.github.rura6502.with_jpa_using_impl_class.repo.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.rura6502.with_jpa_using_impl_class.Student;

public interface StudentRepositoryCustom {

  List<Student> search(String name, int ageMin, int ageMax);

  Page<Student> searchPageSimple(String name, int ageMin, int ageMax, Pageable pageable);

  Page<Student> searchPageComplex(String name, int ageMin, int ageMax, Pageable pageable);
  
}
