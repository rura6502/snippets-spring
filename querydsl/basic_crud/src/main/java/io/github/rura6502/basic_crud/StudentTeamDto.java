package io.github.rura6502.basic_crud;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentTeamDto {


  private String studentName;
  private int age;

  private String className;

  @QueryProjection
  public StudentTeamDto(String studentName, int age, String className) {
    this.studentName = studentName;
    this.age = age;
    this.className = className;
  }

  
}