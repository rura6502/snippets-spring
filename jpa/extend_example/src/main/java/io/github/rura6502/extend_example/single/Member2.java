package io.github.rura6502.extend_example.single;

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
 * 하나의 테이블로 처리한다.
 * 자식 엔티티 클래스에서 확장한 값들은 칼럼이 추가되며, 값 추가 시 해당하지 않는 컬럼은 null로 들어간다.
 * @DiscriminatorColumn 가 자동으로 들어간다.
 * 
 * @DiscriminatorColumn을 사용해서 DTYPE 칼럼을 추가할 수 있다. 이 칼럼은 부모 테이블의 값이
 * 어떤 자식에 의해서 생성된 값인지 구분해주는 구분자이다.
 * 
 * - 조인 필요 없음, 쿼리 단순
 * - 자식 엔티티가 매핑한 컬럼은 모두 null 허용
 * - 테이블이 커질 수 있고 그럴 경우 성능 저하
 */
@DiscriminatorColumn(name = "dist_type")
public class Member2 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;

  private String memberName;

  public Member2(String memberName) {
    this.memberName = memberName;
  }


}
 