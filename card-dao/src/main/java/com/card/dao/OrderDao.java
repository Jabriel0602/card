package com.card.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.card.domain.order.Order;

@Mapper
public interface OrderDao {
    int insert(@Param("order") Order order);

    int insertSelective(@Param("order") Order order);

    int insertList(@Param("orders") List<Order> orders);

    int update(@Param("order") Order order);

    int updateStatus(@Param("orderId")Long orderId,@Param("oldStatus")Integer oldStatus,@Param("newStatus")Integer newStatus);


    Order selectByOrderId(@Param("orderId") Long orderId);

    List<Order> findAllOrder(@Param("userId") Long userId);

    List<Order> findAllOrderWithParam(@Param("order") Order order);

}
