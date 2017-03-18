package com.aygxy.fmaket.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aygxy.fmaket.net.procatal.Body;
import com.aygxy.fmaket.net.procatal.BodyProvider;
import com.aygxy.fmaket.param.GlobalParams;
import com.aygxy.fmaket.user.entity.UserInfo;
import com.aygxy.fmaket.user.service.UserService;
import com.aygxy.fmaket.util.GsonUtil;

@Controller
@RequestMapping("user")
public class UserAction {
	private Logger logger =Logger.getLogger(getClass());
	
	@Resource
	UserService userService;
	
	@RequestMapping("getUserInfo.action")
	@ResponseBody
	public void getUserInfo(HttpServletRequest request){
		String userId = (String) request.getAttribute("body");
		Body resultbody =  BodyProvider.getFaildBody("查询用户信息失败");
		logger.info("UserId:"+userId);
		if(StringUtils.isNotEmpty(userId)){
			UserInfo userInfo = userService.getUserInfo(userId);
			if(userInfo != null){
				resultbody =  BodyProvider.getSuccessBody();
				resultbody.setElements(GsonUtil.objectToString(userInfo));
			}
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
}
