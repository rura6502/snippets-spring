package io.github.rura6502.postgres_data_type_test.test_class;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  int id;
  String name;
  Clazz clazz;


  
}
