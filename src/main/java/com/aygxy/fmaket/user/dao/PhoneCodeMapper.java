package com.aygxy.fmaket.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.user.entity.PhoneCode;

@Repository
public interface PhoneCodeMapper {
	
	PhoneCode selectByPrimaryKey(@Param("id")String id);
	
	PhoneCode selectByPhone(@Param("phone")String phone);
	
	int insert(PhoneCode phoneCode);
	
	int deleteByPrimaryKey(@Param("id")String id);
	
	int deleteByPhoneNum(@Param("phone")String phone);
	
	int updateByPrimaryKey(PhoneCode phoneCode);
	
	List<PhoneCode> selectAllPhoneCode();
}
