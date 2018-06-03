package com.card.controller;

import com.card.domain.order.Order;
import com.card.domain.order.enums.OrderStatusEnum;
import com.card.domain.pay.FinaStatusEnum;
import com.card.domain.refund.Refund;
import com.card.domain.refund.enums.RefundStatusEnum;
import com.card.domain.result.APIResult;
import com.card.manager.order.OrderManager;
import com.card.service.order.OrderService;
import com.card.service.refund.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/6 16:11
 */
@Controller
@RequestMapping("/refunds")
public class RefundController {
	@Autowired
	private RefundService refundService;

	@Autowired
	private OrderManager orderManager;

	@GetMapping("")
	public String listRefund(Refund refund, Map map) {
		/**
		 * 从cookie获取userId
		 */
		List<Refund> refundList = refundService.selectByParam(refund);
		map.put("RefundStatusEnums", RefundStatusEnum.values());
		map.put("RefundStatusEnum", RefundStatusEnum.class);
		map.put("refundList", refundList);
		map.put("refund", new Refund());

		return "refund/list";
	}

	@GetMapping("/page")
	public String getRefundPage() {
		return "refund/refund";
	}


	@PostMapping("")
	public APIResult<Map> addRefund(String orderIds) {
		Map map=orderManager.refund(orderIds);
		return new APIResult<>(map);
	}
}
