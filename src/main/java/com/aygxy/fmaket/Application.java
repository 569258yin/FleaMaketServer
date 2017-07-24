package com.aygxy.fmaket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.debug.DebugLog;
import com.aygxy.fmaket.user.dao.AccountMapper;
import com.aygxy.fmaket.user.entity.PhoneCode;
import com.aygxy.fmaket.user.entity.Token;
import com.aygxy.fmaket.user.service.AccountService;
import com.aygxy.fmaket.user.service.EasemobIMUsers;
import com.aygxy.fmaket.user.service.TokenService;
import com.aygxy.fmaket.user.service.TokenServiceImpl;
import com.aygxy.fmaket.util.SpringContextUtil;


@Repository
public class Application {

	private static Application instance;

	/** 7天 * 24小时 * 60分钟 * 60秒 * 1000 ms    */
	private  final static long TOKEN_USED_TIME = 7 * 24 * 60 * 60 * 1000; 
	private  final static Map<String, Token> tokenMap = new HashMap<>(100);
	private final static Map<String, String> userToken = new HashMap<>(100);
	private final static Map<String, String> phoneCode = new HashMap<>(100);

	private TokenService tService;
	private AccountService aService;
	private EasemobIMUsers easemobIMUsers;

	private Application() {
		DebugLog.logger.info("================Application Init===============");
		tService = (TokenService) SpringContextUtil.getBean("tokenServiceImpl");
		aService = (AccountService) SpringContextUtil.getBean("accountService");
		easemobIMUsers = new EasemobIMUsers();
		
	}

	public static Application Instance(){
		if(instance == null){
			Application application = null;
			synchronized (Application.class) {
				if(application == null){
					application = new Application();
					instance = application;
				}
			}
		}
		return instance;
	}
	public EasemobIMUsers getEasemobIMUsers() {
		return easemobIMUsers;
	}

	//	//对于单列模式，此不会被执行，因为构造器是私有的
	//	@PostConstruct
	//	public void init(){
	//		DebugLog.logger.info("================TokenServer Init===============");
	//		tService = new TokenServiceImpl();
	//	}
	//	
	//	@PreDestroy
	//	public void destroy(){
	//		tService = null;
	//	}
	//	

	public synchronized void putToken(Token token){
		try {
			boolean bool = tService.saveToken(token);
			if(bool){
				tokenMap.put(token.getToken(), token);
				userToken.put(token.getUserId(), token.getTokenId());
			}
		} catch (Exception e) {
			DebugLog.logger.error("保存Token失败"+token.getToken(), e);
		}
	}

	/**
	 * 根据客户端发过来的token查询内存中是否有此对应的，如果有，校验token是否过期，如果过期，就将其移除，同时将数据库中对应移除,并返回空
	 * @param token
	 * @return  Token || null
	 */
	public synchronized Token getToken(String token){
		try {
			Token t = tokenMap.get(token);
			if(null != t){
				long createTime = Long.parseLong(t.getCreateTime());
				//Token过期
				if(System.currentTimeMillis() - createTime > TOKEN_USED_TIME){
					DebugLog.logger.info("Token 过期" + token);
					tokenMap.remove(token);
					userToken.remove(t.getUserId());
					boolean bool = tService.deleteTokenById(t.getTokenId());
					if(!bool){
						DebugLog.logger.error("删除Token失败："+t.getTokenId());
						Thread.sleep(200);
						tService.deleteTokenById(t.getTokenId());
					}
					return null;
				}else{
					return t;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将数据库中的Token 加载到内存中 
	 */
	public synchronized void initTokenMap(){
		try{
			List<Token> lists = tService.selectAllToken();
			if(null != lists){
				for (Token token : lists) {
					tokenMap.put(token.getToken(), token);
					userToken.put(token.getUserId(), token.getTokenId());
				}
			}
		}catch (Exception e) {
			DebugLog.logger.error("初始化Token失败",e);
		}
		try {
			List<PhoneCode> pList = aService.selectAllPhoneCode();
			if(null != pList) {
				for (PhoneCode pc : pList) {
					phoneCode.put(pc.getPhone(), pc.getCode());
				}
			}
		} catch (Exception e) {
			DebugLog.logger.error("初始化手机号验证码失败",e);
		}
		
	}

	/**
	 * 用于拦截器校验token是否存在和过期
	 * @param token
	 * @return
	 */
	public boolean checkToken(String token){
		Token t = getToken(token);
		if(t != null){
			return true;
		}
		return false;
	}

	/**
	 * 根据userID判断服务器中先是否已经存在有token,防止重复插入同一用户的多个token
	 * @param userId
	 * @return
	 */
	public String getTokenIdByUserId(String userId){
		return userToken.get(userId);
	}
	
	
	public void putPhoneCode(String phone,String code){
		phoneCode.put(phone, code);
	}
	
	public String getPhoneCode(String phone){
		return phoneCode.get(phone);
	}
}
