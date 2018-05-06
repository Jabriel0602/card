package com.card.service.adimage;

import java.util.List;
import com.card.domain.adimage.AdImage;

public interface AdImageService{

    int insert(AdImage adImage);

    int insertSelective(AdImage adImage);

    int insertList(List<AdImage> adImages);

    int update(AdImage adImage);

    List<AdImage> findAllAdImage();

    List<AdImage> findAllAdImageWithStatus();

    List<AdImage> findAllAdImageStatusOn();

    AdImage findAdImageById(Long id);

    int delete(Long id);

}
