package com.aygxy.fmaket.user.service;

import java.util.List;

import com.aygxy.fmaket.user.entity.Token;

public interface TokenService {
	
	Token selectTokenById(String tokenId);
	
	boolean deleteTokenById(String tokenId);
	
	boolean deleteTokenByUserId(String userId);
	
	boolean saveToken(Token token);
	
	List<Token> selectAllToken();
}
