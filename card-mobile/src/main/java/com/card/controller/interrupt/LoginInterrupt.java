package com.card.controller.interrupt;

import com.alibaba.fastjson.JSON;
import com.card.common.util.LoginContext;
import com.card.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangzhanbang
 * @date 2018/4/16 17:54
 * @desc
 */
@Slf4j
public class LoginInterrupt extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Long userId = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("card_user_cookie")) {
					userId = Long.valueOf(cookie.getValue());
					LoginContext.setUserId(userId);
					LoginContext.setUserName(userService.getUser(userId).getUserName());
					return true;
				}
			}
		}
		redirect2LoginUrl(request, response);
		return false;
	}

	private void redirect2LoginUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xhq = request.getHeader("X-Requested-With");
		String accept = request.getHeader("Accept");
		if (accept.contains("json") || "XMLHttpRequest".equals(xhq)) {
			Map<String, String> map = new HashMap();
			map.put("code", "notLogin");//未登录
			map.put("loginURL", "http://127.0.0.1/html/login.html");
			ajaxResponse(response, JSON.toJSONString(map));
			return;
		}
		response.sendRedirect("http://127.0.0.1/html/login.html");
	}

	private void ajaxResponse(HttpServletResponse response, String jsonMsg) {
		PrintWriter writer = null;
		try {
			response.setContentType("application/json");
			writer = response.getWriter();
			writer.write(jsonMsg);
			writer.flush();
		} catch (Exception e) {
			log.error("--ajaxResponse error--", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
					log.error("--ajaxResponse close writer error--", e);
				}
			}
		}
	}
}
