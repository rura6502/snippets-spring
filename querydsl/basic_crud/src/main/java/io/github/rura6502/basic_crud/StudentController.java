// package io.github.rura6502.basic_crud;

// import javax.annotation.PostConstruct;
// import javax.persistence.EntityManager;

// import com.querydsl.jpa.impl.JPAQuery;
// import com.querydsl.jpa.impl.JPAQueryFactory;

// import org.springframework.stereotype.Repository;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RestController;

// import lombok.RequiredArgsConstructor;

// @Repository
// @RequiredArgsConstructor
// public class StudentController {

//   private final EntityManager em;

//   @PostConstruct
//   public void init() {
//     Clazz classA = new Clazz("classA");
//     Clazz classB = new Clazz("classB");

//     em.persist(new Student("Woo", classA));
//     em.persist(new Student("Kim", classA));
//     em.persist(new Student("Lee", classA));
//     em.persist(new Student("Hong", classB));
//     em.persist(new Student("Um"));

//   }

//   @GetMapping
//   public Student findStudentById(
//     @PathVariable("studentId") long studentId) {
//     JPAQueryFactory q = new JPAQueryFactory(em);
//     QStudent qStudent = QStudent.student;

//     return q.selectFrom(qStudent).;
//   }

//   @PostMapping("/students")
//   public Student saveStudent(Student student) {
//     em.persist(student);
//     return student;
//   }

  

// }