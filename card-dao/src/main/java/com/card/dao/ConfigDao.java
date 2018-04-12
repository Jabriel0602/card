package com.card.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.card.domain.config.Config;


@Mapper
public interface ConfigDao {
    int insert(@Param("config") Config config);

    int insertSelective(@Param("config") Config config);

    int insertList(@Param("configs") List<Config> configs);

    int update(@Param("config") Config config);
}
