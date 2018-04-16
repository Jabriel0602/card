package com.card.manager.handler;

import com.card.domain.order.Order;
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
public class RechargeHandler extends AbstractHandler{


	@Autowired
	private TaskManager taskManager;

	@Autowired
	private OrderService orderService;

	@Override
	public void handle(Task task) {
		Integer count = taskService.updateStatus(task.getTaskId(), TaskStatusEnum.SEND.getCode(), TaskStatusEnum.EXCUTE.getCode());
		if (count != 1) {
			return;
		}
		Order order = orderService.selectByOrderId(task.getOrderId());
		if (order == null) {
			log.error("生单异常,订单不存在:{}", order);
			throw new RuntimeException("生单异常,订单不存在");
		}

		taskManager.supplierRecharge(order.getOrderId());
		taskService.updateStatus(task.getTaskId(), TaskStatusEnum.EXCUTE.getCode(), TaskStatusEnum.SUCCESS.getCode());
	}

	@Override
	public void fail(Task task) {

	}
}
