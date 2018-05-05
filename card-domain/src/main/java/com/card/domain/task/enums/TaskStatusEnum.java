package com.card.domain.task.enums;

import lombok.Data;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/8
 * Description：
 */
public enum TaskStatusEnum {

	INITIAL(1, "初始化"),
	SEND(2, "已发送"),
	EXCUTE(4, "开始执行"),
	SUCCESS(8, "执行成功"),
	FAIL(16, "执行失败");

	private Integer code;     //二进制唯一标识
	private String desc;        //状态中文描述

	TaskStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
