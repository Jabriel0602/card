package com.card.controller;

import com.card.domain.order.enums.OrderStatusEnum;
import com.card.domain.result.APIResult;
import com.card.domain.task.Task;
import com.card.domain.task.enums.TaskStatusEnum;
import com.card.domain.task.enums.TaskTypeEnum;
import com.card.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/6 16:10
 */
@Controller
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("")
	public String listTask(Map map,Task task){
		List<Task> taskList=taskService.selectTaskByParam(task);
		map.put("taskList",taskList);
		map.put("task",new Task());
		map.put("TaskStatusEnums",TaskStatusEnum.values());
		map.put("TaskStatusEnum",TaskStatusEnum.class);
		map.put("TaskTypeEnums",TaskTypeEnum.values());
		map.put("TaskTypeEnum",TaskTypeEnum.class);
		return "task/list";
	}

	@PutMapping("/{taskId}")
	@ResponseBody
	public APIResult<Integer> listTask(@PathVariable Long taskId){
		Task task=taskService.selectTaskById(taskId);
		if(task==null){
			return new APIResult<>(HttpStatus.FORBIDDEN.value(),0);
		}
		task.setNextExecuteTime(new Date());
		task.setRetryTimes(0);
		task.setExecuteStatus(TaskStatusEnum.INITIAL.getCode());
		Integer count=taskService.update(task);
		return new APIResult<>(count);
	}

}
