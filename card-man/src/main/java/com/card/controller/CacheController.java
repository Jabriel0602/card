package com.card.controller;

import com.card.common.util.RedisUtil;
import com.card.domain.MethodTypeEnum;
import com.card.domain.constant.CacheKeyEnum;
import com.card.domain.result.APIResult;
import com.card.service.user.UserService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/6 16:15
 */
@Controller
@RequestMapping("/caches")
public class CacheController {

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView init(String pattern) {
		ModelAndView mv = new ModelAndView();
		List<String> keyList = new ArrayList<>(keySets(pattern));
		Boolean limit = userService.getMethodTypeLimitByCurrentUser(MethodTypeEnum.CACHE);
		mv.addObject("cacheAuthorityFlag", limit);
		mv.addObject("cacheKeyEnum", CacheKeyEnum.values());
		mv.addObject("pattern", pattern);
		mv.addObject("keyList", keyList);
		mv.setViewName("cache/cache");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/{key}", method = RequestMethod.GET)
	public APIResult<String> query(@PathVariable String key) {
		if (Strings.isNullOrEmpty(key)) {
			return new APIResult<String>("key is null");
		}
		String string= redisUtil.getJSONString(key.trim());
		return new APIResult<String>(string);
	}

	@ResponseBody
	@RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
	public APIResult<String> remove(@PathVariable String key) {
		if (Strings.isNullOrEmpty(key)) {
			return new APIResult<>(HttpStatus.FORBIDDEN.value(),"key不存在");
		}
		redisUtil.delete(key.trim());
		return new APIResult<String>("删除成功");
	}

	private Set<String> keySets(String pattern) {
		if (Strings.isNullOrEmpty(pattern)) {
			return redisUtil.keys("CARD_*");
		} else {
			return redisUtil.keys(pattern + "*");
		}
	}


}
