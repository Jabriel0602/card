package com.card.domain.task;

import lombok.Data;

import javax.jms.Destination;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/8 11:18
 */
@Data
public class TaskMessage {
	private Destination queue;
	private String taskString;
}
