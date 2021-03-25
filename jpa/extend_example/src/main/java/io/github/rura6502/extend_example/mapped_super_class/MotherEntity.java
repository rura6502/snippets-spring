package io.github.rura6502.extend_example.mapped_super_class;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.NoArgsConstructor;


/**
 * 부모 클래스는 매핑정보만 자식에게 전달. 쿼리의 대상이 아님.
 */
@NoArgsConstructor
@MappedSuperclass
public abstract class MotherEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedDate
  private LocalDateTime created;

  @LastModifiedDate
  private LocalDateTime updated;
  
}
