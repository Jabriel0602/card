package com.card.service.icon;

import com.card.dao.IconDao;
import com.card.domain.icon.Icon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author yangzhanbang
 */
@Service
public class IconServiceImpl implements IconService {

	@Autowired
	private IconDao IconDao;

	@Override
	public int insert(Icon icon) {
		return IconDao.insert(icon);
	}

	@Override
	public int insertSelective(Icon icon) {
		return IconDao.insertSelective(icon);
	}

	@Override
	public int insertList(List<Icon> IconDaos) {
		return IconDao.insertList(IconDaos);
	}

	@Override
	public int update(Icon icon) {
		return IconDao.update(icon);
	}

	@Override
	public List<Icon> findAllIcon() {
		return IconDao.findAllIcon();
	}

	@Override
	public List<Icon> findAllAdImagStatusOn() {
		List<Icon> IconDaoList = IconDao.findAllIcon();
		for (Icon icon :IconDaoList) {
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
		return IconDao.delete(id);
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
}
