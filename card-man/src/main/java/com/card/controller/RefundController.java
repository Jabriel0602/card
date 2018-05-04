package com.card.controller;

import com.card.domain.order.Order;
import com.card.domain.order.enums.OrderStatusEnum;
import com.card.domain.pay.FinaStatusEnum;
import com.card.domain.refund.Refund;
import com.card.domain.refund.enums.RefundStatusEnum;
import com.card.service.order.OrderService;
import com.card.service.refund.RefundService;
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
@Controller
@RequestMapping("/refunds")
public class RefundController {
	@Autowired
	private RefundService refundService;


	@GetMapping("")
	public String listOrder(Refund refund, Map map) {
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
}
