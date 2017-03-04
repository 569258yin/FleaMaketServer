package com.aygxy.fmaket.user.dao;

import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.user.entity.UserInfo;

@Repository
public interface UserInfoMapper {

	int deleteByPrimaryKey(String userid);

	int insert(UserInfo record);

	int insertSelective(UserInfo record);

	UserInfo selectByPrimaryKey(String userid);

	int updateByPrimaryKeySelective(UserInfo record);

	int updateByPrimaryKey(UserInfo record);
}