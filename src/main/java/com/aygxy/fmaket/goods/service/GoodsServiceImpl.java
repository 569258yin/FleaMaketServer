package com.aygxy.fmaket.goods.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aygxy.fmaket.goods.dao.GoodsMapper;
import com.aygxy.fmaket.goods.entity.Goods;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	
	@Resource
	GoodsMapper goodsMapper;
	
	@Override
	public boolean saveGoods(Goods goods) {
		int count = goodsMapper.insert(goods);
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Goods> selectGoodsByPage(int pageNum, int pageSize) {
		List<Goods> list = goodsMapper.selectGoodsByPage(pageNum, pageSize);
		return list;
	}

	@Override
	public List<Goods> selectGoodsByTime(int pageNum, int pageSize) {
		List<Goods> list = goodsMapper.selectGoodsByPageOrderTime(pageNum, pageSize);
		return list;
	}

	@Override
	public List<Goods> selectGoodsByAddress(int pageNum, int pageSize, double latitude, double longitude) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
