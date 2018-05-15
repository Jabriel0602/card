package com.card.controller;


import com.card.common.util.IdUtil;
import com.card.common.util.LoginContext;
import com.card.common.util.ValidatorUtils;
import com.card.domain.YnEnum;
import com.card.domain.adimage.AdImage;
import com.card.domain.card.Card;
import com.card.domain.card.CardTypeEnum;
import com.card.domain.icon.Icon;
import com.card.domain.result.APIResult;
import com.card.service.adimage.AdImageService;
import com.card.service.card.CardService;
import com.card.service.icon.IconService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/cards")
public class CardController {

	@Autowired
	private CardService cardService;

	@Autowired
	private IdUtil idUtil;

	@Autowired
	private AdImageService adImageService;

	@Autowired
	private IconService iconService;

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
		if(!cardService.isSwitchOn()){
			return new APIResult<>(HttpStatus.FORBIDDEN.value(),"系统升级中，禁止添加公交卡");
		}
		if(cardService.findCardByVendorCardIdAndUserID(card.getVendorCardId(),LoginContext.getUserId())!=null){
			return new APIResult<String>(HttpStatus.EXPECTATION_FAILED.value(),"该卡片已添加");
		}
		card.setCardId(idUtil.getId(IdUtil.SequenceEnum.CARD));
		card.setUserId(LoginContext.getUserId());
		card.setCardType(CardTypeEnum.BEIJING.getDesc());
		card.setYn(YnEnum.Y.getCode());
		card.setCreateTime(new Date());
		card.setModifiedTime(new Date());
		ValidatorUtils.validate(card);
		try{
			int count = cardService.insert(card);
			if(count==1){
				return new APIResult<String>("卡片添加成功");
			}
		}catch (Exception e){
			return new APIResult<String>(HttpStatus.FORBIDDEN.value(),"卡片添加错误，请检查卡片号");
		}
		return new APIResult<String>(HttpStatus.FORBIDDEN.value(),"卡片添加错误，请检查卡片号");
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
		List<AdImage> adImageList = adImageService.findAllAdImageStatusOnWithCache();
		List<Icon> iconList = iconService.findAllIconStatusOnWithCache();
		Card card = cardService.findCardById(cardId);
		map.put("icon", new Icon());
		map.put("iconList", iconList);
		map.put("card", card);
		map.put("adImage", new AdImage());
		map.put("adImageList", adImageList);
		return "addOrder";
	}
}
