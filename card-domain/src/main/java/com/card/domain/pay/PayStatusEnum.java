package com.card.domain.pay;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/9
 * Description：
 */
public enum PayStatusEnum {

	NOT_PAY	(100, "未支付"),
	HAVE_PAY	(200, "已支付");

	private Integer code;
	private String desc;

	public static PayStatusEnum getPayStatusEnum(int code) {
		for (PayStatusEnum payStatusEnum : values()) {
			if (code == payStatusEnum.getCode()) {
				return payStatusEnum;
			}
		}
		return null;
	}

	PayStatusEnum(Integer code, String desc) {
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
		return "PayStatusEnum{" +
				"code=" + code +
				", desc='" + desc + '\'' +
				'}';
	}
}
