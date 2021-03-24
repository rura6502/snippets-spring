package io.github.rura6502.with_jpa_using_impl_class.repo;

import com.querydsl.core.BooleanBuilder;

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentSearchCondition {

  private String name;

  private Integer ageMin;

  private Integer ageMax; 
}
