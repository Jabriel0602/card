package com.card.manager.task;

import com.card.common.util.IdUtil;
import com.card.common.util.ValidatorUtils;
import com.card.domain.YnEnum;
import com.card.domain.card.Card;
import com.card.domain.order.Order;
import com.card.domain.order.enums.OrderStatusEnum;
import com.card.domain.pay.FinaStatusEnum;
import com.card.domain.pay.PayStatusEnum;
import com.card.domain.refund.Refund;
import com.card.domain.refund.enums.RefundStatusEnum;
import com.card.domain.refund.enums.RefundTypeEnum;
import com.card.domain.task.Task;
import com.card.domain.task.enums.TaskStatusEnum;
import com.card.domain.task.enums.TaskTypeEnum;
import com.card.service.card.CardService;
import com.card.service.order.OrderService;
import com.card.service.refund.RefundService;
import com.card.service.task.TaskService;
import com.outer.system.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author yangzhanbang
 * @date 2018/4/14 9:36
 * @desc
 */
@Slf4j
@Service
public class TaskManagerImpl implements TaskManager {

	@Autowired
	private OrderService orderService;

	@Autowired
	private RefundService refundService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private IdUtil idUtil;

	@Autowired
	private CardService cardService;

	/**
	 * 直接调用代替(handler)
	 * 更改订单状态为支付成功
	 * 插入生单任务
	 *
	 * @param orderId
	 * @return
	 */
	@Override
	@Transactional
	public Order payment(Long orderId) {
		Order order = orderService.selectByOrderId(orderId);
		log.info("订单:{} 当前状态:{}",order.getOrderId(),OrderStatusEnum.getDescByCode(order.getOrderStatus()));

		/**
		 * 模拟台账系统
		 */
		String payNo = UUID.randomUUID().toString();
		order.setPayNo(payNo);
		order.setMoney(order.getSkuMoney());

		order.setPayStatus(PayStatusEnum.HAVE_PAY.getCode());
		order.setOrderStatus(OrderStatusEnum.PAY_SUCCESS.getCode());
		order.setPayTime(new Date());
		order.setModifyTime(new Date());
		ValidatorUtils.validate(order);
		orderService.update(order);
		log.info("订单:{} 当前状态:{}",order.getOrderId(),OrderStatusEnum.getDescByCode(order.getOrderStatus()));
		Task createTask = TaskTypeEnum.ORDER_CREATE.buildTask(orderId, idUtil.getId(IdUtil.SequenceEnum.TASK));
		ValidatorUtils.validate(createTask);
		taskService.insertSelective(createTask);
		return order;
	}

	/**
	 * handler
	 * 调用供应商生单接口
	 * 更新订单状态
	 * 插入充值任务
	 *
	 * @param orderId
	 */
	@Override
	@Transactional
	public void supplierCreateHandler(Long orderId) {
		/**
		 * 调用供应商生单接口
		 */
		Boolean flag = false;
		try {
			flag = supplierService.createOrder();
		} catch (InterruptedException e) {
			throw new RuntimeException("调用供应商接口失败");
		}
		/**
		 * 供应商返回成功
		 */
		if (flag) {
			Order order = orderService.selectByOrderId(orderId);
			log.info("订单:{} 当前状态:{}",order.getOrderId(),OrderStatusEnum.getDescByCode(order.getOrderStatus()));
			order.setOrderStatus(OrderStatusEnum.CREATE_SUCCESS.getCode());
			order.setModifyTime(new Date());
			ValidatorUtils.validate(order);
			Integer count = orderService.update(order);
			log.info("订单:{} 当前状态:{}",order.getOrderId(),OrderStatusEnum.getDescByCode(order.getOrderStatus()));
			if (count != 1) {
				throw new RuntimeException("更新订单失败");
			}
			Task createOrderTask = TaskTypeEnum.ORDER_RECHARGE.buildTask(order.getOrderId(), idUtil.getId(IdUtil.SequenceEnum.TASK));
			taskService.insert(createOrderTask);
		}
	}

	/**
	 * handler
	 * 调用供应商充值接口
	 *
	 * @param orderId
	 */
	@Override
	@Transactional
	public void supplierRechargeHandler(Long orderId) {
		/**
		 * 调用供应商充值接口
		 */
		Boolean flag = false;
		try {
			flag = supplierService.rechargeOrder();
		} catch (InterruptedException e) {
			throw new RuntimeException("调用供应商接口失败");
		}
		/**
		 * 供应商返回成功
		 */
		if (flag) {
			Order order = orderService.selectByOrderId(orderId);
			Card card = cardService.findCardById(order.getCardId());
			synchronized (this) {
				log.info("订单:{},card:{} 充值前 余额:{}",order.getOrderId(),card.getCardId(),card.getMoney());
				card.setMoney(card.getMoney() + order.getMoney());
				card.setModifiedTime(new Date());
				cardService.update(card);
				log.info("订单:{},card:{} 充值后 余额:{}",order.getOrderId(),card.getCardId(),card.getMoney());
			}
			log.info("订单:{} 当前状态:{}",order.getOrderId(),OrderStatusEnum.getDescByCode(order.getOrderStatus()));
			order.setOrderStatus(OrderStatusEnum.RECHARGE_SUCCESS.getCode());
			order.setModifyTime(new Date());
			order.setFinaStatus(FinaStatusEnum.HAVE_JIESUAN.getCode());
			ValidatorUtils.validate(order);
			Integer count = orderService.update(order);
			log.info("订单:{} 当前状态:{}",order.getOrderId(),OrderStatusEnum.getDescByCode(order.getOrderStatus()));
			if (count != 1) {
				throw new RuntimeException("更新订单失败");
			}
		}
	}

