package com.card.domain;

public enum ModuleTypeEnum {

	COMMON(1, "COMMON", "运营模块"),

	CUSTOMER(2, "CUSTOMER", "客服模块"),

	SYSTEM(4, "SYSTEM", "系统管理");

	//二进制码
	private Integer code;
	//tab栏类型值
	private String type;
	//tab栏中文描述
	private String desc;

	ModuleTypeEnum(Integer code, String type, String desc) {
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

	/**
	 * 获取指定key的描述值
	 *
	 * @param code
	 * @return
	 */
	public static String getDesc(int code) {
		for (ModuleTypeEnum type : ModuleTypeEnum.values()) {
			if (type.getCode() == code) {
				return type.getDesc();
			}
		}
		return null;
	}

	public static String getType(Integer code) {
		for (ModuleTypeEnum moduleTypeEnum : ModuleTypeEnum.values()) {
			if (moduleTypeEnum.getCode() .equals(code)) {
				return moduleTypeEnum.getType();
			}
		}
		return null;
	}

	public static String getByType(String type) {
		for (ModuleTypeEnum moduleTypeEnum : ModuleTypeEnum.values()) {
			if (moduleTypeEnum.getType().equals(type)) {
				return moduleTypeEnum.getType();
			}
		}
		return null;
	}
}
