package com.card.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.card.domain.task.Task;
import org.springframework.stereotype.Repository;

@Mapper
public interface TaskDao {
    int insert(@Param("task") Task task);

    int insertSelective(@Param("task") Task task);

    int insertList(@Param("tasks") List<Task> tasks);

    int update(@Param("task") Task task);
}
