package com.card.service.switchs;

import com.card.common.util.LoginContext;
import com.card.dao.CardSwitchDao;
import com.card.domain.switchs.CardSwitch;
import com.card.domain.switchs.SwitchEnum;
import com.card.domain.switchs.SwitchStatusEnum;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class SwitchServiceImpl implements SwitchService{

    @Resource
    private CardSwitchDao cardSwitchDao;

    @Override
    public int insert(CardSwitch cardSwitch){
        return cardSwitchDao.insert(cardSwitch);
    }

    @Override
    public int insertSelective(CardSwitch cardSwitch){
        return cardSwitchDao.insertSelective(cardSwitch);
    }

    @Override
    public int insertList(List<CardSwitch> cardSwitchs){
        return cardSwitchDao.insertList(cardSwitchs);
    }

    @Override
    public int update(CardSwitch cardSwitch){
        return cardSwitchDao.update(cardSwitch);
    }

    @Override
    public int updateSwitchStatus(Long switchId) {
        CardSwitch cardSwitch=select(switchId);
        Integer newStatus=-cardSwitch.getSwitchStatus();
        cardSwitch.setSwitchStatus(newStatus);
        cardSwitch.setSwitchStatusDesc(SwitchStatusEnum.getByCode(newStatus).getDesc());
        cardSwitch.setModifiedTime(new Date());
        cardSwitch.setOperator(LoginContext.getUserName());
        return update(cardSwitch);
    }

    @Override
    public CardSwitch select(Long switchId) {

        CardSwitch cardSwitch=cardSwitchDao.select(switchId);
        if(cardSwitch == null){
            cardSwitch=SwitchEnum.getSwitchEnum(switchId).buildSwitch();
            insert(cardSwitch);
        }

        return cardSwitchDao.select(switchId);
    }

    @Override
    public List<CardSwitch> listCardSwitch() {
        return cardSwitchDao.selectAll();
    }
}
