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

	@Autowired
	private RefundService refundService;

	@Override
	public void handle(Task task) {


		Order order = orderService.selectByOrderId(task.getOrderId());
		if (order == null) {
			log.error("生单异常,订单不存在:{}", order);
			throw new RuntimeException("生单异常,订单不存在");
		}

		Integer orderStatus = OrderStatusEnum.CREATE_FAIL_REFUND_INT.getCode();
		/**
		 * 状态 >= 生单成功 则设置为 充值失败(失败可以使 人工强制失败 和系统错误失败)
		 */
		if (order.getOrderStatus() >= OrderStatusEnum.CREATE_SUCCESS.getCode()) {
			orderStatus = OrderStatusEnum.RECHARGE_FAIL_REFUND_INT.getCode();
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
		if(refund==null){
			log.error("对应订单 退款单 不存在orderId:{}",order.getOrderId());
		}
		refund.setRefundStatus(RefundStatusEnum.REFUND_APPLYING.getCode());
		refundService.update(refund);

		/**
		 * 退款中-->退款失败/退款成功
		 */
		taskManager.refundHandler(task.getOrderId());

	}

	@Override
	public void fail(Task task) {

	}
}
