package com.card.domain.constant;

import com.alibaba.fastjson.JSON;
import com.card.domain.switchs.SwitchStatusEnum;
import com.google.common.collect.Lists;

import java.util.Random;

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
	CARD_ADIMAGES("CARD_ADIMAGES", "首页轮播图", 60*60*24*3, JSON.toJSONString(Lists.newArrayList())),

	/**
	 * 前台-首页ICON 模块
	 */
	CARD_ICONS("CARD_ICONS", "ICON", 60*60*24*3,JSON.toJSONString(Lists.newArrayList())),

	/**
	 * switch数据
	 */
	CARD_SWITCH("CARD_SWITCH", "开关配置", 60*60*24*3,JSON.toJSONString(SwitchStatusEnum.SWITCH_ON.getCode()));

	private String value;//值
	private String desc;//中文描述
	private long exp;    //单位：秒
	private String defaultValue;

	CacheKeyEnum(String value, String desc, int exp,String defaultValue) {
		this.value = value;
		this.desc = desc;
		this.exp = exp;
		this.defaultValue=defaultValue;
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

	public long getExp() {
		//原有时间基础上增加 一个随机数 10分钟
		return exp+new Random().nextInt(10)*60;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Override
	public String toString() {
		return "CacheKeyEnum{" +
				"value='" + value + '\'' +
				", desc='" + desc + '\'' +
				", exp=" + exp +
				", defaultValue='" + defaultValue + '\'' +
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
