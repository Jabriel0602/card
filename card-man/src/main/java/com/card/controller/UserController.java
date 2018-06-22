package com.card.controller;

import com.card.common.util.IdUtil;
import com.card.common.util.LoginContext;
import com.card.common.util.ValidatorUtils;
import com.card.domain.MethodTypeEnum;
import com.card.domain.ModuleTypeEnum;
import com.card.domain.adimage.AdImage;
import com.card.domain.card.Card;
import com.card.domain.result.APIResult;
import com.card.domain.user.User;
import com.card.domain.user.UserTypeEnum;
import com.card.service.adimage.AdImageService;
import com.card.service.card.CardService;
import com.card.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/6 16:14
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IdUtil idUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private AdImageService adImageService;

	@Autowired
	private CardService cardService;

	@Value("${cookie.name}")
	private String cookieName;

	@Value("${cookie.domain}")
	private String cookieDomain;

	/**
	 * 登录
	 *
	 * @return
	 */
	@PostMapping("/cookie")
	public String login(@RequestParam(required = true) String userName, @RequestParam(required = true) String password, HttpServletRequest request, HttpServletResponse response, Map map) {

		User user = userService.getUserByNameAndPassWord(userName, password);
		if (user != null && !user.getUserType().equals(UserTypeEnum.USER.getDesc())) {
			Cookie cookie = new Cookie(cookieName, user.getUserId().toString());
			cookie.setMaxAge(3600 * 24);
			cookie.setDomain(cookieDomain);
			cookie.setPath("/");
			response.addCookie(cookie);

			Map moduleTypeLimitsMap = userService.getModuleTypeLimitsMap(user);
			map.put("moduleTypeLimitsMap", moduleTypeLimitsMap);
			map.put("ModuleTypeEnums", ModuleTypeEnum.values());

			return "index";
		}
		map.put("message", "请输入用户名和密码");
		return "public/login";
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
			cookie.setDomain(cookieDomain);
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		map.put("message", "请输入用户名和密码");
		return "public/login";
	}


	/**
	 * 登录页
	 *
	 * @return
	 */
	@GetMapping("/loginPage")
	public String loginPage(Map map) {

		map.put("message", "请输入用户名和密码");
		return "public/login";
	}



	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView queryUsers() {
		User authority = new User();
		List<User> userList = userService.getUserList();
		ModelAndView mv = new ModelAndView();
		Boolean limit = userService.getMethodTypeLimitByCurrentUser(MethodTypeEnum.AUTHORITY);
		mv.addObject("authorityAuthorityFlag", limit);
		mv.addObject("authority", authority);
		mv.addObject("authorityList", userList);
		mv.setViewName("user/list");
		return mv;
	}


//	@RequestMapping(value = "", method = RequestMethod.POST)
//	public @ResponseBody
//	APIResult<Long> addUserCode(User user) {
//		if (validate(user)) {
//			User oldUser = userService.getUser(user.getUserId());
//			//  判断用户是否存在
//			if (oldUser != null) {
//				return new APIResult<Long>(HttpStatus.EXPECTATION_FAILED.value(), user.getUserId());
//			} else {
//				//格式化参数
//				formatUser(user, oldUser);
//				userService.insert(user);
//				return new APIResult<Long>(user.getUserId());
//			}
//		}
//		return new APIResult<Long>(user.getUserId());
//	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public @ResponseBody
	APIResult<Long> deleteUserCode(@PathVariable Long userId) {
		User user = userService.getUser(userId);
		if (user == null) {
			return new APIResult<Long>(HttpStatus.EXPECTATION_FAILED.value(), userId);
		}
		userService.delete(userId);
		return new APIResult<Long>(userId);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public @ResponseBody
	APIResult<User> updateUser(@PathVariable Long userId, User user) {
		User oldUser = userService.getUser(userId);

		if (oldUser == null) {
			return new APIResult<User>(HttpStatus.EXPECTATION_FAILED.value(), user);
		}
		formatUser(user, oldUser);
		if (validate(user)) {
			userService.update(user);
			// 更新成功
			return new APIResult<User>(user);
		}
		//参数错误
		return new APIResult<User>(user);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ModelAndView updateEdit(@PathVariable Long userId) {
		ModelAndView mv = new ModelAndView();
		User user = null;
		if (userId == null || userId < 0) {
			user = new User();

		} else {
			user = userService.getUser(userId);
		}
		Map moduleTypeLimitsMap = userService.getModuleTypeLimitsMap(user);
		Map methodTypeLimitsMap = userService.getMethodTypeLimitsMap(user);
		mv.addObject("moduleTypeLimitsMap", moduleTypeLimitsMap);
		mv.addObject("ModuleTypeEnums", ModuleTypeEnum.values());
		mv.addObject("methodTypeLimitsMap", methodTypeLimitsMap);
		mv.addObject("MethodTypeEnums", MethodTypeEnum.values());
		mv.addObject("userId", userId);
		mv.addObject("authority", user);
		mv.setViewName("user/edit");
		return mv;
	}

	/**
	 * 格式化 user
	 *
	 * @param user
	 */
	private void formatUser(User user, User oldUser) {
		oldUser.setModifyTime(new Date());
		oldUser.setOperator(LoginContext.getUserName());
		if (oldUser != null) {//更新操作
			oldUser.setModuleTypeLimits(user.getModuleTypeLimits());
			oldUser.setMethodTypeLimits(user.getMethodTypeLimits());
		}
	}

	boolean validate(User user) {
		if (user == null) {
			return false;
		}
		try {
			if (user.getModuleTypeLimits() >= 4) {
				user.setUserType(UserTypeEnum.SUPER_MANAGE.getDesc());
			} else if (user.getModuleTypeLimits() >= 2) {
				user.setUserType(UserTypeEnum.KF_MANAGE.getDesc());
			} else if (user.getModuleTypeLimits() >= 1) {
				user.setUserType(UserTypeEnum.YY_MANAGE.getDesc());
			}
			user.setOperator(LoginContext.getUserName());
			ValidatorUtils.validate(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
