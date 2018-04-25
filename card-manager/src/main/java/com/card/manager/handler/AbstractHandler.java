package com.card.manager.handler;

import com.alibaba.fastjson.JSON;
import com.card.domain.task.Task;
import com.card.domain.task.enums.TaskStatusEnum;
import com.card.service.task.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.*;

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
			System.out.println("QueueMessageListener监听到了文本消息：" + taskString);
			task = JSON.parseObject(taskString, Task.class);
			// 乐观锁排重
			int count = taskService.updateStatus(task.getTaskId(), TaskStatusEnum.SEND.getCode(), TaskStatusEnum.EXCUTE.getCode());
			if (count == 0) {
				log.info("the task has been executed. task=" + task);
			}else {
				handle(task);
			}
		} catch (Exception e) {
			log.error("an error occur.{}", e);
			boolean isFail = taskService.isFail(task);
			if (isFail) {
				fail(task);
			} else {
				taskService.updateStatus(task.getTaskId(), TaskStatusEnum.SUCCESS.getCode(), TaskStatusEnum.INITIAL.getCode());
			}
		}
	}

	@Override
	public abstract void fail(Task task);
}
