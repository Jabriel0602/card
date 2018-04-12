package com.card.domain.config.enums;

public enum ConfigEnum {
	CARD_MAX_NUM(1, "CARD_MAX_NUM", "每种卡片最大限制");

	private Integer code;
	private String value;
	private String desc;

	ConfigEnum(Integer code, String value, String desc) {
		this.code = code;
		this.value = value;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "ConfigEnum{" +
				"code=" + code +
				", value='" + value + '\'' +
				", desc='" + desc + '\'' +
				'}';
	}
}
