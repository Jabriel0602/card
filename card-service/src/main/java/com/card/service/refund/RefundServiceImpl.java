package com.card.service.refund;

import com.card.dao.RefundDao;
import com.card.domain.refund.Refund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class RefundServiceImpl implements RefundService{

    @Autowired
    private RefundDao refundDao;

    @Override
    public int insert(Refund refund){
        return refundDao.insert(refund);
    }

    @Override
    public int insertSelective(Refund refund){
        return refundDao.insertSelective(refund);
    }

    @Override
    public int insertList(List<Refund> refunds){
        return refundDao.insertList(refunds);
    }

    @Override
    public int update(Refund refund){
        return refundDao.update(refund);
    }

    @Override
    public Refund selectById(Long refundId) {
        return refundDao.selectById(refundId);
    }

    @Override
    public Refund selectByOrderId(Long orderId) {
        return refundDao.selectByOrderId(orderId);
    }

    @Override
    public List<Refund> selectByParam(Refund refund) {
        return refundDao.selectByParam(refund);
    }
}
