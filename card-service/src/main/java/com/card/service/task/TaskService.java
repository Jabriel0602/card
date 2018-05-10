package com.card.service.task;

import java.util.List;
import com.card.domain.task.Task;
import com.card.domain.task.enums.TaskTypeEnum;

public interface TaskService{

    int insert(Task task);

    int insertSelective(Task task);

    int insertList(List<Task> tasks);

    int update(Task task);

    int updateStatus(Long taskId, int oldStatus, int newStatus);

    boolean isFail(Task task);

    Task selectTaskById(Long taskId);

    List<Task> selectTaskByParam(Task task);

    List<Task> selectAllTask();

    List<Task> selectTaskInitial(int size);

    List<Task> selectTaskByOrderId(Long orderId,Integer taskType);


}
