package io.github.rura6502.basic_relationship_example.one_to_many;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student3 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long studentId = null;

  private String name;

  // 1:N 에서 N쪽에 참조용 멤버를 넣는 방법
  @ManyToOne
  @JoinColumn(name = "professor3_id"
                        , insertable = false
                        , updatable = false)
  private Professor3 professor3;

  public Student3(String name) {
    this.studentId = null;
    this.name = name;
  }
  
  
}
