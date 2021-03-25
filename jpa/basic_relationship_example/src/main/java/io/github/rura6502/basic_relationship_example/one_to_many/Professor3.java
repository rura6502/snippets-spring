package io.github.rura6502.basic_relationship_example.one_to_many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Professor3 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "professor3_id")
  private Long professor3Id = null;

  private String name;

  @OneToMany    //(mappedBy = "professor3")
  @JoinColumn(name = "professor3_id") //joincolum을 해주지 않으면 중간 테이블이 생성 됨.
  private List<Student3> student3s = new ArrayList<>();

  public Professor3(String name) {
    this.name = name;
  }
}
