package com.card.controller;

import com.card.common.util.IdUtil;
import com.card.common.util.LoginContext;
import com.card.domain.adimage.AdImage;
import com.card.domain.order.Order;
import com.card.domain.order.enums.OrderStatusEnum;
import com.card.domain.pay.FinaStatusEnum;
import com.card.service.adimage.AdImageService;
import com.card.service.order.OrderService;
import com.card.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/6 16:11
 */
@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;


	@GetMapping("")
	public String listOrder(Order order, Map map) {
		/**
		 * 从cookie获取userId
		 */
		List<Order> orderList = orderService.findAllOrderWithParam(order);
		map.put("orderStatusEnums",OrderStatusEnum.values());
		map.put("orderStatusEnum",OrderStatusEnum.class);
		map.put("FinaStatusEnums",FinaStatusEnum.values());
		map.put("FinaStatusEnum",FinaStatusEnum.class);
		map.put("orderList", orderList);
		map.put("order", new Order());

		return "order/list";
	}
}
