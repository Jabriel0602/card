package com.card.manager.handler;

import com.card.domain.order.Order;
import com.card.domain.order.enums.OrderStatusEnum;
import com.card.domain.pay.PayStatusEnum;
import com.card.domain.task.Task;
import com.card.domain.task.enums.TaskStatusEnum;
import com.card.domain.task.enums.TaskTypeEnum;
import com.card.manager.task.TaskManager;
import com.card.service.order.OrderService;
import com.card.service.task.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/7 11:14
 * @desc 对账模块
 */
@Slf4j
public class ConfirmHandler extends AbstractHandler {

	@Autowired
	private OrderService orderService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskManager taskManager;

	@Override
	public void handle(Task task) {
		Order order = orderService.selectByOrderId(task.getOrderId());
		if (order.getPayStatus().equals(PayStatusEnum.HAVE_PAY.getCode())) {
			//已支付-->已支付未充值
			Task createOrderTask = TaskTypeEnum.ORDER_CREATE.buildTask(order.getOrderId());
			taskService.updateStatus(task.getTaskId(),OrderStatusEnum.PAY_SUCCESS.getCode(),OrderStatusEnum.CREATE_ING.getCode());
			taskService.insert(createOrderTask);
		}
	}

	@Override
	public void fail(Task task) {
		log.info("【对账任务执行失败】任务id:{},任务id:{}", task.getOrderId(), task.getTaskId());
		taskService.updateStatus(task.getTaskId(), task.getExecuteStatus(), TaskStatusEnum.FAIL.getCode());
	}
}
