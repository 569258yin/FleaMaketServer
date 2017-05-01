package com.aygxy.fmaket.goods.service;

import java.util.List;

import com.aygxy.fmaket.goods.entity.Goods;

public interface GoodsService {
	
	/**
	 * 保存商品
	 * @param goods
	 * @return
	 */
	public boolean saveGoods(Goods goods);
	
	/**
	 * 分页加载商品
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Goods> selectGoodsByPage(int pageNum, int pageSize);
	
	/**
	 * 通过时间排序分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Goods> selectGoodsByTime(int pageNum,int pageSize);
	
	/**
	 * 通过经纬度获取附近发布的商品
	 * @param pageNum
	 * @param pageSize
	 * @param latitude 纬度
	 * @param longitude 经度
	 * @return
	 */
	public List<Goods> selectGoodsByAddress(int pageNum,int pageSize,double latitude,double longitude);
	
	/**
	 * 通过时间排序分页查询
	 * @param pageNum
	 * @param pageSize
	 * @param goodsTypeId
	 * @return
	 */
	public List<Goods> selectGoodsByGoodsTypeId(int pageNum,int pageSize,int goodsTypeId);
	
	/**
	 * 查询指定用户发布的商品
	 * @param userId
	 * @return
	 */
	public List<Goods> selectGoodsByUserId(String userId);
	
	
	public Goods selectGoodsById(String goodsId);
	
	/**
	 * 更新
	 * @param goods
	 * @return
	 */
	public boolean updateGoods(Goods goods);
	
	/**
	 * 删除
	 * @param goodsId
	 * @return
	 */
	public boolean deleteGoods(String goodsId);
	
	
}
