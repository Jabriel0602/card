package com.card.dao;

import com.card.domain.icon.Icon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IconDao {
    int insert(@Param("icon") Icon icon);

    int insertSelective(@Param("icon") Icon icon);

    int insertList(@Param("icons") List<Icon> icons);

    int update(@Param("icon") Icon icon);

    List<Icon> findAllIcon();

    Icon findIconById(@Param("id") Long id);

    int delete(@Param("id") Long id);
}
