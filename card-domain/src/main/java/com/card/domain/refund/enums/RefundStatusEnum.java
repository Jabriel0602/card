package com.card.domain.refund.enums;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/9
 * Description：
 */
public enum  RefundStatusEnum {
	REFUND_NULL		(0,"无退款记录"),
	REFUND_INT		(1, "退款初始化"),
	REFUND_APPLYING	(2, "已经向支付系统申请退款"),
	REFUND_FAIL		(4, "退款失败"),
	REFUND_SUCCESS	(8, "退款成功");

	private Integer code ;
	private String desc;

	public static RefundStatusEnum getRefundStatusEnum(Integer code) {
		for (RefundStatusEnum refundStatusEnum : values()) {
			if (code == refundStatusEnum.getCode()) {
				return refundStatusEnum;
			}
		}
		return null;
	}

	RefundStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
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
		return "RefundStatusEnum{" +
				"code=" + code +
				", desc='" + desc + '\'' +
				'}';
	}
}
