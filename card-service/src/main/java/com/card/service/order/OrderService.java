package com.card.service.order;

import java.util.List;
import com.card.domain.order.Order;
public interface OrderService{

    int insert(Order order);

    int insertSelective(Order order);

    int insertList(List<Order> orders);

    int update(Order order);

    Order selectByOrderId(Long orderId);
}
