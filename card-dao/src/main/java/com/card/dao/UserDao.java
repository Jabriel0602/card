package com.card.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.card.domain.user.User;

@Mapper
public interface UserDao {
	int insert(@Param("user") User user);

	int insertSelective(@Param("user") User user);

	int insertList(@Param("users") List<User> users);

	int update(@Param("user") User user);

	int updateByUserId(@Param("userId") Long userId, @Param("user") User user);

	User findUserByUserId(@Param("userId") Long userId);

	User getUserByNameAndPassWord(@Param("userName") String userName,@Param("password") String password);

}
