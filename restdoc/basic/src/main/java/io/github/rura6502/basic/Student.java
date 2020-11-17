package io.github.rura6502.basic;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

  private long id;

  private String name;

  private Type type;

  private LocalDate birthday;
  
}


