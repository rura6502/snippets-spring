package io.github.rura6502.extend_example.single;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Student2 extends Member2 {

  private String className;


  public Student2(String memberName, String className) {
    super(memberName);
    this.className = className;
  }


}
