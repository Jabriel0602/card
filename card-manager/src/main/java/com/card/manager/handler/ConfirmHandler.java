package com.card.manager.handler;

import com.card.domain.order.Order;
import com.card.domain.pay.PayStatusEnum;
import com.card.domain.task.Task;
import com.card.domain.task.enums.TaskStatusEnum;
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

	/**
	 * 目前无用，正式投产时候 消费 对账消息
	 * 目前 模拟支付
	 * @param task
	 */

	@Override
	public void handle(Task task) {
		Integer count = taskService.updateStatus(task.getTaskId(), TaskStatusEnum.SEND.getCode(), TaskStatusEnum.EXCUTE.getCode());
		if (count != 1) {
			return;
		}
		Order order = orderService.selectByOrderId(task.getOrderId());
		if (order.getPayStatus().equals(PayStatusEnum.HAVE_PAY.getCode())) {
			//已支付-->已支付未充值
			taskManager.payment(order.getOrderId());
		}
		taskService.updateStatus(task.getTaskId(), TaskStatusEnum.EXCUTE.getCode(), TaskStatusEnum.SUCCESS.getCode());
	}

	@Override
	public void fail(Task task) {
		log.info("【对账任务执行失败】任务id:{},任务id:{}", task.getOrderId(), task.getTaskId());
		taskService.updateStatus(task.getTaskId(), task.getExecuteStatus(), TaskStatusEnum.FAIL.getCode());
	}
}
