package com.card.controller;

import com.card.common.util.Base64Util;
import com.card.common.util.IdUtil;
import com.card.common.util.LoginContext;
import com.card.common.util.ValidatorUtils;
import com.card.domain.adimage.AdImage;
import com.card.domain.card.Card;
import com.card.domain.result.APIResult;
import com.card.domain.user.User;
import com.card.domain.user.UserTypeEnum;
import com.card.service.adimage.AdImageService;
import com.card.service.card.CardService;
import com.card.service.user.UserService;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yangzhanbang
 * @date 2018/4/10 16:26
 * @desc
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	IdUtil idUtil;

	@Autowired
	UserService userService;

	@Autowired
	AdImageService adImageService;

	@Autowired
	CardService cardService;

	/**
	 * 登录
	 *
	 * @return
	 */
	@PostMapping("/cookie")
	public String login(@RequestParam(required = true) String userName, @RequestParam(required = true) String password, HttpServletRequest request, HttpServletResponse response, Map map) {

		User user = userService.getUserByNameAndPassWord(userName, password);
		if (user != null) {
			Cookie cookie = new Cookie("card_user_cookie", user.getUserId().toString());
			cookie.setMaxAge(3600 * 24 * 7);
			cookie.setDomain("");
			cookie.setPath("/");
			response.addCookie(cookie);
			List<AdImage> adImageList = adImageService.findAllAdImage();
			List<Card> cardList = cardService.findCard(user.getUserId());
			map.put("adImage", new AdImage());
			map.put("adImageList", adImageList);
			map.put("card", new Card());
			map.put("cardList", cardList);
			return "index";
		}
		map.put("message", "用户名或密码错误");
		return "login";
	}

	/**
	 * 登出
	 *
	 * @return
	 */
	@PostMapping("/cookieClear")
	public String logout(HttpServletRequest request, HttpServletResponse response, Map map) {
		User user = userService.getUser(LoginContext.getUserId());
		if (user != null) {
			Cookie cookie = new Cookie("card_user_cookie", user.getUserId().toString());
			cookie.setDomain("");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		map.put("message", "请输入用户名和密码");
		return "login";
	}


	/**
	 * 注册页
	 */
	@GetMapping("/registerPage")
	public String registerPage() {

		return "register";
	}


	/**
	 * 是用户名是否存在
	 */
	@GetMapping("/userName")
	@ResponseBody
	public APIResult<Boolean> userNameNotUsed(String userName,Map map) {
		User user = userService.getUserByName(userName);
		return new APIResult<>(user==null);
	}


	/**
	 * 注册
	 */
	@PostMapping("/register")
	public String register(User user, Map map) {
		user.setUserId(idUtil.getId(IdUtil.SequenceEnum.USER));
		user.setCreateTime(new Date());
		user.setModifyTime(new Date());
		user.setUserType(UserTypeEnum.USER.getDesc());
		validate(user);
		user.setPassword(Base64Util.base64ForCharset(user.getPassword(), Charsets.UTF_8.name()));
		userService.insertSelective(user);
		map.put("message", "请妥善保管您的密码");
		return "login";
	}

	/**
	 * 用户信息
	 */
	@GetMapping("")
	public String getUser(Map map) {
		Long userId = LoginContext.getUserId();
		User user = userService.getUser(userId);
		map.put("user", user);
		return "personal";
	}

	/**
	 * 修改用户信息
	 *
	 * @param userId
	 * @return
	 */
	@PostMapping("/{userId}")
	public String updateUser(@PathVariable Long userId, @Validated User user) {
		validate(user);
		userService.update(user);
		return getUser(Maps.newHashMap());
	}

	/**
	 * 修改用户信息
	 *
	 * @param userId
	 * @return
	 */
	@PutMapping("/{userId}/userName")
	@ResponseBody
	public APIResult<Boolean> updateUserName(@PathVariable Long userId, String userName) {
		User user = userService.getUser(userId);
		user.setUserName(userName);
		validate(user);
		int count = userService.update(user);
		return new APIResult<>(count == 1);
	}

	/**
	 * 修改用户信息
	 *
	 * @param userId
	 * @return
	 */
	@PutMapping("/{userId}/birthday")
	@ResponseBody
	public APIResult<Boolean> updateUserBirth(@PathVariable Long userId, String birthday) {
		User user = userService.getUser(userId);
		user.setBirthday(birthday);
		validate(user);
		int count = userService.update(user);
		return new APIResult<>(count == 1);
	}

	/**
	 * 修改用户信息
	 *
	 * @param userId
	 * @return
	 */
	@PutMapping("/{userId}/sex")
	@ResponseBody
	public APIResult<Boolean> updateUserSex(@PathVariable Long userId, String sex) {
		User user = userService.getUser(userId);
		user.setSex(sex);
		validate(user);
		int count = userService.update(user);
		return new APIResult<>(count == 1);
	}

	boolean validate(User user) {
		if (user == null) {
			return false;
		}
		try {
			if (user.getModuleTypeLimits() >= 4) {
				user.setUserType(UserTypeEnum.SUPER_MANAGE.getDesc());
			} else if (user.getModuleTypeLimits() >= 3) {
				user.setUserType(UserTypeEnum.MANAGE.getDesc());
			} else if (user.getModuleTypeLimits() >= 1) {
				user.setUserType(UserTypeEnum.USER.getDesc());
			}
			ValidatorUtils.validate(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
