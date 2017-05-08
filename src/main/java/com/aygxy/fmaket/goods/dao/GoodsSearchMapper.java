package com.aygxy.fmaket.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.goods.entity.GoodsSearch;

@Repository
public interface GoodsSearchMapper {
	
	int insert(GoodsSearch goodsSearch);
	
	List<GoodsSearch> matchKeyWords(@Param("text")String text);
	
	int delete(@Param("searchId")String searchId);
	
	int deleteByGoodsId(@Param("goodsId")String goodsId);
	
}
