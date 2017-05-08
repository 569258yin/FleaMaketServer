package com.aygxy.fmaket.goods.service;

import java.util.Date;
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

	@Override
	public List<Goods> selectGoodsByGoodsTypeId(int pageNum, int pageSize, int goodsTypeId) {
		List<Goods> list = goodsMapper.selectGoodsByGoodsTypeId(pageNum, pageSize, goodsTypeId);
		return list;
	}

	@Override
	public List<Goods> selectGoodsByUserId(String userId) {
		List<Goods> list = goodsMapper.selectGoodsByUserId(userId);
		return list;
	}
	
	@Override
	public Goods selectGoodsById(String goodsId) {
		return goodsMapper.selectByPrimaryKey(goodsId);
	}

	@Override
	public boolean updateGoods(Goods goods) {
		int count = goodsMapper.updateByPrimaryKey(goods);
		return count == 1 ? true : false;
	}

	@Override
	public boolean deleteGoods(String goodsId) {
		int count = goodsMapper.deleteByPrimaryKey(goodsId);
		return count == 1 ? true : false;
	}

	@Override
	public boolean refreshGoods(String goodsId, Date modifyDate) {
		int count = goodsMapper.updateModifyTime(goodsId, modifyDate);
		return count == 1 ? true : false;
	}

	
	

}
