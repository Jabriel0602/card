package com.card.common.annotations.authority;

/**
 * @author yangzhanbang
 * @date 2018/3/28 10:37
 * @desc
 */
public enum OperateTypeEnum {

	SELECT(1,"select","查询"),
	INSERT(2,"insert","插入"),
	DELETE(4,"delete","删除"),
	UPDATE(8,"update","修改"),
	ALL(15,"all","所有权限");

	private Integer code;
	private String key;
	private String value;

	OperateTypeEnum(Integer code, String key, String value) {
		this.code = code;
		this.key = key;
		this.value = value;
	}
}
