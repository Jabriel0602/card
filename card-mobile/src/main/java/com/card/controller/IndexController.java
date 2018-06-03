package com.card.controller;

import com.card.common.util.LoginContext;
import com.card.domain.adimage.AdImage;
import com.card.domain.card.Card;
import com.card.domain.icon.Icon;
import com.card.service.adimage.AdImageService;
import com.card.service.card.CardService;
import com.card.service.icon.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yangzhanbang
 * @date 2018/4/11 15:49
 * @desc
 */
@Controller
@RequestMapping("")
public class IndexController {

	@Autowired
	private AdImageService adImageService;

	@Autowired
	private CardService cardService;

	@Autowired
	private IconService iconService;

	@GetMapping("")
	public String index(Map map, HttpServletRequest request) {
		List<AdImage> adImageList = adImageService.findAllAdImageStatusOnWithCache();
		List<Icon> iconList = iconService.findAllIconStatusOnWithCache();

		Long userId = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("card_user_cookie")) {
					userId = Long.valueOf(cookie.getValue());
					LoginContext.setUserId(userId);
				}
			}
		}
		List<Card> cardList = new ArrayList<>();
		if (userId != null) {
			cardList = cardService.findCard(LoginContext.getUserId());
		}
		map.put("adImage", new AdImage());
		map.put("adImageList", adImageList);
		map.put("icon", new Icon());
		map.put("iconList", iconList);
		map.put("card", new Card());
		map.put("cardList", cardList);

		return "index";
	}
}
