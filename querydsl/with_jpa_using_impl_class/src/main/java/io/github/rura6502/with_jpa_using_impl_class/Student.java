package io.github.rura6502.with_jpa_using_impl_class;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  @Id
  private Long id;

  private String name;

  private Integer age;
  
}
