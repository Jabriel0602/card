package com.card.controller;

import com.card.domain.adimage.AdImage;
import com.card.domain.card.Card;
import com.card.service.adimage.AdImageService;
import com.card.service.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author yangzhanbang
 * @date 2018/4/11 15:49
 * @desc
 */
@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private AdImageService adImageService;

	@Autowired
	private CardService cardService;

	@GetMapping("")
	public String index(Long userId, Map map) {
		List<AdImage> adImageList = adImageService.findAllAdImage();
		List<Card> cardList = cardService.findCard(userId);

		map.put("adImage", new AdImage());
		map.put("adImageList", adImageList);
		map.put("card", new Card());
		map.put("cardList", cardList);

		return "index";
	}
}
