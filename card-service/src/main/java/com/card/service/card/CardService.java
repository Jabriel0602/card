package com.card.service.card;

import java.util.List;

import com.card.domain.card.Card;

public interface CardService {

	int insert(Card card);

	int insertSelective(Card card);

	int insertList(List<Card> cards);

	int update(Card card);

	int deleteById(Long id);

	Card findCardById(Long id);

	Card findCardByVendorCardIdAndUserID(Long vendorCardId,Long userId);

	List<Card> findCard(Long userId);

}
