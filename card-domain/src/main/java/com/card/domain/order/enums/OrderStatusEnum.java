package com.card.domain.order.enums;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/9
 * Description：
 */
public enum OrderStatusEnum {
	/**
	 * 状态码说明
	 * 第1位:支付状态 第2位:生单状态 第3位:充值状态 第4位:退款状态
	 * 数字含义说明: 0代表未进行 1:中间态 2:成功 4:失败
	 * 因为下单后才会有状态
	 * 所有状态的起点:下单成功
	 */
	NOT_PAY			(0000, "未支付","未支付"),
	PAY_SUCCESS		(2000, "支付成功","支付成功"),

	/**
	 * 特殊状态人工退款 强制订单失败  强制到最近的失败状态
	 */

	CREATE_ING		(2100, "支付成功->生单中","生单中"),
	CREATE_SUCCESS	(2200, "支付成功->生单成功","生单成功"),
	CREATE_FAIL		(2400, "支付成功->生单失败","生单失败"),
	CREATE_FAIL_REFUND_INT		(2401, "支付成功->生单失败->退款中","退款中"),
	CREATE_FAIL_REFUND_SUCCESS	(2402, "支付成功->生单失败->退款成功","退款成功"),
	CREATE_FAIL_REFUND_FAIL		(2404, "支付成功->生单失败->退款失败","退款失败"),

	RECHARGE_ING	(2210, "支付成功->生单成功->充值中","充值中"),
	RECHARGE_SUCCESS(2220, "支付成功->生单成功->充值成功","充值成功"),
	RECHARGE_FAIL	(2240, "支付成功->生单成功->充值失败","充值失败"),
	RECHARGE_FAIL_REFUND_INT	(2241, "支付成功->生单成功->充值失败->退款中","退款中"),
	RECHARGE_FAIL_REFUND_SUCCESS(2242, "支付成功->生单成功->充值失败->退款成功","退款成功"),
	RECHARGE_FAIL_REFUND_FAIL	(2244, "支付成功->生单成功->充值失败->退款失败","退款失败");

	private Integer code;
	private String desc;
	private String simpleDesc;

	OrderStatusEnum(Integer code, String desc, String simpleDesc) {
		this.code = code;
		this.desc = desc;
		this.simpleDesc = simpleDesc;
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

	public String getSimpleDesc() {
		return simpleDesc;
	}

	public void setSimpleDesc(String simpleDesc) {
		this.simpleDesc = simpleDesc;
	}

	@Override
	public String toString() {
		return "OrderStatusEnum{" +
				"code=" + code +
				", desc='" + desc + '\'' +
				", simpleDesc='" + simpleDesc + '\'' +
				'}';
	}

	public static String getDescByCode(Integer code){
		for (OrderStatusEnum orderStatusEnum:OrderStatusEnum.values()) {
			if(orderStatusEnum.getCode().equals(code)){
				return orderStatusEnum.getDesc();
			}
		}
		return null;
	}

	public static String getSimpleDescByCode(Integer code){
		for (OrderStatusEnum orderStatusEnum:OrderStatusEnum.values()) {
			if(orderStatusEnum.getCode().equals(code)){
				return orderStatusEnum.getSimpleDesc();
			}
		}
		return null;
	}
}
