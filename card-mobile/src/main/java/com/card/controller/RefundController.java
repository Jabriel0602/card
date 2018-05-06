package com.card.controller;

import com.card.domain.refund.Refund;
import com.card.domain.refund.enums.RefundStatusEnum;
import com.card.domain.result.APIResult;
import com.card.manager.order.OrderManager;
import com.card.service.refund.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/6 16:11
 */
@RestController
@RequestMapping("/refunds")
public class RefundController {
	@Autowired
	private OrderManager orderManager;


	@PostMapping("")
	public APIResult<Map> addRefund(Integer orderId) {
		Map map=orderManager.refund(String.valueOf(orderId));
		return new APIResult<>(map);
	}
}
