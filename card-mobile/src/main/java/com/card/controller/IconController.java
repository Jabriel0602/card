package com.card.controller;

import com.card.domain.icon.Icon;
import com.card.service.icon.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/6 16:09
 */
@Controller
@RequestMapping("/icons")
public class IconController {

	@Autowired
	private IconService iconService;

	/**
	 * 未使用 在index出使用
	 * @return
	 */
	@GetMapping("")
	@ResponseBody
	public List<Icon> listIcon() {
		return iconService.findAllIconStatusOn();
	}
}
