package com.aygxy.fmaket.goods.service;

import java.util.List;

import com.aygxy.fmaket.goods.entity.GoodsSearch;

public interface GoodsSearchService {

	public boolean saveGoodsSearch(GoodsSearch goodsSearch);
	
	public boolean deleteGoodsSearch(String goodsSearchId);
	
	public boolean deleteByGoodsId(String goodsId);
	
	public List<GoodsSearch> matchKeyWords(String text);
}
