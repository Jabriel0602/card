package com.card.domain.task.enums;

import com.card.common.util.IdUtil;
import com.card.domain.YnEnum;
import com.card.domain.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/8
 * Description：
 */
public enum TaskTypeEnum {

	//对账系统 确认收款后 发送消息到业务系统
	ORDER_CONFIRM(1, TaskRetryStrategyEnum.MULTIPLIER_TIME, 30, 3, "对账任务", "confirmQueue"),
	ORDER_CREATE(2, TaskRetryStrategyEnum.EQUAL_TIME, 30, 8, "生单任务", "createOrderQueue"),
	ORDER_RECHARGE(4, TaskRetryStrategyEnum.MULTIPLIER_TIME, 30, 8, "充值任务", "rechargeQueue"),
	ORDER_REFUND(8, TaskRetryStrategyEnum.MULTIPLIER_TIME, 30, 8, "退款任务", "refundQueue");

	private Integer type;           //唯一标识
	private TaskRetryStrategyEnum taskRetryStrategy;    //重试策略
	private int timeUnit;           //超时时间，单位：分钟
	private int maxRetryTimes;    //最大重试次数
	private String desc;            //中文描述
	private String queue;           //队列名

	TaskTypeEnum(Integer type, TaskRetryStrategyEnum taskRetryStrategy, int timeUnit, int maxRetryTimes, String desc, String queue) {
		this.type = type;
		this.taskRetryStrategy = taskRetryStrategy;
		this.timeUnit = timeUnit;
		this.maxRetryTimes = maxRetryTimes;
		this.desc = desc;
		this.queue = queue;
	}


	public static TaskTypeEnum typeOf(Integer type) {
		for (TaskTypeEnum taskType : TaskTypeEnum.values()) {
			if (taskType.type.equals(type)) {
				return taskType;
			}
		}
		return null;
	}


	public Task buildTask(Long orderId,Long taskId) {
		Date date = new Date();
		Task task = new Task();
		task.setOrderId(orderId);
		task.setTaskId(taskId);
		task.setTaskType(type);
		task.setTaskDesc(desc);

		task.setCreatedTime(date);
		task.setModifiedTime(date);

		task.setExecuteStatus(TaskStatusEnum.INITIAL.getCode());
		task.setRetryTimes(0);
		task.setNextExecuteTime(taskRetryStrategy.getNextExecuteTime(date, this, task.getRetryTimes()));
		task.setRetryStrategy(taskRetryStrategy.getType());
		task.setMaxRetryTimes(maxRetryTimes);

		task.setCreatedTime(new Date());
		task.setYn(YnEnum.Y.getCode());
		return task;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public TaskRetryStrategyEnum getTaskRetryStrategy() {
		return taskRetryStrategy;
	}

	public void setTaskRetryStrategy(TaskRetryStrategyEnum taskRetryStrategy) {
		this.taskRetryStrategy = taskRetryStrategy;
	}

	public int getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(int timeUnit) {
		this.timeUnit = timeUnit;
	}


	public int getMaxRetryTimes() {
		return maxRetryTimes;
	}

	public void setMaxRetryTimes(int maxRetryTimes) {
		this.maxRetryTimes = maxRetryTimes;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	@Override
	public String toString() {
		return "TaskTypeEnum{" +
				"type=" + type +
				", taskRetryStrategy=" + taskRetryStrategy +
				", timeUnit=" + timeUnit +
				", maxRetryTimes=" + maxRetryTimes +
				", desc='" + desc + '\'' +
				", queue='" + queue + '\'' +
				'}';
	}

	public static TaskTypeEnum getTaskTypeEnum(Integer code){
		for (TaskTypeEnum taskTypeEnum:TaskTypeEnum.values()) {
			if(taskTypeEnum.getType().equals(code)){
				return taskTypeEnum;
			}
		}
		return null;
	}
}
