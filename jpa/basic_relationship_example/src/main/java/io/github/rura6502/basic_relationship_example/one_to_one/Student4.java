package io.github.rura6502.basic_relationship_example.one_to_one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student4 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "student4_id")
  private Long student4Id = null;

  private String name;

  // mappedBy = 대상 엔티티 클래스에서 본 클래스를 참조하는 멤버명
  @OneToOne(mappedBy = "student4")
  private Computer computer;


  public Student4(String name) {
    this.student4Id = null;
    this.name = name;
  }
  
  
}
