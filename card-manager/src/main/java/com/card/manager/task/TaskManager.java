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

	void updateTaskAndRefund(Task task);

	void refundHandler(Long orderId);

}
