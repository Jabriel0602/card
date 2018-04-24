package com.card.controller;

import com.card.domain.order.Order;
import com.card.domain.result.APIResult;
import com.card.manager.task.TaskManager;
import com.card.service.order.OrderService;
import com.card.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/6 16:32
 */
@Controller
@RequestMapping("/pay")
public class PayController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private TaskManager taskManager;


	@GetMapping("/submitPage")
	public String getSubmitPage(Long orderId, Map map) {
		map.put("orderId", orderId);
		return "submitPage";
	}

	@GetMapping("/success")
	public String getSuccess(Long orderId, Map map) {
		Order order = orderService.selectByOrderId(orderId);
		map.put("order", order);
		return "paySuccess";
	}

	/**
	 * 用户在支付页面点击支付
	 * 本地 直接支付成功--->跳转到对账
	 * @param orderId
	 * @return
	 */
	@PostMapping("")
	public APIResult<Order> payment(Long orderId) {

		Order order = taskManager.payment(orderId);

		return new APIResult<>(order);
	}
}
