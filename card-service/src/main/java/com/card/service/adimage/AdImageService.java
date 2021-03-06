package com.card.service.adimage;

import java.util.List;
import com.card.domain.adimage.AdImage;
import org.apache.ibatis.annotations.Param;

public interface AdImageService{

    int insert(AdImage adImage);

    int insertSelective(AdImage adImage);

    int insertList(List<AdImage> adImages);

    int update(AdImage adImage);

    List<AdImage> findAllAdImage();

    AdImage findAdImageById(Long id);

    int delete(Long id);

}
