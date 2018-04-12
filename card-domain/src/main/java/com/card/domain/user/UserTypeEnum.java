package com.card.domain.user;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/11
 * Description：
 */
public enum UserTypeEnum {
	USER			(1,"普通用户"),
	MANAGE			(2,"企业客服"),
	SUPER_MANAGE	(4,"系统管理");

	private Integer code;

	private String desc;

	UserTypeEnum(Integer code, String desc) {
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


	@Override
	public String toString() {
		return "UserTypeEnum{" +
				"code=" + code +
				", desc='" + desc + '\'' +
				'}';
	}
}
