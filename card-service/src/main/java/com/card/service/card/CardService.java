package com.card.service.card;

import java.util.List;
import com.card.domain.card.Card;
import org.apache.ibatis.annotations.Param;

public interface CardService{

    int insert(Card card);

    int insertSelective(Card card);

    int insertList(List<Card> cards);

    int update(Card card);

    int deleteById(@Param("id")Long id);

    Card findCardById(@Param("id") Long id);

    List<Card> findCard();

}
