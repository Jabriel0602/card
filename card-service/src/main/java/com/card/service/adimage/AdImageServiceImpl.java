package com.card.service.adimage;

import com.card.dao.AdImageDao;
import com.card.domain.adimage.AdImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public AdImage findAdImageById(Long id) {
		return adImageDao.findAdImageById(id);
	}

	@Override
	public int delete(Long id) {
		return adImageDao.delete(id);
	}
}
