package com.card.domain;

/**
 * MAN 端 需要做增删改权限控制的功能(方法)  枚举
 *   code 二进制算法
 */

public enum MethodTypeEnum {

	ADIMAGE(1, "ADIMAGE", "轮播图"),

	ICON(2, "ICON", "ICON活动"),

	SWITCH(4, "SWITCH", "开关管理"),

	CACHE(8, "CACHE", "缓存管理"),

	TASK(16, "TASK", "任务管理"),

	AUTHORITY(32, "AUTHORITY", "权限管理");
	/**
	 * 二进制值
	 */
	private Integer code;
	/**
	 * 方法 名
	 */
	private String type;
	/**
	 * 方法 名 中文描述
	 */
	private String desc;

	MethodTypeEnum(Integer code, String type, String desc) {
		this.code = code;
		this.type = type;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "MethodTypeEnum{" +
				"code=" + code +
				", type='" + type + '\'' +
				", desc='" + desc + '\'' +
				'}';
	}
}
