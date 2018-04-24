package com.card.controller;

import com.card.common.util.DateUtil;
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
import org.springframework.web.bind.annotation.ResponseBody;

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

	/**
	 * 支付页面
	 */

	@GetMapping("/submitPage")
	public String getSubmitPage(Long orderId, Map map) {
		Order order=orderService.selectByOrderId(orderId);
		map.put("order", order);
		return "submitPage";
	}

	/**
	 * 支付成功页
	 * @param orderId
	 * @param map
	 * @return
	 */

	@GetMapping("/success")
	public String getSuccess(Long orderId, Map map) {
		Order order = orderService.selectByOrderId(orderId);
		map.put("order", order);
		map.put("payTime",DateUtil.formatDateYMDHMS(order.getPayTime()));
		return "paySuccess";
	}

	/**
	 *
	 * 点击支付 本地 直接支付成功--->跳转到对账
	 * @param orderId
	 * @return
	 */
	@PostMapping("")
	@ResponseBody
	public APIResult<Order> payment(Long orderId) {

		Order order = taskManager.payment(orderId);

		return new APIResult<>(order);
	}
}
