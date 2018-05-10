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

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@EnableScheduling()//注解实现配置
@Lazy(value = false)
public class MessageWorker {

	private static final int SIZE = 20;

	@Autowired
	private Producer producer;

	@Autowired
	private TaskService taskService;

	/**
	 * 秒 分 时 每月某日 月 周几 Year
	 */
//	每隔两分钟执行一次
	@Scheduled(cron = "* */2 * * * ?")
	public void sendTask() {

		List<Task> taskList = taskService.selectTaskInitial(SIZE);
		for (Task task : taskList) {
			taskService.updateStatus(task.getTaskId(),TaskStatusEnum.INITIAL.getCode(),TaskStatusEnum.SEND.getCode());
			producer.sendTask(task);
		}
	}
}
