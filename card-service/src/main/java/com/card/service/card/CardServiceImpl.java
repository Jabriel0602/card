package com.card.service.card;

import com.card.dao.CardDao;
import com.card.domain.card.Card;
import com.card.domain.switchs.SwitchEnum;
import com.card.domain.switchs.SwitchStatusEnum;
import com.card.service.switchs.SwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardDao cardDao;

	@Autowired
	private SwitchService switchService;

	@Override
	public boolean isSwitchOn() {
		return switchService.select(SwitchEnum.BIND_CARD_SWITCH.getCode()).getSwitchStatus().equals(SwitchStatusEnum.SWITCH_ON.getCode());
	}

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
	public Card findCardByVendorCardIdAndUserID(Long vendorCardId,Long userId) {
		return cardDao.findCardByVendorCardIdAndUserID(vendorCardId,userId);
	}

	@Override
	public List<Card> findCard(Long userId) {
		return cardDao.findCard(userId);
	}
}
