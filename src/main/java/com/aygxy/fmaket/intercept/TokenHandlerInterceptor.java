package com.aygxy.fmaket.intercept;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aygxy.fmaket.Application;
import com.aygxy.fmaket.debug.DebugLog;
import com.aygxy.fmaket.net.procatal.BaseEngine;
import com.aygxy.fmaket.net.procatal.Body;
import com.aygxy.fmaket.net.procatal.BodyProvider;
import com.aygxy.fmaket.net.procatal.IMessage;
import com.aygxy.fmaket.net.procatal.Oelement;
import com.aygxy.fmaket.param.GlobalParams;
import com.aygxy.fmaket.param.MessageParams;
import com.aygxy.fmaket.param.OelementType;

/**
 * 对所有.action请求进行拦截，校验token是否合法，对所有不合法的Token都要求其重新登录
 * @author MyPC
 *
 */
public class TokenHandlerInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = Logger.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("===============preHandle========================");
		DebugLog.logger.debug("TokenHandlerInterceptor preHandle......");
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(request.getInputStream(),"UTF-8"));
		String requestJson = bufferedReader.readLine();
		logger.debug(requestJson);
		IMessage message = BaseEngine.getResult(requestJson);
		if(message != null){
			logger.debug("请求内容："+message.getBody());
			request.setAttribute("body", message.getBody().getElements());
			//判断是否有token存在，并校验是否过期
			String token = message.getBody().getToken();
			if(StringUtils.isEmpty(token)){
				if(!request.getRequestURI().endsWith("account/virifyPhone.action") && !request.getRequestURI().endsWith("account/registerAccount.action")){
					if(!request.getRequestURI().endsWith("account/loginAccount.action")){
						Body body = new Body(new Oelement(OelementType.TOKEN_FAILD,"Token无效"));
						body.setElements(MessageParams.TOKEN_ERROR);
						String result = BaseEngine.getMessageToJson(body, "");
						response.getOutputStream().write(result.getBytes("utf-8"));
						return false;
					}
				}
			} else{
				if(request.getRequestURI().endsWith("account/checkToken.action")){
					boolean bool = Application.Instance().checkToken(token);
					if(!bool && !request.getRequestURI().endsWith("account/loginAccount.action")){
						Body body = new Body(new Oelement(OelementType.TOKEN_FAILD,"Token无效"));
						body.setElements(MessageParams.TOKEN_ERROR);
						String result = BaseEngine.getMessageToJson(body, "");
						response.getOutputStream().write(result.getBytes("utf-8"));
						return false;
					}
				}
			}
		}else{
			Body body = new Body(new Oelement(OelementType.LOCAL_CHECK_MD5_ERROR,"安全检查失败"));
			body.setElements(MessageParams.SIGN_ERROR);
			String result = BaseEngine.getMessageToJson(body, "");
			response.getOutputStream().write(result.getBytes("utf-8"));
			return false;
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Body body = (Body) request.getSession().getAttribute(GlobalParams.RESULT);
		if(body != null){
			logger.debug("afterCompletion body:"+body.toString());
			String result = BaseEngine.getMessageToJson(body, "");
			logger.debug("afterCompletion return message:"+result);
			response.getOutputStream().write(result.getBytes("utf-8"));
		}
	}
}
