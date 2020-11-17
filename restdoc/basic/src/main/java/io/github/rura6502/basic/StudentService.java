package io.github.rura6502.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

  Map<Long, Student> table = new HashMap<>();
  

  public Student getById(Long id)  {
    return table.get(id);
  }

  public List<Student> getAllById(List<Long> ids) {
    return table.entrySet().parallelStream()
                  .filter(entry -> ids.contains(entry.getKey()))
                  .map(entry -> {
                    return entry.getValue();
                  }).collect(Collectors.toList());
  }

  public Student save(Student student) {
    table.put(student.getId(), student);
    return student;
  }

  public Student remove(long id) {
    return table.remove(id);
  }

}
