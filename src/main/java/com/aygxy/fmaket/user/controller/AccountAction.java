package com.aygxy.fmaket.user.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aygxy.fmaket.Application;
import com.aygxy.fmaket.debug.DebugLog;
import com.aygxy.fmaket.goods.service.GoodsTypeService;
import com.aygxy.fmaket.net.jsonbean.AppInitData;
import com.aygxy.fmaket.net.jsonbean.UserAccount;
import com.aygxy.fmaket.net.procatal.Body;
import com.aygxy.fmaket.net.procatal.BodyProvider;
import com.aygxy.fmaket.param.GlobalParams;
import com.aygxy.fmaket.user.entity.Account;
import com.aygxy.fmaket.user.entity.Token;
import com.aygxy.fmaket.user.service.AccountService;
import com.aygxy.fmaket.util.GsonUtil;

@Controller
@RequestMapping("/account")
public class AccountAction {
	private Logger logger =Logger.getLogger(getClass());
	
	@Resource
	AccountService accountService;
	@Resource
	GoodsTypeService goodsTypeService;
	
	@RequestMapping("/loginAccount.action")
	@ResponseBody
	public void login(HttpServletRequest request){
		String body = (String) request.getAttribute("body");
		logger.debug("Action accept context="+body);
		UserAccount user = GsonUtil.stringToObjectByBean(body, UserAccount.class);
		Body resultbody =  BodyProvider.getFaildBody("登录失败");
		if(null != user.getUsername() && null != user.getUserpassword()){
			Account a = accountService.login(user.getUsername(), user.getUserpassword());
			if(null != a){
				Token token = new Token();
				token.setUserId(a.getUserid());
				token.setCreateTime(System.currentTimeMillis()+"");
				token.setTokenId(UUID.randomUUID().toString());
				String temptoken = a.getUserid().substring(0, 12) + "_" + UUID.randomUUID().toString();
				token.setToken(temptoken);
				Application.Instance().putToken(token);
				resultbody =  BodyProvider.getSuccessBody(a.getUserid());
				resultbody.setToken(temptoken);
			}
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	@RequestMapping("/checkToken.action")
	public void checkToken(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Body responseBody = BodyProvider.getSuccessBody("token校验成功");
		try {
			AppInitData appInitData = new AppInitData();
			appInitData.setGoodsTypes(goodsTypeService.getAllGoodsType());
			String resultJson = GsonUtil.objectToString(appInitData);
			responseBody.setElements(resultJson);
		} catch (Exception e) {
			DebugLog.logger.error("检查token获取App初始化数据时失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,responseBody);
	}
	
}
