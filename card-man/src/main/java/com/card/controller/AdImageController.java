package com.card.controller;

import com.card.common.util.IdUtil;
import com.card.common.util.LoginContext;
import com.card.common.util.ValidatorUtils;
import com.card.domain.MethodTypeEnum;
import com.card.domain.adimage.AdImage;
import com.card.domain.result.APIResult;
import com.card.service.adimage.AdImageService;
import com.card.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/adImages")
public class AdImageController {
	@Autowired
	private UserService userService;

	@Autowired
	private AdImageService adImageService;

	@Autowired
	private IdUtil idUtil;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView queryAdImageByModuleType() {

		AdImage adImage = new AdImage();
		List<AdImage> adImageList = adImageService.findAllAdImageWithStatus();
		Boolean limit = userService.getMethodTypeLimitByCurrentUser(MethodTypeEnum.ADIMAGE);

		ModelAndView mv = new ModelAndView();
		mv.addObject("adImage", adImage);
		mv.addObject("adImageList", adImageList);
		mv.addObject("adimageAuthorityFlag", limit);
		mv.setViewName("adimage/list");
		return mv;
	}


	/**
	 * 创建或者修改轮播图页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/{adImageId}", method = RequestMethod.GET)
	public ModelAndView updateEdit(@PathVariable Long adImageId) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("adImageId", adImageId);
		mv.setViewName("adimage/edit");
		//创建页面
		if (adImageId == null || adImageId < 0) {
			return mv;
		}
		//修改页面
		AdImage adImage = adImageService.findAdImageById(adImageId);
		mv.addObject("adImageId", adImage.getId());

		mv.addObject("adImage", adImage);

		return mv;
	}

	/**
	 * 创建轮播图操作
	 *
	 * @param adImage
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public APIResult<AdImage> addImage(AdImage adImage) {
		//参数校验
		try {
			adImage.setId(idUtil.getId(IdUtil.SequenceEnum.ADIMAGE));
			adImage.setOperator(LoginContext.getUserName());
			adImage.setCreatedTime(new Date());
			adImage.setModifiedTime(new Date());
			ValidatorUtils.validate(adImage);
			adImageService.insert(adImage);
			return new APIResult<AdImage>(adImage);
		} catch (Exception e) {
			log.error("参数错误:{}",e);
			return new APIResult<AdImage>(HttpStatus.EXPECTATION_FAILED.value(), adImage);
		}
	}

	/**
	 * 修改轮播图操作
	 *
	 * @return
	 */
	@RequestMapping(value = "{adImageId}", method = RequestMethod.PUT)
	@ResponseBody
	public APIResult<AdImage> updateImage(@PathVariable Long adImageId, AdImage adImage) {

		//获取adImage
		AdImage oldAdimage = adImageService.findAdImageById(adImageId);
		if (oldAdimage == null) {
			return new APIResult<AdImage>(HttpStatus.EXPECTATION_FAILED.value(), oldAdimage);
		}
		//参数校验
		try {
			adImage.setModifiedTime(new Date());
			adImage.setOperator(LoginContext.getUserName());

			ValidatorUtils.validate(adImage);
			adImageService.update(adImage);
			return new APIResult<>(adImage);
		} catch (Exception e) {
			return new APIResult<AdImage>(HttpStatus.EXPECTATION_FAILED.value(), adImage);
		}
	}

	/**
	 * 局部修改 权重
	 *
	 * @return
	 */
	@RequestMapping(value = "{adImageId}/weight", method = RequestMethod.PATCH)
	@ResponseBody
	public APIResult<AdImage> updateAdImageWeight(@PathVariable Long adImageId,Integer weight) {

		//获取adImage
		AdImage oldAdimage = adImageService.findAdImageById(adImageId);
		if (oldAdimage == null) {
			return new APIResult<AdImage>(HttpStatus.EXPECTATION_FAILED.value(), oldAdimage);
		}
		AdImage adImage = oldAdimage;
		adImage.setWeight(weight);
		adImage.setModifiedTime(new Date());

		//参数校验
		try {
			ValidatorUtils.validate(adImage);
			adImageService.update(adImage);
			return new APIResult<AdImage>(adImage);
		} catch (Exception e) {
			log.error("参数校验异常:{}",e);
			return new APIResult<AdImage>(HttpStatus.EXPECTATION_FAILED.value(), adImage);
		}

	}

	/**
	 * 局部修改  上下架开关
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "{adImageId}/putOn", method = RequestMethod.PATCH)
	public APIResult<AdImage> updateAdImagePutOn(@PathVariable Long adImageId, Integer moduleCode, Boolean putOn) {
		//获取adImage
		AdImage oldAdimage = adImageService.findAdImageById(adImageId);
		if (oldAdimage == null) {
			return new APIResult<AdImage>(HttpStatus.EXPECTATION_FAILED.value(), oldAdimage);
		}
		AdImage adImage = oldAdimage;
		adImage.setPutOn(putOn);
		adImage.setModifiedTime(new Date());

		try {
			ValidatorUtils.validate(adImage);
			adImageService.update(adImage);
			return new APIResult<AdImage>(adImage);
		} catch (Exception e) {
			return new APIResult<AdImage>(HttpStatus.EXPECTATION_FAILED.value(), adImage);
		}

	}

	/**
	 * 删除广告图
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{adImageId}", method = RequestMethod.DELETE)
	public APIResult<AdImage> delAdImage(@PathVariable Long adImageId) {
		AdImage adImage = adImageService.findAdImageById(adImageId);
		if (adImage == null) {
			return new APIResult<AdImage>(HttpStatus.EXPECTATION_FAILED.value(), adImage);
		}
		adImageService.delete(adImageId);
		return new APIResult<AdImage>(adImage);
	}
}
