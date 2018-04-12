package com.card.manager.task;


import com.card.service.order.OrderService;
import com.card.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskManagerImpl implements TaskManager {

	@Autowired
	TaskService taskServer;

	@Autowired
	OrderService orderService;

	@Override
	public void updateTaskAndOrder(Long taskId, Integer oldTaskStatus, Integer newTaskStatus, Long orderId, Integer oldOrderStatus, Integer newOrderStatus) {

	}
}
