package com.card.domain.refund.enums;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/11
 * Description：
 */
public enum RefundTypeEnum {
	NORMAL_REFUND		(1, "普通退款"),
	AFTER_SALE_ONLINE	(2, "售后退款");

	private Integer code;
	private String desc;

	RefundTypeEnum(Integer code, String desc) {
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
		return "RefundTypeEnum{" +
				"code=" + code +
				", desc='" + desc + '\'' +
				'}';
	}
}
