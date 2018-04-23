package com.card.controller;


import com.card.common.util.LoginContext;
import com.card.common.util.ValidatorUtils;
import com.card.domain.YnEnum;
import com.card.domain.card.Card;
import com.card.domain.card.CardTypeEnum;
import com.card.domain.result.APIResult;
import com.card.service.card.CardService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/cards")
public class CardController {

	@Autowired
	private CardService cardService;

	@Autowired
	private IndexController indexController;

	@GetMapping("")
	public List<Card> listCard(@RequestParam(required = false) Long userId) {
		return cardService.findCard(userId);
	}

	@GetMapping("/{cardId}")
	public Card getCard(@PathVariable Long cardId) {
		return cardService.findCardById(cardId);
	}

	@PostMapping("")
	public String saveCard(Card card) {
		card.setUserId(LoginContext.getUserId());
		card.setCardType(CardTypeEnum.BEIJING.getDesc());
		card.setYn(YnEnum.Y.getCode());
		card.setCreateTime(new Date());
		card.setModifiedTime(new Date());
		ValidatorUtils.validate(card);
		cardService.insert(card);
		return indexController.index(Maps.newHashMap());
	}

	@GetMapping("/{cardId}/editPage")
	public String CardEdit(@PathVariable("cardId") Long cardId, Map map) {
		Card card = cardService.findCardById(cardId);
		map.put("card", card);
		return "cardEdit";
	}

	@PutMapping(value = "/{cardId}")
	@ResponseBody
	public APIResult<Integer> updateCard(Card cardVo) {
		Card card = cardService.findCardById(cardVo.getCardId());
		card.setRemark(cardVo.getRemark());
		card.setModifiedTime(new Date());
		return new APIResult<>(cardService.update(card));
	}

	@DeleteMapping("/{cardId}")
	public int removeCard(@PathVariable Long cardId) {
		return cardService.deleteById(cardId);
	}


}
