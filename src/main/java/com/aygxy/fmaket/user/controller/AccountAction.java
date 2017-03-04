package com.aygxy.fmaket.user.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aygxy.fmaket.Application;
import com.aygxy.fmaket.net.jsonbean.UserAccount;
import com.aygxy.fmaket.net.procatal.Body;
import com.aygxy.fmaket.net.procatal.BodyProvider;
import com.aygxy.fmaket.net.procatal.Oelement;
import com.aygxy.fmaket.param.GlobalParams;
import com.aygxy.fmaket.param.OelementType;
import com.aygxy.fmaket.user.entity.Account;
import com.aygxy.fmaket.user.entity.Token;
import com.aygxy.fmaket.user.service.AccountService;
import com.aygxy.fmaket.util.GsonUtil;

@Controller
@RequestMapping("account")
public class AccountAction {
	private Logger logger =Logger.getLogger(getClass());
	
	@Resource
	AccountService aService;
	
	@RequestMapping("login.action")
	@ResponseBody
	public void login(HttpServletRequest request){
		String body = (String) request.getAttribute("body");
		logger.debug("Action accept context="+body);
		UserAccount user = GsonUtil.stringToObjectByBean(body, UserAccount.class);
		Body resultbody =  BodyProvider.getFaildBody("登录失败");
		if(null != user.getUsername() && null != user.getUserpassword()){
			Account a = aService.login(user.getUsername(), user.getUserpassword());
			if(null != a){
				Token token = new Token();
				token.setUserId(a.getUserid());
				token.setCreateTime(System.currentTimeMillis()+"");
				token.setTokenId(UUID.randomUUID().toString());
				String temptoken = a.getUserid().substring(0, 12) + "_" + UUID.randomUUID().toString();
				token.setToken(temptoken);
				Application.Instance().putToken(token);
				resultbody =  BodyProvider.getSuccessBody("登录成功");
				resultbody.setToken(temptoken);
			}
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	@RequestMapping("checkToken.action")
	@ResponseBody
	public void checkToken(HttpServletRequest request){
		request.getSession().setAttribute(GlobalParams.RESULT, BodyProvider.getSuccessBody("token校验成功"));
	}
	
	
	
	
	
}
