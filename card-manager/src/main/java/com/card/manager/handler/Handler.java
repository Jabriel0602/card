package com.card.manager.handler;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/6 19:58
 * @desc 消息处理接口
 */
public interface Handler<T> {

	/**
	 * 处理消息
	 * @param t 消息类型
	 * @return
	 */
	void handle(T t);

	/**
	 * 失败处理
	 * @param t 消息类型
	 * @return
	 */
	void fail(T t);
}
