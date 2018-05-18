package com.card.domain.user;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/11
 * Description：
 */
public enum UserTypeEnum {
	USER			(1,"普通用户"),
	YY_MANAGE		(2,"运营管理"),
	KF_MANAGE		(4,"客服管理"),
	SUPER_MANAGE	(8,"系统管理");

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
