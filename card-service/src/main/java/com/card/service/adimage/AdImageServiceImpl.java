package com.card.service.adimage;

import com.card.dao.AdImageDao;
import com.card.domain.adimage.AdImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author yangzhanbang
 */
@Service
public class AdImageServiceImpl implements AdImageService {

	@Autowired
	private AdImageDao adImageDao;

	@Override
	public int insert(AdImage adImage) {
		return adImageDao.insert(adImage);
	}

	@Override
	public int insertSelective(AdImage adImage) {
		return adImageDao.insertSelective(adImage);
	}

	@Override
	public int insertList(List<AdImage> adImages) {
		return adImageDao.insertList(adImages);
	}

	@Override
	public int update(AdImage adImage) {
		return adImageDao.update(adImage);
	}

	@Override
	public List<AdImage> findAllAdImage() {
		return adImageDao.findAllAdImage();
	}

	@Override
	public List<AdImage> findAllAdImagStatusOn() {
		List<AdImage> adImageList = adImageDao.findAllAdImage();
		for (AdImage adImage :adImageList) {
			checkPutOn(adImage);
		}
		return adImageList;
	}

	@Override
	public AdImage findAdImageById(Long id) {
		return adImageDao.findAdImageById(id);
	}

	@Override
	public int delete(Long id) {
		return adImageDao.delete(id);
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
}
