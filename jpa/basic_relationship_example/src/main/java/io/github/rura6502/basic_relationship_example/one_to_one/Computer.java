package io.github.rura6502.basic_relationship_example.one_to_one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Computer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "computer_id")
  private Long computerId = null;

  private String alias;

  @OneToOne
  @JoinColumn(name = "student4_id")
  private Student4 student4;


  public Computer(String alias) {
    this.alias = alias;
  }
}
