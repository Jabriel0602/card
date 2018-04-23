package com.card.controller;

import com.card.common.util.LoginContext;
import com.card.domain.adimage.AdImage;
import com.card.domain.order.Order;
import com.card.domain.order.enums.OrderStatusEnum;
import com.card.domain.pay.FinaStatusEnum;
import com.card.domain.pay.PayStatusEnum;
import com.card.domain.pay.RechargeStatusEnum;
import com.card.domain.refund.enums.RefundStatusEnum;
import com.card.domain.result.APIResult;
import com.card.service.adimage.AdImageService;
import com.card.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

	@Autowired
	private AdImageService adImageService;

	@GetMapping("")
	public String listOrder(Map map) {
		/**
		 * 从cookie获取userId
		 */
		List<Order> orderList = orderService.findAllOrder(LoginContext.getUserId());
		List<AdImage> adImageList = adImageService.findAllAdImage();
		map.put("adImage", new AdImage());
		map.put("adImageList", adImageList);
		map.put("orderList", orderList);
		return "order";
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
