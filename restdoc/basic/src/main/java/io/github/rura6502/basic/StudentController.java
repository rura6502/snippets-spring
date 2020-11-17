package io.github.rura6502.basic;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

  private final StudentService studentService;



  @GetMapping("/{id}")
  public Student getById(@PathVariable("id") long id) {
    return studentService.getById(id);
  }

  @PostMapping
  public Student save(@RequestBody Student student) {
    return studentService.save(student);
  }

  @DeleteMapping("/{id}")
  public Student remove(@PathVariable("id") long id) {
    return studentService.remove(id);
  }
}