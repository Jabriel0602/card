package com.card.domain.switchs;

import java.util.Date;

/**
 * @author yangzhanbang
 * @date 2018/5/4 15:41
 * @desc
 */
public enum SwitchEnum {
	SUBMIT_ORDER_SWITCH(1L, "下单开关", SwitchStatusEnum.SWITCH_ON),
	BIND_CARD_SWITCH(2L, "绑卡开关", SwitchStatusEnum.SWITCH_ON);


	SwitchEnum(Long code, String desc, SwitchStatusEnum switchStatusEnum) {
		this.code = code;
		this.desc = desc;
		this.switchStatusEnum = switchStatusEnum;
	}

	private Long code;
	private String desc;
	private SwitchStatusEnum switchStatusEnum;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public SwitchStatusEnum getSwitchStatusEnum() {
		return switchStatusEnum;
	}

	public void setSwitchStatusEnum(SwitchStatusEnum switchStatusEnum) {
		this.switchStatusEnum = switchStatusEnum;
	}

	public CardSwitch buildSwitch() {
		CardSwitch cardSwitch=new CardSwitch();
		cardSwitch.setSwitchId(getCode());
		cardSwitch.setSwitchDesc(getDesc());
		cardSwitch.setSwitchStatus(getSwitchStatusEnum().getCode());
		cardSwitch.setSwitchStatusDesc(getSwitchStatusEnum().getDesc());
		cardSwitch.setCreatedTime(new Date());
		cardSwitch.setModifiedTime(new Date());
		return cardSwitch;
	}

	public static SwitchEnum getSwitchEnum(Long code) {
		for (SwitchEnum switchEnum : values()) {
			if (code.equals(switchEnum.getCode())) {
				return switchEnum;
			}
		}
		return null;
	}
}
