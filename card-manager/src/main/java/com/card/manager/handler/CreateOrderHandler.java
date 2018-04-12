package com.card.manager.handler;

import com.card.domain.order.Order;
import com.card.domain.task.Task;
import com.card.domain.task.enums.TaskStatusEnum;
import com.card.domain.task.enums.TaskTypeEnum;
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
	private OrderService orderService;

	@Override
	public void handle(Task task) {
		Order order = orderService.selectByOrderId(task.getOrderId());
		if (order == null) {
			log.error("生单异常,订单不存在:{}", order);
			throw new RuntimeException("生单异常,订单不存在");
		}
		Task rechargeTask = TaskTypeEnum.ORDER_RECHARGE.buildTask(order.getOrderId());
		taskService.insert(rechargeTask);
	}

	@Override
	public void fail(Task task) {
		log.info("【生单任务执行失败】任务id:{},任务id:{}", task.getOrderId(), task.getTaskId());
		taskService.updateStatus(task.getTaskId(),task.getExecuteStatus(),TaskStatusEnum.FAIL.getCode());
	}
}
