package com.card.service.config;

import com.card.dao.ConfigDao;
import com.card.domain.config.Config;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class ConfigServiceImpl implements ConfigService{

    private ConfigDao configDao;

    @Override
    public int insert(Config config){
        return configDao.insert(config);
    }

    @Override
    public int insertSelective(Config config){
        return configDao.insertSelective(config);
    }

    @Override
    public int insertList(List<Config> configs){
        return configDao.insertList(configs);
    }

    @Override
    public int update(Config config){
        return configDao.update(config);
    }
}
