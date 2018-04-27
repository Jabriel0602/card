package com.card.controller;

import com.card.common.util.LoginContext;
import com.card.domain.ModuleTypeEnum;
import com.card.domain.user.User;
import com.card.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class IndexController {

	@Value("${cookie.name}")
	private String cookieServer;
	@Value("${cookie.domain}")
	private String domain;

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public ModelAndView index(Model model) {
		ModelAndView mv = new ModelAndView();
		User userLevel = null;
		userLevel = userService.getCurrentUser();
		//通过用户erp帐号去查询用户权限
		if (userLevel != null) {
			//获取用户权限标记
			Map moduleTypeLimitsMap = userService.getModuleTypeLimitsMap(userLevel);
			mv.addObject("moduleTypeLimitsMap", moduleTypeLimitsMap);
			mv.addObject("ModuleTypeEnums", ModuleTypeEnum.values());
		}
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping(value = "topFrame", method = RequestMethod.GET)
	public ModelAndView topFrame(Model model) {
		ModelAndView mv = new ModelAndView();

		User userLevel = null;
		userLevel = userService.getUser(LoginContext.getUserId());
		//通过用户erp帐号去查询用户权限
		if (userLevel != null) {
			//获取用户权限标记
			Map moduleTypeLimitsMap = userService.getModuleTypeLimitsMap(userLevel);
			mv.addObject("moduleTypeLimitsMap", moduleTypeLimitsMap);
			mv.addObject("ModuleTypeEnums", ModuleTypeEnum.values());
		}
		mv.setViewName("public/admin/topFrameAdmin");
		return mv;
	}

	@RequestMapping("leftFrame")
	public String leftFrame(Model model) {
		model.addAttribute("visitAuth", true);
		return "public/admin/leftFrameAdmin";
	}

	/**
	 * 退出登录
	 *
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.clearCookies(response);
		return new ModelAndView(new RedirectView("/"));
	}


	private void clearCookies(HttpServletResponse response) {
		//erp cookie
		Cookie cookie = new Cookie(cookieServer, null);
		cookie.setMaxAge(0);
		cookie.setDomain(domain);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
