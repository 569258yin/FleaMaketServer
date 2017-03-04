package com.aygxy.fmaket.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.aygxy.fmaket.user.dao.AccountMapper;
import com.aygxy.fmaket.user.entity.Account;
import com.aygxy.fmaket.user.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Resource
	AccountMapper accountMapper;
	
	@Override
	public Account login(String account, String password) {
		return accountMapper.selectAccount(account, password);
	}
	
}
