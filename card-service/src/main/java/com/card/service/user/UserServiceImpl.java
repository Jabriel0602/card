package com.card.service.user;

import com.card.common.util.Base64Util;
import com.card.dao.UserDao;
import com.card.domain.user.User;
import com.google.common.base.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public int insert(User user){
        return userDao.insert(user);
    }

    @Override
    public int insertSelective(User user){
        return userDao.insertSelective(user);
    }

    @Override
    public int insertList(List<User> users){
        return userDao.insertList(users);
    }

    @Override
    public int update(User user){
        return userDao.update(user);
    }


    @Override
    public User getUser(Long userId) {
        return userDao.findUserByUserId(userId);
    }

    @Override
    public User getUserByNameAndPassWord(String userName,String password) {
        return userDao.getUserByNameAndPassWord(userName, Base64Util.base64ForCharset(password, Charsets.UTF_8.name()));
    }

}
