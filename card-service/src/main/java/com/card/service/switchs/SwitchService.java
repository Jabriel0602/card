package com.card.service.switchs;

import java.util.List;
import com.card.domain.switchs.CardSwitch;
public interface SwitchService{

    int insert(CardSwitch cardSwitch);

    int insertSelective(CardSwitch cardSwitch);

    int insertList(List<CardSwitch> cardSwitchs);

    int update(CardSwitch cardSwitch);

    int updateSwitchStatus(Long switchId);

    CardSwitch select(Long switchId);

    List<CardSwitch> listCardSwitch();
}
