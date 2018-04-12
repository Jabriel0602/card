package com.card.domain;

/**
 * Created by zhanghaichao3
 */
public enum YnEnum {
	N((byte) 0, "无效"), Y((byte) 1, "有效");

	private Byte code;
	private String desc;

	YnEnum(Byte code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Byte getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
