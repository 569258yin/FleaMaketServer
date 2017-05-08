package com.aygxy.fmaket.user.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aygxy.fmaket.debug.DebugLog;
import com.aygxy.fmaket.net.jsonbean.PageJsonData;
import com.aygxy.fmaket.net.procatal.Body;
import com.aygxy.fmaket.net.procatal.BodyProvider;
import com.aygxy.fmaket.param.GlobalParams;
import com.aygxy.fmaket.user.entity.UserInfo;
import com.aygxy.fmaket.user.service.UserService;
import com.aygxy.fmaket.util.GsonUtil;

@Controller
@RequestMapping("/user")
public class UserAction {
	private Logger logger =Logger.getLogger(getClass());

	@Resource
	UserService userService;

	@RequestMapping("/getUserInfo.action")
	@ResponseBody
	public void getUserInfo(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("查询用户信息失败");
		String body = (String) request.getAttribute("body");
		try {
			PageJsonData pageJsonData = GsonUtil.stringToObjectByBean(body, PageJsonData.class);
			UserInfo userInfo = userService.getUserInfo(pageJsonData.getId());
			if(userInfo != null){
				resultbody =  BodyProvider.getSuccessBody();
				resultbody.setElements(GsonUtil.objectToString(userInfo));
			}
		} catch (Exception e) {
			DebugLog.logger.error("查询用户信息失败"+body, e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}

	@RequestMapping("/saveUserInfo.action")
	@ResponseBody
	public void saveUserInfo(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("新增用户信息失败");
		String body = (String) request.getAttribute("body");
		try{
			UserInfo userInfo = GsonUtil.stringToObjectByBean(body, UserInfo.class);
			boolean result  = userService.saveUserInfo(userInfo);
			if(result){
				resultbody =  BodyProvider.getSuccessBody();
			}
		}catch (Exception e) {
			DebugLog.logger.error("新增用户信息失败"+body, e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}


	@RequestMapping("/updateUserInfo.action")
	@ResponseBody
	public void updateUserInfo(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("修改用户信息失败");
		String body = (String) request.getAttribute("body");
		try{
			UserInfo userInfo = GsonUtil.stringToObjectByBean(body, UserInfo.class);
			userInfo.setModifyTime(new Date());
			boolean result  = userService.updateUserInfo(userInfo);
			if(result){
				resultbody =  BodyProvider.getSuccessBody();
			}
		}catch (Exception e) {
			DebugLog.logger.error("修改用户信息失败"+body, e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}



}
