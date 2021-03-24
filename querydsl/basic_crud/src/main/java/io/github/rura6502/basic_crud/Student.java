package io.github.rura6502.basic_crud;

import javax.jdo.annotations.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "clazz")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "student_id")
  private Long id;

  private String name;

  private int age;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "clazz_id")
  private Clazz clazz;

  public Student(String name, int age) {
    this.name = name;
    this.age = age;
    this.leaveClazz();
  }

  public Student(String name, int age, Clazz clazz) {
    this.name = name;
    this.age = age;
    if (clazz == null) {
      this.leaveClazz();
    } else {
      this.changeClazz(clazz);
    }
    
    
  }

  public void changeClazz(Clazz clazz) {
    this.clazz = clazz;
    clazz.getStudents().add(this);
  }

  public void leaveClazz() {
    if (this.clazz != null) {
      this.clazz.getStudents().remove(this);
    }
    this.clazz = null;
  }


}
