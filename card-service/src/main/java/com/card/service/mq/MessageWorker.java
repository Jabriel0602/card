package com.card.service.mq;

import com.card.domain.task.Task;
import com.card.domain.task.enums.TaskStatusEnum;
import com.card.domain.task.enums.TaskTypeEnum;
import com.card.service.task.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@EnableScheduling()//注解实现配置
@Lazy(value = false)
public class MessageWorker {
	private static final int SIZE = 100;
	@Autowired
	private Producer producer;

	@Autowired
	private TaskService taskService;

	//	每隔3s执行一次
	@Scheduled(cron = "*/3 * * * * ?")
	public void sendTask() {
		/**
		 * 获取前100条 任务状态为初始化的任务
		 */
		List<Task> taskList = taskService.selectTaskInitial(SIZE);
		for (Task task : taskList) {
			sendTaskManager(task);
		}
	}

	@Transactional
	public void sendTaskManager(Task task){
		taskService.updateStatus(task.getTaskId(),TaskStatusEnum.INITIAL.getCode(),TaskStatusEnum.SEND.getCode());
		producer.sendTask(task);
	}
}
