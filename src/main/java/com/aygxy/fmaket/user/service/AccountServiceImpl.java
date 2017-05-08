package com.aygxy.fmaket.user.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.aygxy.fmaket.Application;
import com.aygxy.fmaket.debug.DebugLog;
import com.aygxy.fmaket.user.dao.AccountMapper;
import com.aygxy.fmaket.user.dao.PhoneCodeMapper;
import com.aygxy.fmaket.user.entity.Account;
import com.aygxy.fmaket.user.entity.PhoneCode;
import com.aygxy.fmaket.user.service.AccountService;
import com.aygxy.fmaket.util.VirifyPhoneUtil;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Resource
	AccountMapper accountMapper;
	
	@Resource
	PhoneCodeMapper phoneCodeMapper;
	
	@Override
	public Account login(String account, String password) {
		return accountMapper.selectAccount(account, password);
	}

	@Override
	public boolean register(Account account) {
		int count = accountMapper.insert(account);
		return count == 1;
	}

	@Override
	public boolean virifyPhone(String phone) {
		String random = (int)(Math.random() * 999999 + 100000) +"";
		String result = VirifyPhoneUtil.sendSign(phone, random);
		if(StringUtils.isNotEmpty(result) && result.equals("0")){
			PhoneCode phoneCode = phoneCodeMapper.selectByPhone(phone);
			if(phoneCode != null){
				phoneCode.setCode(random);
				int res = phoneCodeMapper.updateByPrimaryKey(phoneCode);
				if(res == 1){
					Application.Instance().putPhoneCode(phone, random);
					return true;
				}
			}else{
				phoneCode = new PhoneCode(UUID.randomUUID().toString().replace("_", "")+"_" + phone, phone, random, new Date());
				int res = phoneCodeMapper.insert(phoneCode);
				if(res == 1){
					Application.Instance().putPhoneCode(phone, random);
					return true;
				}
			}
			DebugLog.logger.info("手机号："+phone+"的验证码是:"+random);
		}
		return false;
	}
	
	public boolean deletePhoneCase(String phone){
		int count = phoneCodeMapper.deleteByPhoneNum(phone);
		return count > 0;
	}

	@Override
	public boolean deleteAccount(String accountId) {
		int count = accountMapper.deleteByPrimaryKey(accountId);
		return count == 1;
	}

	@Override
	public List<PhoneCode> selectAllPhoneCode() {
		List<PhoneCode> lists = phoneCodeMapper.selectAllPhoneCode();
		return lists;
	}

	@Override
	public boolean selectExistAccount(String account) {
		Account a = accountMapper.selectAccountByaccount(account);
		if(a == null){
			return false;
		}else{
			return true;
		}
	}
	
	
	
}
