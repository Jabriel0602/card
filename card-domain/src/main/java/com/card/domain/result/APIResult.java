package com.card.domain.result;

import lombok.Data;

/**
 * Created with card
 * @author: yangzhanbang
 * Date: 2018/2/8
 * Time: 17:28
 */
@Data
public class APIResult<T> {
	/**
	 * 状态码
	 */
	private String code;

	/**
	 * 状态码描述
	 */
	private String msg;

	/**
	 * 实体泛型
	 */
	private T data;
}
