package io.github.rura6502.basic_relationship_example.many_to_many;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student5 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "student5_id")
  private Long student5Id = null;

  private String name;

  @ManyToMany
  @JoinTable(
    name = "student5_professor5"
  )
  private List<Professor5> professor5;

  public Student5(String name) {
    this.student5Id = null;
    this.name = name;
  }
  
  
}
