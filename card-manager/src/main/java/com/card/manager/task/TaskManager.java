package com.card.manager.task;

import com.card.domain.order.Order;

/**
 * @author yangzhanbang
 * @date 2018/4/14 9:34
 * @desc
 */
public interface TaskManager {

	Order payment(Long orderId);

	void supplierCreate(Long orderId);

	void supplierRecharge(Long orderId);

	void refund(Long orderId);

}
