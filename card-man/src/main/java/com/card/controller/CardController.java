package com.card.controller;


import com.card.common.util.LoginContext;
import com.card.common.util.ValidatorUtils;
import com.card.domain.YnEnum;
import com.card.domain.adimage.AdImage;
import com.card.domain.card.Card;
import com.card.domain.card.CardTypeEnum;
import com.card.domain.result.APIResult;
import com.card.service.adimage.AdImageService;
import com.card.service.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/cards")
public class CardController {


	@Autowired
	private CardService cardService;

	@Autowired
	private AdImageService adImageService;

	@GetMapping("")
	public List<Card> listCard(@RequestParam(required = false) Long userId) {
		return cardService.findCard(userId);
	}

	@GetMapping("/{cardId}")
	public Card getCard(@PathVariable Long cardId) {
		return cardService.findCardById(cardId);
	}

	@PostMapping("")
	@ResponseBody
	public APIResult<String> saveCard(Card card, HttpServletRequest request) {
		card.setUserId(LoginContext.getUserId());
		card.setCardType(CardTypeEnum.BEIJING.getDesc());
		card.setYn(YnEnum.Y.getCode());
		card.setCreateTime(new Date());
		card.setModifiedTime(new Date());
		ValidatorUtils.validate(card);
		try{
			int count = cardService.insert(card);
			if(count==1){
				return new APIResult<String>(card.getCardId()+"");
			}
		}catch (Exception e){
			return new APIResult<String>(HttpStatus.FORBIDDEN.value(),card.getCardId()+"");
		}
		return new APIResult<String>(HttpStatus.EXPECTATION_FAILED.value(),card.getCardId()+"");
	}

	@GetMapping("/{cardId}/editPage")
	public String CardEdit(@PathVariable("cardId") Long cardId, Map map) {
		Card card = cardService.findCardById(cardId);
		map.put("card", card);
		return "cardEdit";
	}

	@PutMapping(value = "/{cardId}")
	@ResponseBody
	public APIResult<Integer> updateCard(@PathVariable(value = "cardId") Long cardId, Card cardVo) {
		Card card = cardService.findCardById(cardId);
		card.setRemark(cardVo.getRemark());
		card.setModifiedTime(new Date());
		return new APIResult<>(cardService.update(card));
	}

	@DeleteMapping("/{cardId}")
	public int removeCard(@PathVariable Long cardId) {
		return cardService.deleteById(cardId);
	}


	@GetMapping("/{cardId}/orders/new")
	public String addOrderPage(@PathVariable("cardId") Long cardId, Map map) {
		List<AdImage> adImageList = adImageService.findAllAdImage();
		Card card = cardService.findCardById(cardId);
		map.put("card", card);
		map.put("adImage", new AdImage());
		map.put("adImageList", adImageList);
		return "addOrder";
	}

}
