package com.card.common.annotations.shardrules;

/**
 * @author yangzhanbang
 * @date 2018/3/26 18:16
 * @desc
 */
public enum DataBaseShardRuleEnums {
	ORDER_ID_DBSHARDRULE(1,"OrderIdDBShardRule","订单维度分库"){
		@Override
		String show() {
			return null;
		}
	},
	USER_ID_DBSHARDRULE(1,"UserIdDBShardRule","用户维度分库"){
		@Override
		String show() {
			return null;
		}
	};

	private Integer code;
	private String key;
	private String value;

	abstract String show();

	DataBaseShardRuleEnums(Integer code, String key, String value) {
		this.code = code;
		this.key = key;
		this.value = value;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
