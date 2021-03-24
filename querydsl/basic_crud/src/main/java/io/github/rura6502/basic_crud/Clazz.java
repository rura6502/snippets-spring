package io.github.rura6502.basic_crud;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Clazz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "clazz_id")
  private Long id;

  private String name;

  @OneToMany(mappedBy = "clazz")
  private List<Student> students = new ArrayList<>();

  public Clazz(String name) {
    this.name = name;
  }

  
  
}
