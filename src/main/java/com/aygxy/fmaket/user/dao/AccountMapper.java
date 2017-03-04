package com.aygxy.fmaket.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.user.entity.Account;

@Repository
public interface AccountMapper {
	
    int deleteByPrimaryKey(@Param(value = "userid")String userid);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(@Param(value = "userid")String userid);
    
    /**
     * 用于登录，校验用户名和密码是否正确
     * @param account
     * @param password
     * @return
     */
    Account selectAccount(@Param(value = "account")String account,@Param(value = "password")String password);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}