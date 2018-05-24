package com.card.service.adimage;


import com.alibaba.fastjson.JSONObject;
import com.card.common.util.RedisUtil;
import com.card.dao.AdImageDao;
import com.card.domain.adimage.AdImage;
import com.card.domain.constant.CacheKeyEnum;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


/**
 * @author yangzhanbang
 */
@Service
public class AdImageServiceImpl implements AdImageService {

	@Autowired
	private AdImageDao adImageDao;

	@Autowired
	private RedisUtil redisUtil;

	@Override
	public int insert(AdImage adImage) {
		Integer count = adImageDao.insert(adImage);
		if (count == 1) {
			sortAdImage(findAllAdImage());
			return count;
		}
		return 0;
	}

	@Override
	public int insertSelective(AdImage adImage) {
		Integer count = adImageDao.insertSelective(adImage);
		if (count == 1) {
			sortAdImage(findAllAdImage());
			return count;
		}
		return 0;
	}

	@Override
	public int insertList(List<AdImage> adImages) {
		Integer count = adImageDao.insertList(adImages);
		if (count == 1) {
			sortAdImage(findAllAdImage());
			return count;
		}
		return 0;
	}

	@Override
	public int update(AdImage adImage) {
		Integer count = adImageDao.update(adImage);
		if (count == 1) {
			sortAdImage(findAllAdImage());
			return count;
		}
		return 0;
	}

	/**
	 * 返回排序值
	 *
	 * @return
	 */
	@Override
	public List<AdImage> findAllAdImage() {
		return sortAdImage(adImageDao.findAllAdImage());
	}

	@Override
	public List<AdImage> findAllAdImageWithStatus() {
		List<AdImage> adImageList = findAllAdImage();
		for (AdImage adImage : adImageList) {
			checkPutOn(adImage);
		}
		return adImageList;
	}

	@Override
	public List<AdImage> findAllAdImageStatusOn() {
		List<AdImage> adImageList = findAllAdImageWithStatus();
		return fitterStatusOn(adImageList);
	}

	/**
	 * 获取 处于上架状态 AdImage
	 */
	@Override
	public List<AdImage> findAllAdImageStatusOnWithCache() {
		List<AdImage> adImageList = JSONObject.parseArray(redisUtil.getJSONString(CacheKeyEnum.CARD_ADIMAGES.getValue()),AdImage.class);
		if (adImageList == null || adImageList.size() == 0) {
			adImageList = findAllAdImage();
			/**
			 * 数据库值为空也要缓存 防止大量请求每次都打得数据库
			 */
			if(adImageList!=null){
				redisUtil.set(CacheKeyEnum.CARD_ADIMAGES.getValue(), adImageList,CacheKeyEnum.CARD_ADIMAGES.getExp());
			}else {
				//防止缓存穿透
				redisUtil.set(CacheKeyEnum.CARD_ADIMAGES.getValue(),CacheKeyEnum.CARD_ADIMAGES.getDefaultValue(),300L);
			}
		}
		/**
		 * 过滤缓存中 已经下架的AdImage
		 */
		return fitterStatusOn(adImageList);
	}

	@Override
	public AdImage findAdImageById(Long id) {
		return adImageDao.findAdImageById(id);
	}

	@Override
	public int delete(Long id) {
		Integer count = adImageDao.delete(id);
		if (count == 1) {
			sortAdImage(findAllAdImage());
			return count;
		}
		return 0;
	}

	/**
	 * 判断上下架 :前端颜色 显示
	 * 1.停用状态  --> 必定下架
	 * 2.启用状态  1).(上架时间<当前时间 || 当前时间<=下架时间））--->发布状态
	 * 2). 未发布
	 *
	 * @param adImage
	 */
	private void checkPutOn(AdImage adImage) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = adImage.getStartTime();
			endDate = adImage.getEndTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (adImage.getPutOn() && startDate.getTime() < System.currentTimeMillis() && System.currentTimeMillis() < endDate.getTime()) {
			adImage.setReleaseStatus(true);
		} else {
			adImage.setReleaseStatus(false);
		}
	}

	private List<AdImage> sortAdImage(List<AdImage> adImageList) {
		Collections.sort(adImageList, new Comparator<AdImage>() {
			@Override
			public int compare(AdImage o1, AdImage o2) {
				return o1.getWeight() - o2.getWeight();
			}
		});
		return adImageList;
	}

	private List<AdImage> fitterStatusOn(List<AdImage> adImageList) {
		List<AdImage> adImageListStatusOn = Lists.newArrayList();
		for (AdImage adImage : adImageList) {
			/**
			 * 更新发布状态
			 */
			checkPutOn(adImage);
			if (adImage.getPutOn()) {
				adImageListStatusOn.add(adImage);
			}
			/**
			 * 最多4个
			 */
			if (adImageListStatusOn.size() >= 4) {
				return adImageListStatusOn;
			}
		}
		return adImageListStatusOn;
	}
}
