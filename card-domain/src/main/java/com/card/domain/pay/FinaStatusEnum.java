package com.card.domain.pay;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/9
 * Description：
 */
public enum FinaStatusEnum {
	NOT_JIESUAN	(0, "未结算"),
	HAVE_JIESUAN	(1, "已结算");

	private Integer code;
	private String desc;

	FinaStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static FinaStatusEnum valueOf(Integer code) {
		for (FinaStatusEnum finaStatusEnum : FinaStatusEnum.values()) {
			if (finaStatusEnum.getCode() .equals(code)) {
				return finaStatusEnum;
			}
		}
		return null;
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
		return "FinaStatusEnum{" +
				"code=" + code +
				", desc='" + desc + '\'' +
				'}';
	}
}