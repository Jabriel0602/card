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
 * @desc 生单处理接口
 */
@Slf4j
public class CreateOrderHandler extends AbstractHandler {


	@Autowired
	private TaskManager taskManager;

	@Autowired
	private OrderService orderService;

	@Override
	public void handle(Task task) {
		/**
		 * 支付成功--->生单中
		 */
		Integer orderCount = orderService.updateStatus(task.getOrderId(), OrderStatusEnum.PAY_SUCCESS.getCode(), OrderStatusEnum.CREATE_ING.getCode());

		if (orderCount != 1) {
			return;
		}

		Order order = orderService.selectByOrderId(task.getOrderId());
		if (order == null) {
			log.error("生单异常,订单不存在:{}", order);
			throw new RuntimeException("生单异常,订单不存在");
		}

		//生单中--->生单成功
		taskManager.supplierCreateHandler(order.getOrderId());
	}

	@Override
	public void fail(Task task) {
		log.info("【生单任务执行失败】任务id:{},任务id:{}", task.getOrderId(), task.getTaskId());
		taskService.updateStatus(task.getTaskId(), task.getExecuteStatus(), TaskStatusEnum.FAIL.getCode());

		/**
		 * 支付成功->生单失败->退款中
		 */
		taskManager.updateTaskAndRefund(task);
	}
}
