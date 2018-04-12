package com.card.service.config;

import java.util.List;
import com.card.domain.config.Config;
public interface ConfigService{

    int insert(Config config);

    int insertSelective(Config config);

    int insertList(List<Config> configs);

    int update(Config config);
}
