package io.github.rura6502.extend_example.table_per_class;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
/**
 * 자식 엔티티 클래스마다 테이블을 만든다. 부모는 만들지 않는다.
 * 
 * @DiscriminatorColumn을 사용해서 DTYPE 칼럼을 추가할 수 있다. 이 칼럼은 부모 테이블의 값이
 * 어떤 자식에 의해서 생성된 값인지 구분해주는 구분자이다.
 * 
 * 특정 자식 엔티티 클래스를 조회할 때 같은 부모를 가진
 * 모든 엔티티 클래스를 조회해서  union하여 찾는다. -> 성능 로스
 * 
 * 비추
 * - union 쿼리로 성능 느림
 * 
 * 
 */
@DiscriminatorColumn(name = "dist_type")
public class Member3 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;

  private String memberName;

  public Member3(String memberName) {
    this.memberName = memberName;
  }


}
 