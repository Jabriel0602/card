package com.card.domain.task.enums;

import com.card.common.util.DateUtil;
import com.card.domain.task.Task;

import java.util.Date;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/8
 * Description：
 */
public enum TaskRetryStrategyEnum {


	EQUAL_TIME(1, "等时间间隔"),            //等待时间 为任务重试时间间隔
	MULTIPLIER_TIME(2, "倍数时间间隔"),    //等待时间 为任务重试时间间隔*  (重试次数)retryTimes
	EXPONENTIAL_TIME(4, "指数时间间隔");   //等待时间 为任务重试时间间隔*  (int) Math.pow(2, retryTimes);

	TaskRetryStrategyEnum(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	private Integer type;
	private String desc;


	public Date getNextExecuteTime(Date date, TaskTypeEnum taskTypeEnum, Integer retryTimes) {
		switch (type) {
			case 1:
				return DateUtil.getAddMinuteDate(date, taskTypeEnum.getTimeUnit());
			case 2:
				return DateUtil.getAddMinuteDate(date, taskTypeEnum.getTimeUnit() * retryTimes);
			case 3:
				return DateUtil.getAddMinuteDate(date, taskTypeEnum.getTimeUnit() * (int) Math.pow(2, retryTimes));
			default:
				return DateUtil.getAddMinuteDate(date, taskTypeEnum.getTimeUnit());
		}
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static TaskRetryStrategyEnum getTaskRetryStrategyEnum(Integer code){
		for (TaskRetryStrategyEnum taskStatusEnum:TaskRetryStrategyEnum.values()) {
			if(taskStatusEnum.getType()==code){
				return taskStatusEnum;
			}
		}
		return null;
	}
}
