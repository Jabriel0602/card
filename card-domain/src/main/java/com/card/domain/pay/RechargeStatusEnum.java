package com.card.domain.pay;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/9
 * Description：
 */
public enum RechargeStatusEnum {

	NOT_RECHARGE	(10, "未充值"),
	HAVA_RECHARGE	(20, "已充值");

	private Integer code;
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	RechargeStatusEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static RechargeStatusEnum getRechargeStatusEnum(Integer code) {
		for (RechargeStatusEnum rechargeStatusEnum : values()) {
			if (code == rechargeStatusEnum.getCode()) {
				return rechargeStatusEnum;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "RechargeStatusEnum{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				'}';
	}
}
