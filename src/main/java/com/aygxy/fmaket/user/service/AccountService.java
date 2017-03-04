package com.aygxy.fmaket.user.service;

import com.aygxy.fmaket.user.entity.Account;

public interface AccountService {
	
	Account login(String account,String password);
}
