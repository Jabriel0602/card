package com.card.service.order;

import com.card.dao.OrderDao;
import com.card.domain.order.Order;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService{

    private OrderDao orderDao;

    @Override
    public int insert(Order order){
        return orderDao.insert(order);
    }

    @Override
    public int insertSelective(Order order){
        return orderDao.insertSelective(order);
    }

    @Override
    public int insertList(List<Order> orders){
        return orderDao.insertList(orders);
    }

    @Override
    public int update(Order order){
        return orderDao.update(order);
    }

    @Override
    public Order selectByOrderId(Long orderId) {
        return orderDao.selectByOrderId(orderId);
    }

    @Override
    public List<Order> findAllOrder(Long userId) {
        return orderDao.findAllOrder(userId);
    }
}
