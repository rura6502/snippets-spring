package io.github.rura6502.basic_relationship_example.many_to_many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Professor5 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "professor5_id")
  private Long professor3Id = null;

  private String name;

  @ManyToMany(mappedBy = "professor5")
  
  private List<Student5> student5s = new ArrayList<>();

  public Professor5(String name) {
    this.name = name;
  }
}
