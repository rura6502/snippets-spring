package io.github.rura6502.extend_example.joined;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
// 상위 클래스의 dtype에 들어갈 값을 지정한다.
@DiscriminatorValue("student")
public class Student1 extends Member1 {

  private String className;


  public Student1(String memberName, String className) {
    super(memberName);
    this.className = className;
  }


}
