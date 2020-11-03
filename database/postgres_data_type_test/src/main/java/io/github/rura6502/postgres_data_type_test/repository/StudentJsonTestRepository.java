package io.github.rura6502.postgres_data_type_test.repository;

import java.sql.PreparedStatement;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonMappingException;

import io.github.rura6502.postgres_data_type_test.test_class.Student;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Repository
public class StudentJsonTestRepository {

  private final JdbcTemplate jdbcTemplate;

  private final ObjectMapper objMapper;

  public int save(Student student) {

    final String studentAsString;
    try {
      studentAsString = objMapper.writeValueAsString(student);
    } catch (JsonProcessingException jpex) {
      return 0;
    }
    return jdbcTemplate.update(conn -> {
      final String sql
        = "INSERT INTO public.tbl_test_json"
            + " VALUES(?, ?::JSON)"
          ;
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, student.getName());
      pstmt.setObject(2, studentAsString);
      return pstmt;
    });
  }

  public Student findByName(String name) {
      return jdbcTemplate
                  .queryForObject(
                    "SELECT * FROM public.tbl_test_json WHERE id = ?"
                    , (rs, rowNum) -> {
                      try {
                        return objMapper.readValue(rs.getString("test_jsonb"), Student.class);
                      } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                        return null;
                      }
                      
                    }
                    , new Object[] {name});
  }
}