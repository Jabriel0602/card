package com.card.controller;

import com.card.common.util.IdUtil;
import com.card.common.util.LoginContext;
import com.card.domain.YnEnum;
import com.card.domain.adimage.AdImage;
import com.card.domain.card.CardTypeEnum;
import com.card.domain.icon.Icon;
import com.card.domain.order.Order;
import com.card.domain.order.enums.OrderStatusEnum;
import com.card.domain.pay.FinaStatusEnum;
import com.card.domain.pay.PayStatusEnum;
import com.card.domain.pay.RechargeStatusEnum;
import com.card.domain.refund.enums.RefundStatusEnum;
import com.card.domain.result.APIResult;
import com.card.domain.user.User;
import com.card.service.adimage.AdImageService;
import com.card.service.icon.IconService;
import com.card.service.order.OrderService;
import com.card.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private AdImageService adImageService;

	@Autowired
	private IdUtil idUtil;

	@Autowired
	private IconService iconService;

	@GetMapping("")
	public String listOrder(Map map) {
		/**
		 * 从cookie获取userId
		 */
		List<Order> orderList = orderService.findAllOrder(LoginContext.getUserId());
		List<AdImage> adImageList = adImageService.findAllAdImage();
		List<Icon> iconList = iconService.findAllIconStatusOn();
		map.put("orderStatusEnum",OrderStatusEnum.class);
		map.put("adImage", new AdImage());
		map.put("adImageList", adImageList);
		map.put("orderList", orderList);
		map.put("icon", new Icon());
		map.put("iconList", iconList);
		return "order";
	}

	@PostMapping("")
	@ResponseBody
	public APIResult<Long> submitOrder(Order order) {

		User user = userService.getUser(LoginContext.getUserId());
		order.setOrderId(idUtil.getId(IdUtil.SequenceEnum.ORDER));
		order.setUserId(LoginContext.getUserId());

		order.setPhone(user.getPhone());
		order.setCardType(CardTypeEnum.BEIJING.getDesc());

		order.setPayStatus(PayStatusEnum.NOT_PAY.getCode());
		order.setRechargeStatus(RechargeStatusEnum.NOT_RECHARGE.getCode());
		order.setFinaStatus(FinaStatusEnum.NOT_JIESUAN.getCode());
		order.setRefundStatus(RefundStatusEnum.REFUND_NULL.getCode());
		order.setOrderStatus(OrderStatusEnum.NOT_PAY.getCode());

		order.setSubmitTime(new Date());
		order.setCreatedTime(new Date());
		order.setModifyTime(new Date());
		order.setYn(YnEnum.Y.getCode());
		if (orderService.insertSelective(order) == 1) {
			return new APIResult<>(order.getOrderId());
		} else {
			return new APIResult<>(order.getOrderId());
		}
	}


}
