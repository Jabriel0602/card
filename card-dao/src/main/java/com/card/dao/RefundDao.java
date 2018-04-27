package com.card.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.card.domain.refund.Refund;
import org.springframework.stereotype.Repository;

@Mapper
public interface RefundDao {
    int insert(@Param("refund") Refund refund);

    int insertSelective(@Param("refund") Refund refund);

    int insertList(@Param("refunds") List<Refund> refunds);

    int update(@Param("refund") Refund refund);

    Refund selectById(@Param("refundId") Long refundId);

    Refund selectByOrderId(@Param("orderId") Long orderID);

    List<Refund> selectByParam(@Param("refund") Refund refund);
}
