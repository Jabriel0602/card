package com.card.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.card.domain.switchs.CardSwitch;

@Mapper
public interface CardSwitchDao {
    int insert(@Param("cardSwitch") CardSwitch cardSwitch);

    int insertSelective(@Param("cardSwitch") CardSwitch cardSwitch);

    int insertList(@Param("cardSwitchs") List<CardSwitch> cardSwitchs);

    int update(@Param("cardSwitch") CardSwitch cardSwitch);

	CardSwitch select(@Param("switchId") Long switchId);

	List<CardSwitch> selectAll();

}
