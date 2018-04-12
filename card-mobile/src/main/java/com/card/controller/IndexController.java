package com.card.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yangzhanbang
 * @date 2018/4/11 15:49
 * @desc
 */
@Controller
@RequestMapping("/index")
public class IndexController {

	@GetMapping("")
	public String index(){
		return "index";
	}
}
