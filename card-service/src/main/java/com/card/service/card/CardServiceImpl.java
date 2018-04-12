package com.card.service.card;

import com.card.dao.CardDao;
import com.card.domain.card.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardDao cardDao;

	@Override
	public int insert(Card card) {
		return cardDao.insert(card);
	}

	@Override
	public int insertSelective(Card card) {
		return cardDao.insertSelective(card);
	}

	@Override
	public int insertList(List<Card> cards) {
		return cardDao.insertList(cards);
	}

	@Override
	public int update(Card card) {
		return cardDao.update(card);
	}

	@Override
	public int deleteById(Long id) {
		return cardDao.deleteById(id);
	}

	@Override
	public Card findCardById(Long id) {
		return cardDao.findCardById(id);
	}

	@Override
	public List<Card> findCard() {
		return cardDao.findCard();
	}
}
