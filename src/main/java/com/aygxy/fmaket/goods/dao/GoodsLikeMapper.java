package com.aygxy.fmaket.goods.dao;

import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.goods.entity.GoodsLike;

@Repository
public interface GoodsLikeMapper {
    int deleteByPrimaryKey(String goodslikeid);

    int insert(GoodsLike record);

    int insertSelective(GoodsLike record);

    GoodsLike selectByPrimaryKey(String goodslikeid);

    int updateByPrimaryKeySelective(GoodsLike record);

    int updateByPrimaryKey(GoodsLike record);
}