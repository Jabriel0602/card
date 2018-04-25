package com.card.service.order;

import java.util.List;
import com.card.domain.order.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderService{

    int insert(Order order);

    int insertSelective(Order order);

    int insertList(List<Order> orders);

    int update(Order order);

    int updateStatus(Long orderId,Integer oldStatus,Integer newStatus);

    Order selectByOrderId(Long orderId);

    List<Order> findAllOrder(Long userId);

}
