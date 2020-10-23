package io.github.rura6502.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Clazz {

  private long id;

  private String name;

  private Type type;
  
}
