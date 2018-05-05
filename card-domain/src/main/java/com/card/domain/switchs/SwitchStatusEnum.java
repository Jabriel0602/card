package com.card.domain.switchs;

import com.card.domain.task.enums.TaskTypeEnum;

/**
 * @author yangzhanbang
 * @date 2018/5/4 15:42
 * @desc
 */
public enum  SwitchStatusEnum {

	SWITCH_OFF(-1,"关闭"),
	SWITCH_ON(1,"开启");

	SwitchStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private Integer code;
	private String desc;

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

	@Override
	public String toString() {
		return "SwitchStatusEnum{" +
				"code=" + code +
				", desc='" + desc + '\'' +
				'}';
	}

	public static SwitchStatusEnum getByCode(Integer code) {
		for (SwitchStatusEnum switchStatusEnum : SwitchStatusEnum.values()) {
			if (switchStatusEnum.code.equals(code)) {
				return switchStatusEnum;
			}
		}
		return null;
	}

}
