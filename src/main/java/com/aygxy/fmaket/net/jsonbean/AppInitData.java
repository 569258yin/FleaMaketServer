package com.aygxy.fmaket.net.jsonbean;
/**
 * app 启动时校验token后从服务器获取初始化信息
 * @author MyPC
 *
 */

import java.util.List;

import com.aygxy.fmaket.goods.entity.GoodsType;

public class AppInitData{
	
	private List<GoodsType> goodsTypes;

	public List<GoodsType> getGoodsTypes() {
		return goodsTypes;
	}

	public void setGoodsTypes(List<GoodsType> goodsTypes) {
		this.goodsTypes = goodsTypes;
	}
	
	
	
	
}
