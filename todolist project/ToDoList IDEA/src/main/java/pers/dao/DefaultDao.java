package pers.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DefaultDao {
    @Select("${sql}")
    List<Map<String, Object>> select(@Param("sql") String sql);

    @Select("${sql}")
    int selectCount(@Param("sql") String sql);

    @Select("${sql}")
    Map<String, Object> selectOne(@Param("sql") String sql);

    @Update("${sql}")
    void update(@Param("sql") String sql);

    @Insert("${sql}")
    void insert(@Param("sql") String sql);

    @Delete("${sql}")
    void delete(@Param("sql") String sql);
}
