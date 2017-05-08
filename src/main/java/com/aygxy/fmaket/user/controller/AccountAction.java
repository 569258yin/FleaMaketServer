package com.aygxy.fmaket.user.controller;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aygxy.fmaket.Application;
import com.aygxy.fmaket.debug.DebugLog;
import com.aygxy.fmaket.goods.service.GoodsTypeService;
import com.aygxy.fmaket.net.jsonbean.AppInitData;
import com.aygxy.fmaket.net.jsonbean.PageJsonData;
import com.aygxy.fmaket.net.procatal.Body;
import com.aygxy.fmaket.net.procatal.BodyProvider;
import com.aygxy.fmaket.param.GlobalParams;
import com.aygxy.fmaket.user.entity.Account;
import com.aygxy.fmaket.user.entity.Token;
import com.aygxy.fmaket.user.entity.UserInfo;
import com.aygxy.fmaket.user.service.AccountService;
import com.aygxy.fmaket.user.service.UserService;
import com.aygxy.fmaket.util.GsonUtil;

@Controller
@RequestMapping("/account")
public class AccountAction {
	private Logger logger =Logger.getLogger(getClass());

	@Resource
	AccountService accountService;
	@Resource
	GoodsTypeService goodsTypeService;
	@Resource
	UserService userService;
	
	@RequestMapping("/loginAccount.action")
	@ResponseBody
	public void login(HttpServletRequest request){
		String body = (String) request.getAttribute("body");
		logger.debug("Action accept context="+body);
		Body resultbody =  BodyProvider.getFaildBody("登录失败");
		try{
			Account user = GsonUtil.stringToObjectByBean(body, Account.class);
			if(null != user.getUseraccount() && null != user.getUserpassword()){
				Account a = accountService.login(user.getUseraccount(), user.getUserpassword());
				if(null != a){
					String tokenId = Application.Instance().getTokenIdByUserId(a.getUserid());
					if(StringUtils.isNotEmpty(tokenId)) {
						Token t = Application.Instance().getToken(tokenId);
						if(t != null) {
							resultbody =  BodyProvider.getSuccessBody(a.getUserid());
							resultbody.setToken(t.getToken());
						}else{
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
					}else{
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
			}
		}catch (Exception e) {
			DebugLog.logger.error("登录失败"+body);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}

	@RequestMapping("/checkToken.action")
	@ResponseBody
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
	
	
	@RequestMapping("/registerAccount.action")
	@ResponseBody
	public void registerAccount(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Body responseBody = BodyProvider.getFaildBody("注册账号失败");
		String body = (String) request.getAttribute("body");
		try {
			Account account = GsonUtil.stringToObjectByBean(body, Account.class);
			if(account != null){
				//用户名
				if(!accountService.selectExistAccount(account.getUseraccount())){
					//验证码
					String localCode = Application.Instance().getPhoneCode(account.getPhoneNum());
					if(StringUtils.isNotEmpty(localCode) && localCode.equals(account.getCode())){
						account.setUserid(UUID.randomUUID().toString().replace("-", "")+account.hashCode());
						if(accountService.register(account)){
							UserInfo userInfo = new UserInfo();
							userInfo.setUserid(account.getUserid());
							userInfo.setCreateTime(new Date());
							userInfo.setModifyTime(new Date());
							userInfo.setUsersex(0);
							userInfo.setUserage(20);
							boolean res = false;
							try {
								res = userService.saveUserInfo(userInfo);
							} catch (Exception e) {
								DebugLog.logger.error("账号注册成功，但插入用户失败！",e);
							}
							if(res){
								responseBody = BodyProvider.getSuccessBody();
							}else{
								DebugLog.logger.error("账号注册成功，但插入用户失败！");
								accountService.deleteAccount(account.getUserid());
							}
						}
					}else{
						responseBody.getOelement().setMessage("验证码错误");
					}
				}else{
					responseBody.getOelement().setMessage("用户名已存在");
				}
			}
		} catch (Exception e) {
			DebugLog.logger.error("注册账号失败"+body, e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,responseBody);
	}
	
	@RequestMapping("/virifyPhone.action")
	@ResponseBody
	public void virifyPhone(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Body responseBody = BodyProvider.getFaildBody("获取手机验证码失败");
		String body = (String) request.getAttribute("body");
		try {
			PageJsonData pageJsonData = GsonUtil.stringToObjectByBean(body, PageJsonData.class);
			if(pageJsonData != null){
				if(accountService.virifyPhone(pageJsonData.getId())){
					responseBody = BodyProvider.getSuccessBody();
				}
			}
		} catch (Exception e) {
			DebugLog.logger.error("获取手机验证码失败"+body, e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,responseBody);
	}

}
