package com.card.controller;

import com.card.domain.user.User;
import com.card.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangzhanbang
 * @date 2018/4/10 16:26
 * @desc
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * 登录
	 *
	 * @return
	 */
	@PostMapping("/cookie")
	public boolean login() {
		return true;
	}

	/**
	 * 登出
	 *
	 * @return
	 */
	@DeleteMapping("/cookie")
	public boolean logout() {
		return true;
	}


	/**
	 * 注册
	 */
	@PostMapping("")
	public String register(@Validated User user) {
		userService.insert(user);
		return "login";
	}

	/**
	 * 用户信息
	 *
	 * @param userId
	 * @return
	 */
	@GetMapping("/{userId}")
	public String getUser(@PathVariable Long userId) {
		User user = userService.getUser(userId);
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
		userService.updateByUserPin(userId, user);
		return getUser(userId);
	}
}
