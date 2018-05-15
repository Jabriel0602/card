package com.card.domain.constant;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/11
 * Description：
 * 缓存时间 ：单位：秒
 * 如果前缀有动态key拼入，使用方式：CacheKeyEnum.CY_CONFIG.value(type),会返回拼接后的全部key
 */

public enum  CacheKeyEnum {


	/**
	 * 前台-首页轮播图
	 */
	CARD_ADIMAGES("CARD_ADIMAGES", "首页轮播图", 60*60*24*7),

	/**
	 * 前台-首页ICON 模块
	 */
	CARD_ICONS("CARD_ICONS", "ICON", 60*60*24*7),


	/**
	 * switch数据
	 */
	CARD_CONFIG("CARD_CONFIG", "系统配置数据", 60*60*24*7);



	private String value;//值
	private String desc;//中文描述
	private int exp;    //单位：秒

	CacheKeyEnum(String value, String desc, int exp) {
		this.value = value;
		this.desc = desc;
		this.exp = exp;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	@Override
	public String toString() {
		return "CacheKeyEnum{" +
				"value='" + value + '\'' +
				", desc='" + desc + '\'' +
				", exp=" + exp +
				'}';
	}

	public String format(Object... values) {
		return String.format(value, values) + "_NEW2018";
	}
	public static CacheKeyEnum getCacheKeyEnum(String value) {
		for (CacheKeyEnum cacheKeyEnum : CacheKeyEnum.values()) {
			if (cacheKeyEnum.getValue().equals(value)) {
				return cacheKeyEnum;
			}
		}
		return null;
	}
}
