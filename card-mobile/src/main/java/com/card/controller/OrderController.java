package com.card.controller;

import com.card.domain.order.Order;
import com.card.domain.order.enums.OrderStatusEnum;
import com.card.domain.pay.FinaStatusEnum;
import com.card.domain.pay.PayStatusEnum;
import com.card.domain.pay.RechargeStatusEnum;
import com.card.domain.refund.enums.RefundStatusEnum;
import com.card.domain.result.APIResult;
import com.card.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
	@ResponseBody
	public APIResult<List<Order>> listOrder(@RequestParam(required = false) Long userId) {
		/**
		 * 从cookie获取userId
		 */
		return new APIResult<List<Order>>(orderService.findAllOrder(userId));
	}

	@PostMapping("")
	@ResponseBody
	public APIResult<Boolean> submitOrder(Order order) {
		order.setPayStatus(PayStatusEnum.NOT_PAY.getCode());
		order.setRechargeStatus(RechargeStatusEnum.NOT_RECHARGE.getCode());
		order.setFinaStatus(FinaStatusEnum.NOT_JIESUAN.getCode());
		order.setRefundStatus(RefundStatusEnum.REFUND_NULL.getCode());
		order.setOrderStatus(OrderStatusEnum.NOT_PAY.getCode());
		order.setSubmitTime(new Date());
		order.setCreatedTime(new Date());
		order.setModifyTime(new Date());
		if (orderService.insert(order) == 1) {
			return new APIResult<>(true);
		} else {
			return new APIResult<>(false);
		}
	}


}
