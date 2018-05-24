package com.card.controller;

import com.card.common.util.IdUtil;
import com.card.common.util.LoginContext;
import com.card.common.util.ValidatorUtils;
import com.card.domain.MethodTypeEnum;
import com.card.domain.icon.Icon;
import com.card.domain.result.APIResult;
import com.card.service.icon.IconService;
import com.card.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/6 16:09
 */
@Slf4j
@Controller
@RequestMapping("/icons")
public class IconController {
	@Autowired
	private UserService userService;

	@Autowired
	private IconService iconService;

	@Autowired
	private IdUtil idUtil;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView queryIcon() {

		Icon icon = new Icon();
		List<Icon> iconList = iconService.findAllIconWithStatus();
		Boolean limit = userService.getMethodTypeLimitByCurrentUser(MethodTypeEnum.ADIMAGE);

		ModelAndView mv = new ModelAndView();
		mv.addObject("icon", icon);
		mv.addObject("iconList", iconList);
		mv.addObject("iconAuthorityFlag", limit);
		mv.setViewName("icon/list");
		return mv;
	}


	/**
	 * 创建或者修改轮播图页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/{iconId}", method = RequestMethod.GET)
	public ModelAndView updateEdit(@PathVariable Long iconId) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("iconId", iconId);
		mv.setViewName("icon/edit");
		//创建页面
		if (iconId == null || iconId < 0) {
			return mv;
		}
		//修改页面
		Icon icon = iconService.findIconById(iconId);
		mv.addObject("iconId", icon.getId());

		mv.addObject("icon", icon);

		return mv;
	}

	/**
	 * 创建轮播图操作
	 *
	 * @param icon
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public APIResult<Icon> addImage(Icon icon) {
		//参数校验
		try {
			icon.setId(idUtil.getId(IdUtil.SequenceEnum.ICON));
			icon.setOperator(LoginContext.getUserName());
			icon.setCreatedTime(new Date());
			icon.setModifiedTime(new Date());
			ValidatorUtils.validate(icon);
			iconService.insert(icon);
			return new APIResult<Icon>(icon);
		} catch (Exception e) {
			log.error("参数错误:{}",e);
			return new APIResult<Icon>(HttpStatus.EXPECTATION_FAILED.value(), icon);
		}
	}

	/**
	 * 修改轮播图操作
	 *
	 * @return
	 */
	@RequestMapping(value = "{iconId}", method = RequestMethod.PUT)
	@ResponseBody
	public APIResult<Icon> updateImage(@PathVariable Long iconId, Icon icon) {

		//获取icon
		Icon oldAdimage = iconService.findIconById(iconId);
		if (oldAdimage == null) {
			return new APIResult<Icon>(HttpStatus.EXPECTATION_FAILED.value(), oldAdimage);
		}
		//参数校验
		try {
			icon.setModifiedTime(new Date());
			icon.setOperator(LoginContext.getUserName());
			ValidatorUtils.validate(icon);
			iconService.update(icon);
			return new APIResult<>(icon);
		} catch (Exception e) {
			log.error("修改ICON异常"+e);
			return new APIResult<Icon>(HttpStatus.EXPECTATION_FAILED.value(), icon);
		}
	}

	/**
	 * 局部修改 权重
	 *
	 * @return
	 */
	@RequestMapping(value = "{iconId}/weight", method = RequestMethod.PATCH)
	@ResponseBody
	public APIResult<Icon> updateIconWeight(@PathVariable Long iconId,Integer weight) {

		//获取icon
		Icon oldAdimage = iconService.findIconById(iconId);
		if (oldAdimage == null) {
			return new APIResult<Icon>(HttpStatus.EXPECTATION_FAILED.value(), oldAdimage);
		}
		Icon icon = oldAdimage;
		icon.setWeight(weight);
		icon.setModifiedTime(new Date());

		//参数校验
		try {
			ValidatorUtils.validate(icon);
			iconService.update(icon);
			return new APIResult<Icon>(icon);
		} catch (Exception e) {
			log.error("参数校验异常:{}",e);
			return new APIResult<Icon>(HttpStatus.EXPECTATION_FAILED.value(), icon);
		}

	}

	/**
	 * 局部修改  上下架开关
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "{iconId}/putOn", method = RequestMethod.PATCH)
	public APIResult<Icon> updateIconPutOn(@PathVariable Long iconId, Integer moduleCode, Boolean putOn) {
		//获取icon
		Icon oldAdimage = iconService.findIconById(iconId);
		if (oldAdimage == null) {
			return new APIResult<Icon>(HttpStatus.EXPECTATION_FAILED.value(), oldAdimage);
		}
		Icon icon = oldAdimage;
		icon.setPutOn(putOn);
		icon.setModifiedTime(new Date());

		try {
			ValidatorUtils.validate(icon);
			iconService.update(icon);
			return new APIResult<Icon>(icon);
		} catch (Exception e) {
			return new APIResult<Icon>(HttpStatus.EXPECTATION_FAILED.value(), icon);
		}

	}

	/**
	 * 删除广告图
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{iconId}", method = RequestMethod.DELETE)
	public APIResult<Icon> delIcon(@PathVariable Long iconId) {
		Icon icon = iconService.findIconById(iconId);
		if (icon == null) {
			return new APIResult<Icon>(HttpStatus.EXPECTATION_FAILED.value(), icon);
		}
		iconService.delete(iconId);
		return new APIResult<Icon>(icon);
	}
}
