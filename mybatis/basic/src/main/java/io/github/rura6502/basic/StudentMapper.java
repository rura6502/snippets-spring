package io.github.rura6502.basic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * TestMapper
 */
@Mapper
public interface StudentMapper {

  public List<Map<String, Object>> findAll();

  public List<Student> findAllWithConditions(
    @Param("ids") List<Long> ids
    , @Param("names") List<String> names
    , @Param("genders") List<Gender> genders);

  public List<Student> findAllWithConditions(
    @Param("names") List<String> names
    , @Param("genders") List<Gender> genders);

  public List<Student> findAllByName(
    @Param("names") List<String> names);



  public int save(Student student);
}
