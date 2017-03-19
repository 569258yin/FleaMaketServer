package com.aygxy.fmaket.goods.service;

import java.util.List;

import com.aygxy.fmaket.goods.entity.GoodsType;

public interface GoodsTypeService {
	
	/**
	 * 获取所有的类别
	 * @return
	 */
	public List<GoodsType> getAllGoodsType();
}
