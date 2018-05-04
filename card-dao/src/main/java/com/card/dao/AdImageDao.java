package com.card.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.card.domain.adimage.AdImage;

@Mapper
public interface AdImageDao {
	int insert(@Param("adImage") AdImage adImage);

	int insertSelective(@Param("adImage") AdImage adImage);

	int insertList(@Param("adImages") List<AdImage> adImages);

	int update(@Param("adImage") AdImage adImage);

	List<AdImage> findAllAdImage();

	AdImage findAdImageById(@Param("id")Long id);

	int delete(@Param("id")Long id);
}
