package com.aygxy.fmaket.user.service;

import com.aygxy.fmaket.user.service.AuthTokenAPI;
import com.aygxy.fmaket.util.comm.TokenUtil;


public class EasemobAuthToken implements AuthTokenAPI{

	@Override
	public Object getAuthToken(){
		return TokenUtil.getAccessToken();
	}
}
