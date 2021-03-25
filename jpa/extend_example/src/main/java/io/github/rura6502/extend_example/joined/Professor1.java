package io.github.rura6502.extend_example.joined;

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
@AllArgsConstructor
// 상위 클래스의 dtype에 들어갈 값을 지정한다.
@DiscriminatorValue("professor")
public class Professor1 extends Member1 {

  private String profRoomId;

  
}