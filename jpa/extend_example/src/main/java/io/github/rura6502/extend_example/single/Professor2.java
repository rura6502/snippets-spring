package io.github.rura6502.extend_example.single;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Professor1
 */
@Entity
@Data
@NoArgsConstructor
// 상위 클래스의 dtype에 들어갈 값을 지정한다.
@DiscriminatorValue("professor")
public class Professor2 extends Member2 {

  private String profRoomId;

  
}