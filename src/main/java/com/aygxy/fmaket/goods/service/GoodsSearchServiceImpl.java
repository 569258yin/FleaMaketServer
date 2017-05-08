package com.aygxy.fmaket.goods.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aygxy.fmaket.goods.dao.GoodsSearchMapper;
import com.aygxy.fmaket.goods.entity.GoodsSearch;

@Service("goodsSearchService")
public class GoodsSearchServiceImpl implements GoodsSearchService{

	@Resource
	GoodsSearchMapper goodsSearchMapper;
	
	@Override
	public boolean saveGoodsSearch(GoodsSearch goodsSearch) {
		int count = goodsSearchMapper.insert(goodsSearch);
		return count == 1;
	}

	@Override
	public boolean deleteGoodsSearch(String goodsSearchId) {
		int count = goodsSearchMapper.delete(goodsSearchId);
		return count == 1;
	}

	@Override
	public boolean deleteByGoodsId(String goodsId) {
		int count = goodsSearchMapper.deleteByGoodsId(goodsId);
		return count == 1;
	}

	@Override
	public List<GoodsSearch> matchKeyWords(String text) {
		List<GoodsSearch> lists = goodsSearchMapper.matchKeyWords(text);
		return lists;
	}

}