	/**
	 * 更新订单状态
	 * 插入退款任务
	 * 插入退款记录
	 *
	 * @param orderId
	 */
	@Override
	@Transactional
	public void updateTaskAndRefund(Long orderId) {
		Order order = orderService.selectByOrderId(orderId);
		Integer orderStatus = OrderStatusEnum.CREATE_FAIL.getCode();
		if (order.getOrderStatus() > OrderStatusEnum.CREATE_SUCCESS.getCode()) {
			orderStatus = OrderStatusEnum.RECHARGE_FAIL.getCode();
		}
		order.setOrderStatus(orderStatus);
		order.setModifyTime(new Date());
		ValidatorUtils.validate(order);
		Integer count = orderService.update(order);
		if (count != 1) {
			throw new RuntimeException("更新订单失败");
		}
		Task refundTask = TaskTypeEnum.ORDER_REFUND.buildTask(order.getOrderId(), idUtil.getId(IdUtil.SequenceEnum.TASK));
		taskService.insert(refundTask);

		Refund refundOrder = new Refund();
		refundOrder.setRefundId(idUtil.getId(IdUtil.SequenceEnum.REFUND));
		refundOrder.setOrderId(order.getOrderId());
		refundOrder.setCardId(order.getCardId());
		refundOrder.setMoney(order.getMoney());
		refundOrder.setUserId(order.getUserId());
		refundOrder.setCreatedTime(new Date());
		refundOrder.setModifiedTime(new Date());
		refundOrder.setFinishTime(new Date());
		refundOrder.setRefundType(RefundTypeEnum.NORMAL_REFUND.getCode());
		//退款初始化
		refundOrder.setRefundStatus(RefundStatusEnum.REFUND_INT.getCode());
		refundOrder.setYn(YnEnum.Y.getCode());
		ValidatorUtils.validate(refundOrder);
		refundService.insert(refundOrder);
	}

	@Override
	@Transactional
	public void refundHandler(Long orderId) {
		/**
		 * 调用供应商充值接口
		 * 模拟支付系统退款
		 *
		 * 公司支付系统已经 将人民币退款给 微信账号
		 */
		Boolean flag = false;
		try {
			flag = supplierService.refundeOrder();
			log.info("已经通知供应商退款 orderId:{}",orderId);
		} catch (InterruptedException e) {
			throw new RuntimeException("调用供应商接口失败");
		}
		/**
		 * 供应商返回成功
		 * 向供应商申请退款--->退款成功
		 */

		Refund refund = refundService.selectByOrderId(orderId);
		refund.setRefundStatus(RefundStatusEnum.REFUND_SUCCESS.getCode());
		refundService.update(refund);

		Card card=cardService.findCardById(refund.getCardId());
		if(card.getMoney()>=refund.getMoney()){
			synchronized (this){
				log.info("card:{} 退款前 余额:{}",card.getCardId(),card.getMoney());
				card.setMoney(card.getMoney()-refund.getMoney());
				card.setModifiedTime(new Date());
				cardService.update(card);
				log.info("card:{} 退款后 余额:{}",card.getCardId(),card.getMoney());
			}
		}else {
			log.info("余额不足无法退款 refundId:{},card.getMoney():{},refund.getMoney():{}",refund.getRefundId(),card.getMoney(),refund.getMoney());
			throw new RuntimeException("余额不足无法退款");
		}
		Order order=orderService.selectByOrderId(orderId);
		order.setModifyTime(new Date());


		/**
		 * 状态 >= 生单成功 则设置为 充值失败(失败可以使 人工强制失败 和系统错误失败)
		 */
		Integer orderStatus=OrderStatusEnum.CREATE_FAIL_REFUND_SUCCESS.getCode();
		if (order.getOrderStatus() >= OrderStatusEnum.CREATE_SUCCESS.getCode()) {
			orderStatus = OrderStatusEnum.RECHARGE_FAIL_REFUND_SUCCESS.getCode();
		}
		order.setOrderStatus(orderStatus);
		orderService.update(order);
	}
}
