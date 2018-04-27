package com.card.domain;

/**
 * MAN 端 需要做增删改权限控制的功能(方法)  枚举
 *   code 二进制算法,前端屏蔽增删改功能
 */

public enum MethodTypeEnum {

	NOTICE(1, "NOTICE", "公告"),

	ADIMAGE(2, "ADIMAGE", "轮播图"),

	ICON(4, "ICON", "ICON活动"),

	CONFIG(8, "CONFIG", "配置管理"),

	CACHE(16, "CACHE", "缓存管理"),

	TASK(32, "TASK", "任务管理"),

	AUTHORITY(64, "AUTHORITY", "权限管理");

	private Integer code;
	private String type;
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
