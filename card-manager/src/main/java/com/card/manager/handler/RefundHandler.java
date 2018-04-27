package com.card.manager.handler;

import com.card.domain.order.Order;
import com.card.domain.order.enums.OrderStatusEnum;
import com.card.domain.refund.Refund;
import com.card.domain.refund.enums.RefundStatusEnum;
import com.card.domain.task.Task;
import com.card.domain.task.enums.TaskStatusEnum;
import com.card.manager.task.TaskManager;
import com.card.service.order.OrderService;
import com.card.service.refund.RefundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/7 11:14
 * @desc 退款处理接口
 */
@Slf4j
public class RefundHandler extends AbstractHandler{



	@Autowired
	private TaskManager taskManager;

	@Autowired
	private OrderService orderService;

	private RefundService refundService;

	@Override
	public void handle(Task task) {


		Order order = orderService.selectByOrderId(task.getOrderId());
		if (order == null) {
			log.error("生单异常,订单不存在:{}", order);
			throw new RuntimeException("生单异常,订单不存在");
		}

		Integer orderStatus = OrderStatusEnum.RECHARGE_FAIL_REFUND_INT.getCode();
		if (order.getOrderStatus() >= OrderStatusEnum.RECHARGE_FAIL.getCode()) {
			orderStatus = OrderStatusEnum.CREATE_FAIL_REFUND_INT.getCode();
		}
		/**
		 *	充值失败-->退款中
		 *  或
		 *	生单失败-->退款中
		 */
		orderService.updateStatus(task.getOrderId(), order.getOrderStatus(), orderStatus);

		Refund refund=refundService.selectByOrderId(order.getOrderId());

		/*
		 *退款初始化-->向供应商申请退款
		 */
		refund.setRefundStatus(RefundStatusEnum.REFUND_APPLYING.getCode());
		refundService.update(refund);

		/**
		 * 退款中-->退款失败
		 */
		taskManager.refundHandler(task.getOrderId());

	}

	@Override
	public void fail(Task task) {

	}
}
