package com.card.domain.result;

import lombok.Data;
import org.springframework.http.HttpStatus;

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
	private Integer code;

	/**
	 * 状态码描述
	 */
	private String msg;

	/**
	 * 实体泛型
	 */
	private T data;

	public APIResult(){

	}

	public APIResult(T data){
		this.code=HttpStatus.OK.value();
		this.data=data;
	}

	public APIResult(Integer code,T data){
		this.code=code;
		this.data=data;
	}
}
