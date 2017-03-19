package com.aygxy.fmaket.goods.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aygxy.fmaket.goods.dao.GoodsTypeMapper;
import com.aygxy.fmaket.goods.entity.GoodsType;

@Service("goodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {
	
	@Resource
	GoodsTypeMapper goodsTypeMapper;
	
	@Override
	public List<GoodsType> getAllGoodsType() {
		return goodsTypeMapper.selectAllGoodsType();
	}

}
