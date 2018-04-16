package com.card.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.card.domain.card.Card;

@Mapper
public interface CardDao {
    int insert(@Param("card") Card card);

    int insertSelective(@Param("card") Card card);

    int insertList(@Param("cards") List<Card> cards);

    int update(@Param("card") Card card);

    int deleteById(@Param("id")Long id);

    Card findCardById(@Param("id") Long id);

    List<Card> findCard(@Param("userId")Long userId);
}
