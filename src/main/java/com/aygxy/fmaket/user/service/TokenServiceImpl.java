package com.aygxy.fmaket.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aygxy.fmaket.user.dao.TokenMapper;
import com.aygxy.fmaket.user.entity.Token;

@Service
public class TokenServiceImpl implements TokenService{
	
	@Resource
	TokenMapper tokenMapper;
	
	
	@Override
	public Token selectTokenById(String tokenId) {
		return tokenMapper.selectByPrimaryKey(tokenId);
	}

	@Override
	public boolean deleteTokenById(String tokenId) {
		int count = tokenMapper.deleteByPrimaryKey(tokenId);
		if(count > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean saveToken(Token token) {
		int count = tokenMapper.insertSelective(token);
		if(count > 0){
			return true;
		}
		return false;
	}

	@Override
	public List<Token> selectAllToken() {
		return tokenMapper.selectAllToken();
	}

	@Override
	public boolean deleteTokenByUserId(String userId) {
		return false;
	}
	

}
