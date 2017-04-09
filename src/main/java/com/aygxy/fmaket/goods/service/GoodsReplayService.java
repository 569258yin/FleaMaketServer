package com.aygxy.fmaket.goods.service;

import java.util.List;

import com.aygxy.fmaket.goods.entity.GoodsRepaly;
import com.aygxy.fmaket.goods.entity.ToRepaly;

public interface GoodsReplayService {
	
	/**
	 * 根据时间先后查询所有对应商品id评论
	 * @return
	 */
	public List<GoodsRepaly> selectAllByGoodsId(String goodsId);
	
	/**
	 * 
	 * @param goodsRepaly
	 * @return
	 */
	public boolean saveGoodsReplay(GoodsRepaly goodsRepaly);
	
	
	/**
	 * 回复评论
	 * @param toRepaly
	 * @return
	 */
	public boolean saveToReplay(ToRepaly toRepaly);
	
	
 }
