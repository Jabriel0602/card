package com.card.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.card.domain.task.Task;

@Mapper
public interface TaskDao {
    int insert(@Param("task") Task task);

    int insertSelective(@Param("task") Task task);

    int insertList(@Param("tasks") List<Task> tasks);

    int update(@Param("task") Task task);

    int updateStatus(@Param("taskId") Long taskId,@Param("oldStatus")int oldStatus,@Param("newStatus") int newStatus);

    List<Task> selectTaskInitial(int size);

}
