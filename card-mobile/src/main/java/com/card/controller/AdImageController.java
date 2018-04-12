package com.card.controller;

import com.card.domain.adimage.AdImage;
import com.card.service.adimage.AdImageService;
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
@RequestMapping("/adImages")
public class AdImageController {

	@Autowired
	private AdImageService adImageService;

	@GetMapping("")
	@ResponseBody
	public List<AdImage> listAdImage() {
		return adImageService.findAllAdImage();
	}
}
