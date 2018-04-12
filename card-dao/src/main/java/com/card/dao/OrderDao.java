package com.card.dao;

import com.card.domain.order.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {
    int insert(@Param("order") Order order);

    int insertSelective(@Param("order") Order order);

    int insertList(@Param("orders") List<Order> orders);

    int update(@Param("order") Order order);

    Order selectByOrderId(@Param("orderId") Long orderId);
}
