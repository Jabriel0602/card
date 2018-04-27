package com.card.controller;

import com.card.common.util.ValidatorUtils;
import com.card.domain.MethodTypeEnum;
import com.card.domain.adimage.AdImage;
import com.card.domain.result.APIResult;
import com.card.service.adimage.AdImageService;
import com.card.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/6 16:09
 */
@RestController
@RequestMapping("/adimages")
public class AdImageController {
	@Autowired
	private UserService userService;

	@Autowired
	private AdImageService adImageService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView queryAdImageByModuleType() {

		AdImage adImage = new AdImage();
		List<AdImage> adImageList = adImageService.findAllAdImage();
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
		mv.addObject("adImage", adImage);

		return mv;
	}

	/**
	 * 创建轮播图操作
	 *
	 * @param moduleCode
	 * @param adImage
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public APIResult<AdImage> addImage(Integer moduleCode, AdImage adImage) {
		//参数校验
		try {
			ValidatorUtils.validate(adImage);
			adImageService.insert(adImage);
			return new APIResult<AdImage>(adImage);
		} catch (Exception e) {
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
	public APIResult<AdImage> updateAdImageWeight(@PathVariable Long adImageId, Integer moduleCode, Integer weight) {

		//获取adImage
		AdImage oldAdimage = adImageService.findAdImageById(adImageId);
		if (oldAdimage == null) {
			return new APIResult<AdImage>(HttpStatus.EXPECTATION_FAILED.value(), oldAdimage);
		}
		AdImage adImage = oldAdimage;
		adImage.setWeight(weight);
		//参数校验
		try {
			ValidatorUtils.validate(adImage);
			adImageService.update(adImage);
			return new APIResult<AdImage>(adImage);
		} catch (Exception e) {
			return new APIResult<AdImage>(HttpStatus.EXPECTATION_FAILED.value(), adImage);
		}

	}

	/**
	 * 局部修改  上下架开关
	 *
	 * @return
	 */
	@RequestMapping(value = "{adImageId}/putOn", method = RequestMethod.PATCH)
	public APIResult<AdImage> updateAdImagePutOn(@PathVariable Long adImageId, Integer moduleCode, Boolean putOn) {
		//获取adImage
		AdImage oldAdimage = adImageService.findAdImageById(adImageId);
		if (oldAdimage == null) {
			return new APIResult<AdImage>(HttpStatus.EXPECTATION_FAILED.value(), oldAdimage);
		}
		AdImage adImage = oldAdimage;
		adImage.setPutOn(putOn);
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
	@RequestMapping(value = "/{adImageId}", method = RequestMethod.DELETE)
	public APIResult<AdImage> delAdImage(@PathVariable Long adImageId, Integer moduleCode) {
		AdImage adImage = adImageService.findAdImageById(adImageId);
		if (adImage == null) {
			return new APIResult<AdImage>(HttpStatus.EXPECTATION_FAILED.value(), adImage);
		}
		adImageService.delete(adImageId);
		return new APIResult<AdImage>(adImage);
	}
}
