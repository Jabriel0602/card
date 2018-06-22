package com.card.manager.handler;

import com.alibaba.fastjson.JSON;
import com.card.domain.task.Task;
import com.card.domain.task.enums.TaskRetryStrategyEnum;
import com.card.domain.task.enums.TaskStatusEnum;
import com.card.domain.task.enums.TaskTypeEnum;
import com.card.service.task.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.*;
import java.util.Date;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/6 20:00
 */
@Slf4j
public abstract class AbstractHandler implements Handler<Task>, MessageListener {

	@Autowired
	TaskService taskService;

	@Override
	public void onMessage(Message message) {
		TextMessage tm = (TextMessage) message;
		Task task = null;
		try {
			String taskString = tm.getText();
			log.info("QueueMessageListener监听到了文本消息：{}", taskString);
			task = JSON.parseObject(taskString, Task.class);
			/**
			 * 乐观锁排重
			 * 任务已发送--->执行中
			 */
			int count = taskService.updateStatus(task.getTaskId(), TaskStatusEnum.SEND.getCode(), TaskStatusEnum.EXCUTE.getCode());
			if (count == 0) {
				log.info("the task has been executed. task:{}", task);
			} else {
				handle(task);
				/**
				 * 任务执行中--->执行成功
				 */
				taskService.updateStatus(task.getTaskId(), TaskStatusEnum.EXCUTE.getCode(), TaskStatusEnum.SUCCESS.getCode());
			}
		} catch (Exception e) {
			log.error("an error occur.{}", e);
			boolean isFail = taskService.isFail(task);
			if (isFail) {
				fail(task);
			} else {
				//获取枚举类中任务重试机制
				TaskRetryStrategyEnum taskRetryStrategyEnum = TaskRetryStrategyEnum.getTaskRetryStrategyEnum(task.getRetryStrategy());
				//获取枚举类中任务类型
				TaskTypeEnum taskTypeEnum = TaskTypeEnum.getTaskTypeEnum(task.getTaskType());
				//获取下次重试时间
				Date nextExecuteTime = taskRetryStrategyEnum.getNextExecuteTime(new Date(), taskTypeEnum, task.getRetryTimes());
				task.setNextExecuteTime(nextExecuteTime);

				task.setExecuteStatus(TaskStatusEnum.INITIAL.getCode());
				taskService.update(task);
//				taskService.updateStatus(task.getTaskId(), TaskStatusEnum.SUCCESS.getCode(), TaskStatusEnum.INITIAL.getCode());
			}
		}
	}

	@Override
	public abstract void fail(Task task);
}
