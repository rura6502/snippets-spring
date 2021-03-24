package io.github.rura6502.basic_crud;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @AllArgsConstructor
@NoArgsConstructor
public class StudentDtoWithAnnotation {
  private String name;
  private int age;

  @QueryProjection  // DTO에 대한 Q타입을 생성해줌
  public StudentDtoWithAnnotation(String name, int age) {
    this.name = name;
    this.age = age;
  }

  
}