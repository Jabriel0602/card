package com.card.service.user;

import java.util.List;
import com.card.domain.user.User;
import org.apache.ibatis.annotations.Param;

public interface UserService{

    int insert(User user);

    int insertSelective(User user);

    int insertList(List<User> users);

    int update(User user);

    int updateByUserPin(@Param("userId")Long userId, @Param("users") User user);

    User getUser(Long userId);
}
