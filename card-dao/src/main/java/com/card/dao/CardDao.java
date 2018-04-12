package com.card.dao;

import com.card.domain.card.Card;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface CardDao {
	int insert(@Param("card") Card card);

	int insertSelective(@Param("card") Card card);

	int insertList(@Param("cards") List<Card> cards);

	int update(@Param("card") Card card);

	int deleteById(@Param("id")Long id);

	Card findCardById(@Param("id") Long id);

	List<Card> findCard();
}
