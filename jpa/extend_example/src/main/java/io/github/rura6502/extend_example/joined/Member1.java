package io.github.rura6502.extend_example.joined;

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
@Inheritance(strategy = InheritanceType.JOINED)
/**
 * 부모, 자식 엔티티 클래스마다 별개의 테이블을 생성하고 각 엔티티 객체가 가진 값을 해당하는 엔티티 테이블과
 * 부모 엔티티 클래스 테이블에에 저장한다.
 *  자식 클래스의 아이디는 부모 엔티티 클래스의 테이블을 참조하기 위한 외래키가 된다.
 * 
 * @DiscriminatorColumn을 사용해서 DTYPE 칼럼을 추가할 수 있다. 이 칼럼은 부모 테이블의 값이
 * 어떤 자식에 의해서 생성된 값인지 구분해주는 구분자이다.
 * 
 * - 정규화 -> 저장공간 효율화
 * - 조인 연산으로 쿼리 복잡, 성능 저하
 * - insert 쿼리 두번 사용(부모, 자식)
 */
@DiscriminatorColumn(name = "dist_type")
public class Member1 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;

  private String memberName;

  public Member1(String memberName) {
    this.memberName = memberName;
  }


}
 