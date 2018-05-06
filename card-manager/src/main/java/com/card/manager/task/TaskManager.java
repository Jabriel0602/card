package com.card.manager.task;

import com.card.domain.order.Order;
import com.card.domain.refund.Refund;
import com.card.domain.task.Task;

/**
 * @author yangzhanbang
 * @date 2018/4/14 9:34
 * @desc
 */
public interface TaskManager {

	Order payment(Long orderId);

	void supplierCreateHandler(Long orderId);

	void supplierRechargeHandler(Long orderId);

	/**
	 * 更新订单
	 * 更新退款
	 * 插入task
	 * @param orderId
	 */
	void updateTaskAndRefund(Long orderId);

	void refundHandler(Long orderId);

}
