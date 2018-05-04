package com.card.service.icon;

import com.card.domain.icon.Icon;

import java.util.List;

public interface IconService{

    int insert(Icon icon);

    int insertSelective(Icon icon);

    int insertList(List<Icon> icons);

    int update(Icon icon);

    List<Icon> findAllIcon();

    List<Icon> findAllAdImagStatusOn();


    Icon findIconById(Long id);

    int delete(Long id);

}