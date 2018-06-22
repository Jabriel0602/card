package com.card.manager.order;

import com.card.common.util.IdUtil;
import com.card.common.util.NumberUtil;
import com.card.domain.order.Order;
import com.card.domain.order.enums.OrderStatusEnum;
import com.card.domain.refund.Refund;
import com.card.domain.task.Task;
import com.card.domain.task.enums.TaskTypeEnum;
import com.card.manager.task.TaskManager;
import com.card.service.order.OrderService;
import com.card.service.refund.RefundService;
import com.card.service.task.TaskService;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yangzhanbang
 * @date 2018/5/5 17:24
 * @desc
 */
@Slf4j
@Service
public class OrderManagerImpl implements OrderManager {
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private RefundService refundService;

	@Autowired
	private IdUtil idUtil;

	@Autowired
	private TaskManager taskManager;

	@Override
	@Transactional
	public Map refund(String orderIds) {
		Map<String, String> map = Maps.newHashMap();
		// 1 过滤相同的 订单
		Set<Long> orderIdSet = filterData(orderIds);
		for (Long orderId : orderIdSet) {
			// 2.构造参数
			Order order = orderService.selectByOrderId(orderId);
			if (order == null) {
				log.info("售后退款,查无此订单,orderID:{}", orderId);
				map.put(orderId + "", "订单表无此订单号");
				continue;
			}
			/**
			 * todo 支付成功 未充值成功才可以退款
			 * 充值成功才可以申请退款
			 */
			if (!order.getOrderStatus().equals(OrderStatusEnum.RECHARGE_SUCCESS.getCode())) {
				log.info("售后退款，订单状态不正确,orderId:{},orderStatus:{}", orderId, order.getOrderStatus());
				map.put(orderId + "", "订单状态不正确");
				continue;
			}
			// 3 查找任务表，看是否任务已存在
			List<Task> list = taskService.selectTaskByOrderId(orderId,TaskTypeEnum.ORDER_REFUND.getType());
			if (null != list && list.size() > 0) {
				log.info("售后退款，任务已经存在，orderId is:{}", orderId);
				map.put(orderId + "", "已插入任务，请勿重复提交");
				continue;
			}
			// 4 查找是否退款表中含有
			Refund refundExist = refundService.selectByOrderId(orderId);
			if (refundExist != null) {
				log.info("售后退款，退款表不存在此订单，orderId is:{}", orderId);
				map.put(orderId + "", "已推到退款网关");
				continue;
			}
			map.put(orderId + "", "已插入退款任务");
			taskManager.updateTaskAndRefund(orderId);
		}
		return map;
	}

	private Set<Long> filterData(String erpOrderIds) {
		Set<Long> erpOrderIdSet = Sets.newHashSet();
		String[] erpOrderIdsArr = erpOrderIds.split(",");
		long orderId;
		for (String str : erpOrderIdsArr) {
			if (NumberUtil.isNumeric(str)) {
				orderId = Long.valueOf(str);
				erpOrderIdSet.add(orderId);
			}
		}
		return erpOrderIdSet;
	}
}
