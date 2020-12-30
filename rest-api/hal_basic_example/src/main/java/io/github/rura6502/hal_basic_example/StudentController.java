package io.github.rura6502.hal_basic_example;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mediatype.Affordances;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  private Map<String, Student> database = new HashMap<>();

  @PostConstruct
  public void setData() {
    database.put("ST000", new Student("ST000", "Kim"));
    database.put("ST001", new Student("ST001", "Hong"));
    database.put("ST002", new Student("ST002", "Lee"));
    database.put("ST003", new Student("ST003", "Woo"));
    database.put("ST004", new Student("ST004", "Young"));
    database.put("ST005", new Student("ST005", "Nam"));
  }

  @GetMapping("/students")
  public List<Student> getStudents() {
    List<Student> students
      = database.entrySet()
                          .stream()
                          .map(entry -> entry.getValue())
                          .collect(Collectors.toList());

    return students;
  }

  // @GetMapping("/students/class/{studentId}")
  // public EntityModel<Student> getStudentByIdLinkByClass(@PathVariable("studentId") String studentId) {
  //   Student student = database.get(studentId);
  //   // EntityModel<Student> model = EntityModel.of(student);
  //   // model.add(linkTo(StudentController.class).slash(student.getId()).withSelfRel());
  //   return EntityModel.of(
  //     student
  //               , linkTo(methodOn(this.getClass()).getStudentByIdLinkByClass(studentId)).withSelfRel()
  //                   .andAffordance(afford(methodOn(this.getClass()).deleteStudent(studentId))));

  // }

  // @GetMapping("/students/method/{studentId}")
  // public EntityModel<Student> getStudentByIdLinkByMethod(@PathVariable("studentId") String studentId) {
  //   Student student = database.get(studentId);
  //   EntityModel<Student> model = EntityModel.of(student);
  //   model.add(linkTo(methodOn(StudentController.class).getStudentByIdLinkByMethod(studentId)).withSelfRel());
  //   return model;
  // }

  /**
   * default HATEOS
   * @param studentId
   * @return
   */
  @GetMapping("/v1/students/{studentId}")
  public EntityModel<Student> getStudentById_v1(@PathVariable("studentId") String studentId) {
    Student student = this.getStudentById(studentId);
    EntityModel<Student> entityModel = EntityModel.of(student);
    entityModel.add(Link.of("http://abcd.com:8080/v1/students/" + studentId));
    return entityModel;
  }

  /**
   * without static string url
   * @param studentId
   * @return
   */
  @GetMapping("/v2/students/{studentId}")
  public EntityModel<Student> getStudentById_v2(@PathVariable("studentId") String studentId) {
    Student student = this.getStudentById(studentId);
    EntityModel<Student> entityModel = EntityModel.of(student);
    entityModel.add(linkTo(methodOn(this.getClass()).getStudentById_v2(studentId)).withSelfRel());
    return entityModel;
  }

  /**
   * using affordances
   * @param studentId
   * @return
   */
  @GetMapping("/v3/students/{studentId}")
  public EntityModel<Student> getStudentById_v3(@PathVariable("studentId") String studentId) {
    Student student = this.getStudentById(studentId);
    EntityModel<Student> entityModel = EntityModel.of(student);

    Link delete
      = Affordances.of(
          linkTo(methodOn(this.getClass())
                        .deleteStudent(studentId))
            .withRel("delete"))
          .afford(HttpMethod.DELETE)
          .withName("hi")
          .toLink();

    entityModel.add(
      linkTo(methodOn(this.getClass()).getStudentById_v3(studentId)).withSelfRel()
      , delete
      , linkTo(methodOn(this.getClass()).updateStudent(studentId, null)).withRel("update")
          .andAffordance(afford(methodOn(this.getClass()).deleteStudent(studentId)))
    );

    return entityModel;
  }




  private Student getStudentById(String studentId) {
    return database.get(studentId);
  }



  @PostMapping("/students")
  public Student saveStudent(@RequestBody Student student) {
    return database.put(student.getId(), student);
  }

  @PutMapping("/students/{studentId}")
  public Student updateStudent(
    @PathVariable("studentId")String studentId
    , @RequestBody Student student) {
    
    return database.put(studentId, student);
  }

  @DeleteMapping("/students/{studentId}")
  public Student deleteStudent(@PathVariable("studentId")String studentId) {
    return database.remove(studentId);
  }
  
  
}