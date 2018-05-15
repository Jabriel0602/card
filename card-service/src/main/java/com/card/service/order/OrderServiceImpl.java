package com.card.service.order;

import com.card.dao.OrderDao;
import com.card.domain.order.Order;
import com.card.domain.switchs.SwitchEnum;
import com.card.domain.switchs.SwitchStatusEnum;
import com.card.service.switchs.SwitchService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private SwitchService switchService;

    @Override
    public boolean isSwitchOn() {
        return switchService.select(SwitchEnum.SUBMIT_ORDER_SWITCH.getCode()).getSwitchStatus().equals(SwitchStatusEnum.SWITCH_ON.getCode());
    }

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
    public int updateStatus(Long orderId, Integer oldStatus, Integer newStatus) {
        return orderDao.updateStatus(orderId,oldStatus,newStatus);
    }

    @Override
    public Order selectByOrderId(Long orderId) {
        return orderDao.selectByOrderId(orderId);
    }

    @Override
    public List<Order> findAllOrder(Long userId) {
        return orderDao.findAllOrder(userId);
    }

    @Override
    public List<Order> findAllOrderWithParam(Order order) {
        return orderDao.findAllOrderWithParam(order);
    }
}
