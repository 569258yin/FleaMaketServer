package com.aygxy.fmaket.user.service;

import java.util.List;

import com.aygxy.fmaket.user.entity.Account;
import com.aygxy.fmaket.user.entity.PhoneCode;

public interface AccountService {
	
	Account login(String account,String password);
	
	boolean register(Account account);
	
	boolean virifyPhone(String phone);
	
	boolean deleteAccount(String accountId);
	
	List<PhoneCode> selectAllPhoneCode();
	
	boolean deletePhoneCase(String phone);
	
	boolean selectExistAccount(String account);
}
