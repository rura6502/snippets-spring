package io.github.rura6502.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Student {

  private Long id;

  private String name;

  private Gender gender;

  private Integer age;

  private Clazz clazz;


  public static String addMr(String name) {
    return "Mr." + name;
  }
}
