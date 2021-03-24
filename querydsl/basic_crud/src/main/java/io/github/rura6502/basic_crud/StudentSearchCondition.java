package io.github.rura6502.basic_crud;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * StudentSearchCondition
 */
@Data
@AllArgsConstructor
public class StudentSearchCondition {

  private String studentName;

  private String className;

  private Integer ageMin;

  private Integer ageMax;

  
}