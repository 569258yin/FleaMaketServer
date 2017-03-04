package com.aygxy.fmaket.goods.dao;

import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.goods.entity.GoodsCollect;

@Repository
public interface GoodsCollectMapper {
    int deleteByPrimaryKey(String gcid);

    int insert(GoodsCollect record);

    int insertSelective(GoodsCollect record);

    GoodsCollect selectByPrimaryKey(String gcid);

    int updateByPrimaryKeySelective(GoodsCollect record);

    int updateByPrimaryKey(GoodsCollect record);
}