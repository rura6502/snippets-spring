package io.github.rura6502.basic_relationship_example.many_to_one_with_two_way;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Professor2 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "professor2_id")
  private Long professor2Id = null;

  private String name;

  /**
   * 
   * 연관관계의 주인은 아니지만 참조하고 싶을 때 사용.
   * 테이블은 fk - pk 관계로 양방향을 표현할 수 있지만 객체에서는
   * 양쪽에 각 객체의 참조를 둬서 양방향을 표현할 수 있음.
   * 
   * 객체로 양방향을 구현할 경우는 엄밀히 말하면 단방향 2개를
   * 만드는 것. 하지만 양방향에 대한 주인(업데이트를 반영할)은
   * 정해야 함. 주인이 아닌 쪽은 해당 키를 사용해 값을 변화(I, U)시킬 수 없음
   * 주인 아닌 쪽임을 선언하는 것이 `mappedBy` 속성
   * 
   * 테이블상의 외래키가 있는 곳을 주인(보통은 N:1에서 N쪽)으로 하는 것이 좋음
   * 
   */
  @OneToMany(mappedBy = "professor2")
  private List<Student2> student2s = new ArrayList<>();



  public Professor2(String name) {
    this.professor2Id = null;
    this.name = name;
    this.student2s = new ArrayList<>();
  }

  public void addAndSetStudent(Student2 student) {
    student.setProfessor2(this);
    this.student2s.add(student);
  }

}
