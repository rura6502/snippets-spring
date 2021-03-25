package io.github.rura6502.extend_example.table_per_class;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Student3 extends Member3 {

  private String className;


  public Student3(String memberName, String className) {
    super(memberName);
    this.className = className;
  }


}
