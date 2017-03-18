package com.aygxy.fmaket.goods.service;

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

}
