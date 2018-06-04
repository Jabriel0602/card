package com.card.service.icon;

import com.alibaba.fastjson.JSONObject;
import com.card.common.util.RedisUtil;
import com.card.dao.IconDao;
import com.card.domain.constant.CacheKeyEnum;
import com.card.domain.icon.Icon;
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
public class IconServiceImpl implements IconService {

	@Autowired
	private IconDao IconDao;

	@Autowired
	private RedisUtil redisUtil;

	@Override
	public int insert(Icon icon) {
		Integer count = IconDao.insert(icon);
		if (count == 1) {
			sortIcon(findAllIcon());
			/**
			 * 有变化删除缓存
			 */
			redisUtil.delete(CacheKeyEnum.CARD_ICONS.getKey());
			return count;
		}
		return 0;
	}

	@Override
	public int insertSelective(Icon icon) {
		Integer count = IconDao.insertSelective(icon);
		if (count == 1) {
			sortIcon(findAllIcon());
			/**
			 * 有变化删除缓存
			 */
			redisUtil.delete(CacheKeyEnum.CARD_ICONS.getKey());
			return count;
		}
		return 0;
	}

	@Override
	public int insertList(List<Icon> iconList) {
		Integer count = IconDao.insertList(iconList);
		if (count == 1) {
			sortIcon(findAllIcon());
			/**
			 * 有变化删除缓存
			 */
			redisUtil.delete(CacheKeyEnum.CARD_ICONS.getKey());
			return count;
		}
		return 0;
	}

	@Override
	public int update(Icon icon) {
		Integer count = IconDao.update(icon);
		if (count == 1) {
			sortIcon(findAllIcon());
			/**
			 * 有变化删除缓存
			 */
			redisUtil.delete(CacheKeyEnum.CARD_ICONS.getKey());
			return count;
		}
		return 0;
	}

	/**
	 * 返回有序值
	 *
	 * @return
	 */
	@Override
	public List<Icon> findAllIcon() {
		return sortIcon(IconDao.findAllIcon());
	}

	@Override
	public List<Icon> findAllIconStatusOn() {
		List<Icon> iconList = findAllIcon();
		return fitterStatusOn(iconList);
	}

	/**
	 * 获取 处于上架状态的 ICON
	 */
	@Override
	public List<Icon> findAllIconStatusOnWithCache() {
		List<Icon> iconList = JSONObject.parseArray(redisUtil.getJSONString(CacheKeyEnum.CARD_ICONS.getKey()), Icon.class);

		if (iconList == null || iconList.size() == 0) {
			iconList = findAllIconStatusOn();
			/**
			 * 数据库值为空也要缓存 防止大量请求每次都打得数据库
			 */
			if(iconList!=null){
				redisUtil.set(CacheKeyEnum.CARD_ICONS.getKey(), iconList,CacheKeyEnum.CARD_ICONS.getExp());
			}else {
				//防止缓存穿透
				redisUtil.set(CacheKeyEnum.CARD_ICONS.getKey(),CacheKeyEnum.CARD_ICONS.getDefaultValue(),300L);
			}
		}


		/**
		 * 过滤缓存中 已经下架的ICON
		 */
		return fitterStatusOn(iconList);
	}

	@Override
	public List<Icon> findAllIconWithStatus() {
		List<Icon> IconDaoList = findAllIcon();
		for (Icon icon : IconDaoList) {
			checkPutOn(icon);
		}
		return IconDaoList;
	}

	@Override
	public Icon findIconById(Long id) {
		return IconDao.findIconById(id);
	}

	@Override
	public int delete(Long id) {
		Integer count = IconDao.delete(id);
		if (count == 1) {
			sortIcon(findAllIcon());
			/**
			 * 有变化删除缓存
			 */
			redisUtil.delete(CacheKeyEnum.CARD_ICONS.getKey());
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
	 * @param icon
	 */
	private void checkPutOn(Icon icon) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = icon.getStartTime();
			endDate = icon.getEndTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (icon.getPutOn() && startDate.getTime() < System.currentTimeMillis() && System.currentTimeMillis() < endDate.getTime()) {
			icon.setReleaseStatus(true);
		} else {
			icon.setReleaseStatus(false);
		}
	}

	private List<Icon> sortIcon(List<Icon> iconList) {
		Collections.sort(iconList, new Comparator<Icon>() {
			@Override
			public int compare(Icon o1, Icon o2) {
				return o1.getWeight() - o2.getWeight();
			}
		});
		return iconList;
	}

	private List<Icon> fitterStatusOn(List<Icon> iconList) {
		List<Icon> iconListStatusOn = Lists.newArrayList();
		for (Icon icon : iconList) {
			/**
			 * 设置
			 */
			checkPutOn(icon);
			if (icon.getPutOn()) {
				iconListStatusOn.add(icon);
			}
			/**
			 * 最多4个
			 */
			if (iconListStatusOn.size() >= 4) {
				return iconListStatusOn;
			}
		}
		return iconListStatusOn;
	}
}
