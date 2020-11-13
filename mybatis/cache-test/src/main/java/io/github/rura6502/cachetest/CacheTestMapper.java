package io.github.rura6502.cachetest;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CacheTestMapper {

  public List<Map<String, Object>> selectAll();

  public List<Map<String, Object>> selectWithCache(@Param("id") int id);

  public List<Map<String, Object>> selectWithoutCache(@Param("id") int id);

  
  
}
