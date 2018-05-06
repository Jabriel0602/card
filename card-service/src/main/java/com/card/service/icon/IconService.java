package com.card.service.icon;

import com.card.domain.adimage.AdImage;
import com.card.domain.icon.Icon;

import java.util.List;

public interface IconService{

    int insert(Icon icon);

    int insertSelective(Icon icon);

    int insertList(List<Icon> icons);

    int update(Icon icon);

    List<Icon> findAllIcon();

    List<Icon> findAllIconStatusOn();

    List<Icon> findAllIconWithStatus();

    Icon findIconById(Long id);

    int delete(Long id);

}
