package com.card.service.user;

import com.card.common.util.Base64Util;
import com.card.common.util.LoginContext;
import com.card.dao.UserDao;
import com.card.domain.MethodTypeEnum;
import com.card.domain.ModuleTypeEnum;
import com.card.domain.YnEnum;
import com.card.domain.user.User;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public int insertSelective(User user) {
		return userDao.insertSelective(user);
	}

	@Override
	public int insertList(List<User> users) {
		return userDao.insertList(users);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public int delete(Long userId) {
		User user = getUser(userId);
		user.setYn(YnEnum.N.getCode());
		return update(user);
	}


	@Override
	public User getUser(Long userId) {
		return userDao.findUserByUserId(userId);
	}

	@Override
	public User getUserByNameAndPassWord(String userName, String password) {
		return userDao.getUserByNameAndPassWord(userName, Base64Util.base64ForCharset(password, Charsets.UTF_8.name()));
	}

	@Override
	public User getCurrentUser() {
		return getUser(LoginContext.getUserId());
	}

	@Override
	public List<User> getUserList() {
		return userDao.listUser();
	}


	@Override
	public Boolean getMethodTypeLimitByCurrentUser(MethodTypeEnum methodTypeEnum) {
		return getMethodTypeLimitsMap(getCurrentUser()).get(methodTypeEnum.getType());
	}

	@Override
	public Map<String, Boolean> getModuleTypeLimitsMap(User user) {
		Map moduleTypeLimitsMap = Maps.newHashMap();
		Integer moduleTypeLimits = user.getModuleTypeLimits();
		for (ModuleTypeEnum moduleTypeEnum : ModuleTypeEnum.values()) {
			moduleTypeLimitsMap.put(moduleTypeEnum.getType(), (moduleTypeLimits & moduleTypeEnum.getCode()) == moduleTypeEnum.getCode());
		}
		return moduleTypeLimitsMap;
	}

	@Override
	public Map<String, Boolean> getMethodTypeLimitsMap(User user) {
		Map methodTypeLimitsMap = Maps.newHashMap();
		Integer methodTypeLimits = user.getMethodTypeLimits();
		for (MethodTypeEnum methodTypeEnum : MethodTypeEnum.values()) {
			methodTypeLimitsMap.put(methodTypeEnum.getType(), (methodTypeLimits & methodTypeEnum.getCode()) == methodTypeEnum.getCode());
		}
		return methodTypeLimitsMap;
	}

}
