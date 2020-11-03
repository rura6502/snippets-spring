package io.github.rura6502.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  private Long id;

  private String name;

  private Gender gender;

  private Integer age;

  private Clazz clazz;
}
