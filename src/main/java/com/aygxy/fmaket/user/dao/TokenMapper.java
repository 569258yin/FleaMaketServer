package com.aygxy.fmaket.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.user.entity.Token;

@Repository
public interface TokenMapper {
	
	int deleteByPrimaryKey(@Param(value = "tokenId")String tokenId);

    int insert(Token record);

    int insertSelective(Token record);

    List<Token> selectAllToken();
    
    Token selectByPrimaryKey(@Param(value = "tokenId")String tokenId);
    
    Token selectByUserId(@Param(value = "userId")String userId);
    
    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);
	
}
