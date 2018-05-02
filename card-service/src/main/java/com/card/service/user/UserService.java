package com.card.service.user;

import java.util.List;
import java.util.Map;

import com.card.domain.MethodTypeEnum;
import com.card.domain.user.User;
import org.apache.ibatis.annotations.Param;

public interface UserService{

    int insert(User user);

    int insertSelective(User user);

    int insertList(List<User> users);

    int update(User user);

    int delete(Long userId);

    User getUser(Long userId);

    User getUserByNameAndPassWord(String userName,String password);

    User getCurrentUser();

    List<User> getUserList();

    /**
     * 根据当前环境的ERP账号,获取方法权限
     *
     * @param
     * @return
     */
    Boolean getMethodTypeLimitByCurrentUser(MethodTypeEnum methodTypeEnum);

    /**
     * 获取指定用户 Tab栏的权限 map
     */
    Map<String, Boolean> getModuleTypeLimitsMap(User user);

    /**
     * 获取指定用户 方法的权限 map
     */
    Map<String, Boolean> getMethodTypeLimitsMap(User user);


}
