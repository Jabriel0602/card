package com.card.service.refund;

import java.util.List;
import com.card.domain.refund.Refund;
public interface RefundService{

    int insert(Refund refund);

    int insertSelective(Refund refund);

    int insertList(List<Refund> refunds);

    int update(Refund refund);

    Refund selectById(Long refundId);

    Refund selectByOrderId(Long orderId);

    List<Refund> selectByParam(Refund refund);
}
