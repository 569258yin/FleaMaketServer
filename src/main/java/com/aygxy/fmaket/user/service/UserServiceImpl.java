package com.aygxy.fmaket.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aygxy.fmaket.user.dao.UserInfoMapper;
import com.aygxy.fmaket.user.entity.UserInfo;

@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	UserInfoMapper userInfoMapper;
	
	@Override
	public UserInfo getUserInfo(String userId) {
		return userInfoMapper.selectByPrimaryKey(userId);
	}

	@Override
	public boolean saveUserInfo(UserInfo userInfo) {
		int count = userInfoMapper.insert(userInfo);
		return count == 1;
	}

	@Override
	public boolean updateUserInfo(UserInfo userInfo) {
		int count = userInfoMapper.updateByPrimaryKey(userInfo);
		return count == 1;
	}

	@Override
	public boolean deleteUserInfo(String userId) {
		return false;
	}

}
