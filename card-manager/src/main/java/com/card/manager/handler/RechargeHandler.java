package com.card.manager.handler;

import com.card.domain.order.Order;
import com.card.domain.order.enums.OrderStatusEnum;
import com.card.domain.task.Task;
import com.card.domain.task.enums.TaskStatusEnum;
import com.card.manager.task.TaskManager;
import com.card.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/7 11:14
 * @desc 充值消息处理
 */
@Slf4j
public class RechargeHandler extends AbstractHandler {


	@Autowired
	private TaskManager taskManager;

	@Autowired
	private OrderService orderService;

	@Override
	public void handle(Task task) {

		/**
		 * 生单成功--->充值中
		 */

		Integer orderCount = orderService.updateStatus(task.getOrderId(), OrderStatusEnum.CREATE_SUCCESS.getCode(), OrderStatusEnum.RECHARGE_ING.getCode());

		if (orderCount != 1) {
			return;
		}
		Order order = orderService.selectByOrderId(task.getOrderId());
		if (order == null) {
			log.error("生单异常,订单不存在:{}", order);
			throw new RuntimeException("生单异常,订单不存在");
		}

		//订单充值中--->充值成功
		taskManager.supplierRechargeHandler(order.getOrderId());
	}

	@Override
	public void fail(Task task) {
		log.info("【充值任务执行失败】任务id:{},任务id:{}", task.getOrderId(), task.getTaskId());
		taskService.updateStatus(task.getTaskId(), task.getExecuteStatus(), TaskStatusEnum.FAIL.getCode());
		/**
		 * 支付成功->生单成功->充值失败->退款中
		 */
		taskManager.updateTaskAndRefund(task);
	}
}
