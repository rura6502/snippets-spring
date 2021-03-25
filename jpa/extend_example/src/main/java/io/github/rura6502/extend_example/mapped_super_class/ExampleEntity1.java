package io.github.rura6502.extend_example.mapped_super_class;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class ExampleEntity1 extends MotherEntity {

  private String name;

  private int attr1;
  
}
